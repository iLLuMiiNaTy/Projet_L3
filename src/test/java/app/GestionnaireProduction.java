package app;

import java.util.Map;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class GestionnaireProduction {

	private static ObservableList<ChaineDeProduction> listeChaine = FXCollections.observableArrayList();
	private static GestionnaireStock GeStock;
	
	public GestionnaireProduction(GestionnaireStock GeStock){
		GestionnaireProduction.GeStock = GeStock;
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
	
//#########################################################################################################
//Définir dynamiquement le niveau d'activation des 3 Chaînes de Production pour réaliser une certaine Commande
//#########################################################################################################
	
	/*
	 * - Calcul le niveau d'activation pour la commande passée en paramètre
	 * - Récupère le produit final (produit demandé inscrit sur la commande)
	 * - Appelle une méthode qui prend en paramètre 
	 * ce même produit final et la quantité de ce produit demandé sur la commande
	 */
	public static void calculerNiveauActivationPourCommande(Commande commande) {
	    Element produitFinal = GestionnaireStock.trouverElementParCode(commande.getCodeProduit());
	    calculerNiveauActivation(produitFinal, commande.getQuantite());
	}

	/*
	 * - Définit le niveau d'activation adéquat pour la chaîne qui s'occupe de l'élément passé en paramètre
	 * selon la quantité indiquée
	 */
	private static void calculerNiveauActivation(Element element, float quantiteRequise) {
	    ChaineDeProduction chaine = getChaineParElementSortie(element);//renvoie la chaine de production qui produit cet élément
	    for (Element e : GestionnaireStock.getListeElement()) {
	    	if (e.equals(element)) {
	    		if (chaine != null) {
	    	        // Calcul de la quantité produite par la chaine pour une seule activation
	    	        float quantiteProduiteParNiveau = chaine.getElementsSortie().get(element);//récupère la quantité d'élément produite pour un niveau d'activation de 1
	    	        int niveauActivationNecessaire = (int) Math.ceil((quantiteRequise - e.getQuantite()) / quantiteProduiteParNiveau);//Calcul le niveau d'activation nécessaire pour produire la quantité demandée

	    	        chaine.activer(niveauActivationNecessaire);

	    	        // Calcul rétroactif pour chaque élément d'entrée
	    	        for (Map.Entry<Element, Float> entree : chaine.getElementsEntree().entrySet()) {
	    	            float quantiteEntreeRequise = entree.getValue() * niveauActivationNecessaire;
	    	            calculerNiveauActivation(entree.getKey(), quantiteEntreeRequise);
	    	        }
	    	    }
	    	}
	    }
	}
	
	private static void simulationCalculerNiveauActivation(Element element, float quantiteRequise) {
	    ChaineDeProduction chaine = getChaineParElementSortie(element);//renvoie la chaine de production qui produit cet élément
	    for (Map.Entry<Element, Float> entry : GestionnaireStock.stockElementCommande.entrySet()) {
            Element e = entry.getKey();
            Float q = entry.getValue();
	    	if (e.equals(element)) {
	    		if (chaine != null) {
	    	        // Calcul de la quantité produite par la chaine pour une seule activation
	    	        float quantiteProduiteParNiveau = chaine.getElementsSortie().get(element);//récupère la quantité d'élément produite pour un niveau d'activation de 1
	    	        int niveauActivationNecessaire = (int) Math.ceil((quantiteRequise - q) / quantiteProduiteParNiveau);//Calcul le niveau d'activation nécessaire pour produire la quantité demandée

	    	        chaine.activer(niveauActivationNecessaire);

	    	        // Calcul rétroactif pour chaque élément d'entrée
	    	        for (Map.Entry<Element, Float> entree : chaine.getElementsEntree().entrySet()) {
	    	            float quantiteEntreeRequise = entree.getValue() * niveauActivationNecessaire;
	    	            calculerNiveauActivation(entree.getKey(), quantiteEntreeRequise);
	    	        }
	    	    }
	    	}
	    }
	}
	
	public static void simulerProduction(Commande commande) {
		//calculerNiveauActivationPourCommande(commande);
		GeStock.verifierStockCommande(commande);
		simulationProduireCommande(commande);
		}
	
	/*public static void produireTouteCommande() {
		for (Commande commande : GestionnaireCommande.getListeCommande()) {
			if (commande.getRealisable() && !commande.getStatut()) { // On regarde si la commande est marqué comme Réalisable avant d'entamer la production
				commande.setStatut(true);// On passe le statut de la commande à true pour indiquer qu'elle est faite et ne sera donc pas à refaire
				Element elementProduit = GestionnaireStock.trouverElementParCode(commande.getCodeProduit());
			    // On suppose que ce produit final est produit par une chaîne principale. L'étape initiale.
			    produire(elementProduit, commande.getQuantite());
			    GeStock.retirerStock(elementProduit, commande.getQuantite());
			    GestionnaireStock.actualiserStockElementCommande();
			}
		}
	}*/
	
	public static void simulationProduireCommande(Commande commande) {
		if (commande.getRealisable() && !commande.getSimulee()) { // On regarde si la commande est marqué comme Réalisable avant d'entamer la production
			commande.setSimulee(true);;// On passe la valeur simulee de la commande à true pour indiquer qu'elle est faite et ne sera donc pas à refaire
			Element elementProduit = GestionnaireStock.trouverElementParCode(commande.getCodeProduit());
		    // On suppose que ce produit final est produit par une chaîne principale. L'étape initiale.
			
			if (GeStock.simulationVerifierStockElement(elementProduit, commande.getQuantite())) {
				GeStock.simulationRetirerStock(elementProduit, commande.getQuantite());
			} else {
			    simulationProduire(elementProduit, commande.getQuantite());
			    GeStock.simulationRetirerStock(elementProduit, (float) commande.getQuantite());
			}
		}
	}
	
	public static void simulationProduire(Element element, float quantiteNecessaire) {
		simulationCalculerNiveauActivation(element, quantiteNecessaire);
		if (GeStock.simulationVerifierStockElement(element, quantiteNecessaire)) {
			GeStock.simulationRetirerStock(element, quantiteNecessaire);
		} else {
			ChaineDeProduction chainePrincipale = getChaineParElementSortie(element);
		    if (chainePrincipale != null) {	        
		        // Pour chaque élément d'entrée de cette chaîne, vérifier si un autre besoin de production est nécessaire
		        for (Map.Entry<Element, Float> entree : chainePrincipale.getElementsEntree().entrySet()) {
		            Element elementEntree = entree.getKey();
		            float quantiteEntreeRequise = entree.getValue() * chainePrincipale.getActivation();
		            ChaineDeProduction chaineSecondaire = getChaineParElementSortie(elementEntree);
		            if (chaineSecondaire != null) {
		                // Si l'élément d'entrée est également produit par une autre chaîne, produire cet élément en premier
		                simulationProduire(elementEntree, quantiteEntreeRequise);
		                GeStock.simulationRetirerStock(elementEntree, quantiteEntreeRequise);
		            } else {
		                // Sinon, ça signifie que l'élément doit déjà être disponible en stock
		                GeStock.simulationRetirerStock(elementEntree, quantiteEntreeRequise);
		            }
		        }

		        // Après s'être assuré de la disponibilité des éléments d'entrée, produit l'élément de sortie
		        float quantiteProduite = chainePrincipale.getElementsSortie().get(element) * chainePrincipale.getActivation();
		        //System.out.println("  Ajouter stock : " + element + " | " + quantiteProduite);
		        GeStock.simulationAjouterStock(element, quantiteProduite);  
		    }
		}
	}
	
	/*private static void produire(Element element, float quantiteNecessaire) {
		calculerNiveauActivation(element, quantiteNecessaire);
		if (GeStock.verifierStockElement(element, quantiteNecessaire)) {
			System.out.println("  Retirer stock (inter) : " + element + " | " + quantiteNecessaire);
			GeStock.retirerStock(element, quantiteNecessaire);
		} else {
			ChaineDeProduction chainePrincipale = getChaineParElementSortie(element);
		    if (chainePrincipale != null) {	        
		        // Pour chaque élément d'entrée de cette chaîne, vérifier si un autre besoin de production est nécessaire
		        for (Map.Entry<Element, Float> entree : chainePrincipale.getElementsEntree().entrySet()) {
		            Element elementEntree = entree.getKey();
		            float quantiteEntreeRequise = entree.getValue() * chainePrincipale.getActivation();
		            ChaineDeProduction chaineSecondaire = getChaineParElementSortie(elementEntree);
		            if (chaineSecondaire != null) {
		                // Si l'élément d'entrée est également produit par une autre chaîne, produire cet élément en premier
		                produire(elementEntree, quantiteEntreeRequise);
		                System.out.println("  Retirer stock (if) : " + elementEntree + " | " + quantiteEntreeRequise);
		                GeStock.retirerStock(elementEntree, quantiteEntreeRequise);
		            } else {
		                // Sinon, ça signifie que l'élément doit déjà être disponible en stock
		            	System.out.println("  Retirer stock (else) : " + elementEntree + " | " + quantiteEntreeRequise);
		                GeStock.retirerStock(elementEntree, quantiteEntreeRequise);
		            }
		        }

		        // Après s'être assuré de la disponibilité des éléments d'entrée, produit l'élément de sortie
		        float quantiteProduite = chainePrincipale.getElementsSortie().get(element) * chainePrincipale.getActivation();
		        System.out.println("Ajouter stock : " + element + " | " + quantiteProduite);
		        GeStock.ajouterStock(element, quantiteProduite);  
		    }
		}
	}*/
}



