package app;

import java.util.Map;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class GestionnaireProduction {

	private static ObservableList<ChaineDeProduction> listeChaine = FXCollections.observableArrayList();
	private static GestionnaireStock GeStock;
	
	public GestionnaireProduction(GestionnaireStock GeStock){
		this.GeStock = GeStock;
	}
	
	public void ajouterChaine(ChaineDeProduction c) {
		listeChaine.add(c);
	}
	
	public static ObservableList<ChaineDeProduction> getListeChaine(){
		return listeChaine;
	}
	
	// Methode pour retrouver une chaine de production basée sur un élément produit en sortie
		public static ChaineDeProduction getChaineParElementSortie(Element elementSortie) {
			for (ChaineDeProduction chaine : GestionnaireProduction.getListeChaine()) {
				if (chaine.getElementsSortie().containsKey(elementSortie)) {
					return chaine;
					}
			    }
			    return null;
			}
	/*
	public void ajusterNiveauActivation(String codeChaine, SimpleIntegerProperty niveauActivation) {
		boolean chaineOK = false;
		for (ChaineDeProduction c: listeChaine) {
			if (c.getCode().equals(codeChaine)) {
				c.activer(niveauActivation);
				chaineOK = true;
			}
		}
		if (chaineOK = false) {
			System.out.println("Aucune chaîne de production trouvée avec le code : " + codeChaine);
		}
	}*/
	
	
//#########################################################################################################
//Définir dynamiquement le niveau d'activation des 3 Chaînes de Production pour réaliser une certaine Commande
//#########################################################################################################
	
	/*
	 * - Calcul le niveau d'activation pour la commande passée en paramètre
	 * - Récupère le produit final (produit demandé inscrit sur la commande)
	 * - Appelle une méthode qui prend en paramètre 
	 * ce même produit final et la quantité de ce produit demandé sur la commande
	 */
	public void calculerNiveauActivationPourCommande(Commande commande) {
	    Element produitFinal = GestionnaireStock.trouverElementParCode(commande.getCodeProduit());
	    calculerNiveauActivation(produitFinal, commande.getQuantite());
	}

	/*
	 * - Définit le niveau d'activation adéquat pour la chaîne qui s'occupe de l'élément passé en paramètre
	 * selon la quantité indiquée
	 */
	private void calculerNiveauActivation(Element element, float quantiteRequise) {
	    ChaineDeProduction chaine = getChaineParElementSortie(element);//renvoie la chaine de production qui produit cet élément

	    if (chaine != null) {
	        // Calcul de la quantité produite par la chaine pour une seule activation
	        float quantiteProduiteParNiveau = chaine.getElementsSortie().get(element);//récupère la quantité d'élément produite pour un niveau d'activation de 1
	        int niveauActivationNecessaire = (int) Math.ceil(quantiteRequise / quantiteProduiteParNiveau);//Calcul le niveau d'activation nécessaire pour produire la quantité demandée

	        chaine.activer(niveauActivationNecessaire);

	        // Calcul rétroactif pour chaque élément d'entrée
	        for (Map.Entry<Element, Float> entree : chaine.getElementsEntree().entrySet()) {
	            float quantiteEntreeRequise = entree.getValue() * niveauActivationNecessaire;
	            calculerNiveauActivation(entree.getKey(), quantiteEntreeRequise);
	        }
	    }
	}
	
	
	
	
	
	
	public static void simulerProduction() {
	    for (Commande commande : GestionnaireCommande.getListeCommande()) {
	        if (GeStock.verifierStockCommande(commande)) {
	        	System.out.println("test");
	            // Calcul des niveaux d'activation pour chaque commande
	            //calculerNiveauActivationPourCommande(commande);
	            // Production et mise à jour des quantités dans le stock
	            produireSelonCommande(commande);
	        } else {
	            System.out.println("Stock insuffisant pour la commande: " + commande.getNumeroCommande());
	            // Vous pouvez ici ajouter une logique pour lister les éléments manquants, si nécessaire
	        }
	    }
	}
	
	private static void produireSelonCommande(Commande commande) {
	    Element elementProduit = GestionnaireStock.trouverElementParCode(commande.getCodeProduit());
	    // On suppose que ce produit final est produit par une chaîne principale. L'étape initiale.
	    produire(elementProduit, commande.getQuantite());
	}
	
	private static void produire(Element element, float quantiteNecessaire) {
	    ChaineDeProduction chainePrincipale = getChaineParElementSortie(element);
	    
	    if (chainePrincipale != null) {
	        int niveauActivationNecessaire = (int) Math.ceil(quantiteNecessaire / chainePrincipale.getElementsSortie().get(element));
	        chainePrincipale.activer(niveauActivationNecessaire);
	        
	        // Pour chaque élément d'entrée de cette chaîne, vérifier si un autre besoin de production est nécessaire
	        for (Map.Entry<Element, Float> entree : chainePrincipale.getElementsEntree().entrySet()) {
	            Element elementEntree = entree.getKey();
	            float quantiteEntreeRequise = entree.getValue() * niveauActivationNecessaire;
	            ChaineDeProduction chaineSecondaire = getChaineParElementSortie(elementEntree);
	            
	            if (chaineSecondaire != null) {
	                // Si l'élément d'entrée est également produit par une autre chaîne, produire cet élément en premier
	                produire(elementEntree, quantiteEntreeRequise);
	            } else {
	                // Sinon, ça signifie que l'élément doit déjà être disponible en stock
	                GeStock.ajouterStock(elementEntree, -Math.round(quantiteEntreeRequise));
	            }
	        }

	        // Après s'être assuré de la disponibilité des éléments d'entrée, produit l'élément de sortie
	        float quantiteProduite = chainePrincipale.getElementsSortie().get(element) * niveauActivationNecessaire;
	        GeStock.ajouterStock(element, Math.round(quantiteProduite));
	    }
	}
	
	
	
	
	/*
	public static void simulerProduction(ObservableList<Element> listeElement, ObservableList<Commande> listeCommande) {
		for (ChaineDeProduction cp : listeChaine) {
			for (Commande com : listeCommande) {
				simulerProductionPourChaine(cp, listeElement, com);
			}
        }
	}

	private static void simulerProductionPourChaine(ChaineDeProduction cp, ObservableList<Element> listeElement, Commande com) {
		HashMap<Element, Float> elementsEntree = cp.getElementsEntree();
        HashMap<Element, Float> elementsSortie = cp.getElementsSortie();
        
     // Vérification des éléments d'entrée disponibles
        for (Map.Entry<Element, Float> entree : elementsEntree.entrySet()) {
            Element elementEntree = entree.getKey();
            float quantiteNecessaire = entree.getValue() * cp.getActivation();
            
            // Rechercher cet élément dans listeElement pour vérifier la quantité
            for (Element el : listeElement) {
                if (el.getCode().equals(elementEntree.getCode()) && el.getQuantite() >= quantiteNecessaire) {
                    // Réduire la quantité de l'élément d'entrée dans listeElement
                    el.setQuantite(el.getQuantite() - (int)quantiteNecessaire);
                } else {
                    System.out.println("Quantité insuffisante");
                    return;
                }
            }
        }
     // Augmentation des quantités des éléments de sortie
        for (Map.Entry<Element, Float> sortie : elementsSortie.entrySet()) {
            Element elementSortie = sortie.getKey();
            float quantiteProduite = sortie.getValue() * cp.getActivation();
            
            boolean found = false;
            for (Element el : listeElement) {
                if (el.getCode().equals(elementSortie.getCode())) {
                    el.setQuantite(el.getQuantite() + (int)quantiteProduite);
                    found = true;
                    break;
                }
            }
        }
	}*/
}



