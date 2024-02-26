package app;

import java.util.HashMap;
import java.util.Map;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.text.DecimalFormat;
import java.util.ArrayList;

public class GestionnaireStock {

    private static ObservableList<Element> listeElement = FXCollections.observableArrayList();
    static HashMap<Element, Float> stockElementCommande = new HashMap<>(); // HashMap pour vérifications de stocks au niveau des commandes
    
    public static void test() {
    	for (Element e : listeElement) {
    		System.out.println("Liste element : " + e);
    	}
    	for (Map.Entry<Element, Float> entry : stockElementCommande.entrySet()) {
            Element element = entry.getKey();
            Float quantite = entry.getValue();
            System.out.println("HashMap element : " + element + " | " + quantite);
    	}
    }
    
    public static void actualiserStock() {
    	for (Map.Entry<Element, Float> entry : stockElementCommande.entrySet()) {
            Element e = entry.getKey();
            Float q = entry.getValue();
            e.setQuantite(q);
            GestionnaireCommande.completerCommande();
            }
    }
    
    public static Element trouverElementParCode(String code) {
        for (Element element : listeElement) {
            if (element.getCode().equals(code)) {
                return element;
            }
        }
        return null; // Si aucun élément correspondant n'est trouvé
    }
    
    public void ajouterStock(Element element, float quantite) {
        // Vérifier si l'élément existe déjà dans la liste
        boolean existe = false;
        for (Element e : listeElement) {
            // Si le code de l'élément correspond à un élément existant dans le stock
            if (e.equals(element)) {
                // Mise à jour de la quantité de l'élément
                e.setQuantite(e.getQuantite() + quantite);
                //stockElementCommande.put(element, quantite);
                existe = true;
                break;
            }
        }
        
        // Si l'élément n'existe pas, l'ajouter à la liste
        if (!existe) {
            listeElement.add(element);
            //stockElementCommande.put(element, quantite);// Ajoute aussi les éléments dans une liste parallèle qui sert pour des vérifications, sans modifications de stocks réels
        }
        simulationAjouterStock(element, quantite);//Ajouter dans la liste de simulation
    }
    
    public void simulationAjouterStock(Element element, float quantite) {
    	// Vérifier si l'élément existe déjà dans la liste
        boolean existe = false;
        for (Map.Entry<Element, Float> entry : stockElementCommande.entrySet()) {
            Element e = entry.getKey();
            Float q = entry.getValue();
            if (e.getCode().equals(element.getCode())) {
                // Mise à jour de la quantité de l'élément
                stockElementCommande.put(element, q + quantite);
                existe = true;
                break;
            }
    	}
        
        // Si l'élément n'existe pas, l'ajouter à la liste
        if (!existe) {
            stockElementCommande.put(element, quantite);// Ajoute aussi les éléments dans une liste parallèle qui sert pour des vérifications, sans modifications de stocks réels
        }
    }
    
    public void simulationRetirerStock(Element element, float quantiteNecessaire) {
    	for (Map.Entry<Element, Float> entry : stockElementCommande.entrySet()) {
            Element e = entry.getKey();
            Float q = entry.getValue();
            if (e.equals(element)) {
    			if (q >= quantiteNecessaire) {
    				
    				DecimalFormat df = new DecimalFormat("#.##");
                	String resultatFormate = df.format(q - quantiteNecessaire);
                	String resultatFloatString = resultatFormate.replace(",", ".");
                	float res = Float.parseFloat(resultatFloatString);
                	stockElementCommande.put(element, res);
    			}
    		}
            }
    }
    
    /*public boolean verifierStockElement(Element element, float quantite) {
    	// Parcourir tous les éléments nécessaires pour vérifier si le stock est suffisant
        for (Element e: listeElement) {
        	if (e.equals(element)) {
        		if (e.getQuantite() >= quantite) {
        			return true;
        		}
        	}
        }
        return false;
    }*/
    
    public boolean simulationVerifierStockElement(Element element, float quantite) {
    	// Parcourir tous les éléments nécessaires pour vérifier si le stock est suffisant
    	for (Map.Entry<Element, Float> entry : stockElementCommande.entrySet()) {
            Element e = entry.getKey();
            Float q = entry.getValue();
        	if (e.equals(element)) {
        		if (q >= quantite) {
        			return true;
        		}
        	}
        }
        return false;
    }
    
