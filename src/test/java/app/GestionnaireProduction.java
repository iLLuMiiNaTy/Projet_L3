package app;

import java.util.Map;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
/**
 * Classe GestionnaireProduction
 * 
 * Cette classe permet de gérer les chaines de production
 * Elle contient une liste de chaines de production
 */
public class GestionnaireProduction {

	private static ObservableList<ChaineDeProduction> listeChaine = FXCollections.observableArrayList();
	private static GestionnaireStock GeStock;

	/**
	 * Constructeur de la classe GestionnaireProduction
	 * 
	 * @param GeStock
	 */
	public GestionnaireProduction(GestionnaireStock GeStock){
		GestionnaireProduction.GeStock = GeStock;
	}
	/**
	 * Ajoute une chaine de production à la liste de chaine de production
	 * @param c
	 */
	public void ajouterChaine(ChaineDeProduction c) {
		listeChaine.add(c);
	}
	/**
	 * Renvoi la liste des chaines de production
	 * @return
	 */
	public static ObservableList<ChaineDeProduction> getListeChaine(){
		return listeChaine;
	}

	/**
	 * Renvoi une chaine de production par son nom
	 * 
	 * @param nom
	 * @return
	 */
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
	
		/**
		 * Calculer le niveau d'activation nécessaire pour produire une commande
		 * 
		 * @param commande
		 */
	public static void calculerNiveauActivationPourCommande(Commande commande) {
	    Element produitFinal = GestionnaireStock.trouverElementParCode(commande.getCodeProduit());
	    calculerNiveauActivation(produitFinal, commande.getQuantite());
	}

	/**
	 * Calculer le niveau d'activation nécessaire pour produire un élément
	 * 
	 * @param element
	 * @param quantiteRequise
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
	/**
	 * Simulation du calcul du niveau d'activation nécessaire pour produire une commande
	 * @param element
	 * @param quantiteRequise
	 */
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

	/**
	 * Simulation de la production d'une commande
	 * 
	 * @param commande
	 */
	public static void simulerProduction(Commande commande) {
		//calculerNiveauActivationPourCommande(commande);
		GeStock.verifierStockCommande(commande);
		simulationProduireCommande(commande);
		}
	/**
	 * Simulation de la production d'une commande
	 * @param commande
	 */
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

	/**
	 * Simulation de la production d'un élément dans une quantitéé donnée
	 * 
	 * @param element
	 * @param quantiteNecessaire
	 */
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
}