    public boolean verifierStockCommande(Commande commande) {
        // Trouver le produit fini correspondant au code produit de la commande
        Element produitFinal = trouverElementParCode(commande.getCodeProduit());

        // Création d'une Map pour stocker les éléments nécessaires et leurs quantités requises pour la commande
        //HashMap<Element, Float> elementsNecessaires = new HashMap<>();
        HashMap<Element, Float> produitsFinauxNecessaires = new HashMap<>();
        HashMap<Element, Float> produitsIntermediairesNecessaires = new HashMap<>();
        HashMap<Element, Float> matieresPremieresNecessaires = new HashMap<>();

        // Calcul des éléments nécessaires pour produire le produit final dans la quantité demandée
        //calculerElementsNecessaires(produitFinal, commande.getQuantite(), dronesNecessaires, coquesEtPropulsionsNecessaires, matieresPremieresNecessaires);
        calculerProduitsFinauxNecessaires(produitFinal, commande.getQuantite(), produitsFinauxNecessaires, produitsIntermediairesNecessaires, matieresPremieresNecessaires);
        
        if (verifierStockProduit(produitsFinauxNecessaires)) {
        	commande.setRealisable(true);
        	for (Map.Entry<Element, Float> entry : produitsFinauxNecessaires.entrySet()) {
                Element element = entry.getKey();
                Float quantiteNecessaire = entry.getValue();

                //stockElementCommande.put(element, stockElementCommande.get(element) - quantiteNecessaire);
            }        
            return true; // Tous les éléments nécessaires sont en quantité suffisante dans le stock
        } 
        if (verifierStockProduit(produitsIntermediairesNecessaires)){
        	commande.setRealisable(true);
        	for (Map.Entry<Element, Float> entry : produitsIntermediairesNecessaires.entrySet()) {
                Element element = entry.getKey();
                Float quantiteNecessaire = entry.getValue();

                //stockElementCommande.put(element, stockElementCommande.get(element) - quantiteNecessaire);
            }        
            return true; // Tous les éléments nécessaires sont en quantité suffisante dans le stock
        }
        if (verifierStockProduit(matieresPremieresNecessaires)){
        	commande.setRealisable(true);
        	for (Map.Entry<Element, Float> entry : matieresPremieresNecessaires.entrySet()) {
                Element element = entry.getKey();
                Float quantiteNecessaire = entry.getValue();
                // On arrondi la quantité au dixième près pour empécher les erreurs les erreurs de précision entre les différents nombres
                // Cette manière de faire est aussi présente pour la méthode produire() pour maintenir la cohérence des résultats
                DecimalFormat df = new DecimalFormat("#.##");
            	String resultatFormate = df.format(stockElementCommande.get(element) - quantiteNecessaire);
            	String resultatFloatString = resultatFormate.replace(",", ".");
            	float res = Float.parseFloat(resultatFloatString);

                //stockElementCommande.put(element, res);
            }        
            return true; // Tous les éléments nécessaires sont en quantité suffisante dans le stock
        }
        return false;
        }
    
    public boolean verifierStockProduit(HashMap<Element, Float> stockNecessaire) {
    	// Parcourir tous les éléments nécessaires pour vérifier si le stock est suffisant
        for (Map.Entry<Element, Float> entree : stockNecessaire.entrySet()) {
        	Element element = entree.getKey();
        	float quantiteNecessaire = entree.getValue();
        	float quantiteEnStock = stockElementCommande.get(element);
        	if (quantiteEnStock < quantiteNecessaire) {
        	    return false; // Retourne faux si la quantité en stock d'un élément est insuffisante
        	}
        }
        return true;
    }    
    
    private void calculerProduitsFinauxNecessaires(Element element, float quantite, HashMap<Element, Float> produitsFinauxNecessaires, HashMap<Element, Float> produitsIntermediairesNecessaires, HashMap<Element, Float> matieresPremieresNecessaires) {
    	produitsFinauxNecessaires.merge(element, quantite, Float::sum);
        ChaineDeProduction chaine = GestionnaireProduction.getChaineParElementSortie(element); // Méthode pour obtenir la chaîne de production basée sur l'élément de sortie
        if (chaine != null) {
            for (Map.Entry<Element, Float> entree : chaine.getElementsEntree().entrySet()) {           	
            	Element elemEntree = entree.getKey();           	
            	float quantiteRequiseParUniteProduite = entree.getValue();
            	float quantiteTotaleRequise = chaine.getActivation() * quantiteRequiseParUniteProduite;
            	
            	// Récursivité pour prendre en compte les éléments d'entrée des éléments d'entrée
            	calculerProduitsIntermediairesNecessaires(elemEntree, quantiteTotaleRequise, produitsIntermediairesNecessaires, matieresPremieresNecessaires);
            }
        }
        return;
    }
    
    private void calculerProduitsIntermediairesNecessaires(Element element, float quantite, HashMap<Element, Float> produitsIntermediairesNecessaires, HashMap<Element, Float> matieresPremieresNecessaires) {
        ChaineDeProduction chaine = GestionnaireProduction.getChaineParElementSortie(element); // Méthode pour obtenir la chaîne de production basée sur l'élément de sortie
        if (chaine != null) {
        	produitsIntermediairesNecessaires.merge(element, quantite, Float::sum);
            for (Map.Entry<Element, Float> entree : chaine.getElementsEntree().entrySet()) {           	
            	Element elemEntree = entree.getKey();           	
            	float quantiteRequiseParUniteProduite = entree.getValue();
            	float quantiteTotaleRequise = chaine.getActivation() * quantiteRequiseParUniteProduite;
            	
            	// Récursivité pour prendre en compte les éléments d'entrée des éléments d'entrée
            	calculerMatieresPremieresNecessaires(elemEntree, quantiteTotaleRequise, matieresPremieresNecessaires);
            }
        }
        if (estMatierePremiere(element)) {
        	calculerMatieresPremieresNecessaires(element, quantite, matieresPremieresNecessaires);// Si ce n'est pas un produit intermédiaire, alors on appel la foncion pour les matières premières
        }
        return;
    }
    
    private void calculerMatieresPremieresNecessaires(Element element, float quantite, HashMap<Element, Float> matieresPremieresNecessaires) {
    	matieresPremieresNecessaires.merge(element, quantite, Float::sum);
        return;
    }
    
    public boolean estMatierePremiere(Element element) {
    	for (ChaineDeProduction chaine : GestionnaireProduction.getListeChaine()) {
    		if (chaine.getElementsSortie().containsKey(element)) {
    			return false;
    		}
    	}
    	//si l'élément n'est dans la sortie d'aucune chaîne, c'est une matière première
    	return true;
    }
    
    public static ObservableList<Element> getListeElement() { // Permet d'accéder à ma liste d'élément depuis n'importe où
        return listeElement;
    }
}
