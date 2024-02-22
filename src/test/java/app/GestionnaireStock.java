package app;

import java.util.HashMap;
import java.util.Map;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.ArrayList;

public class GestionnaireStock {

    private static ObservableList<Element> listeElement = FXCollections.observableArrayList();
    
    public static Element trouverElementParCode(String code) {
        for (Element element : listeElement) {
            if (element.getCode().equals(code)) {
                return element;
            }
        }
        return null; // Si aucun élément correspondant n'est trouvé
    }

    public void ajouterStock(Element e, float q) {
        for (Element elem : listeElement) {
        	if (elem.equals(e)){
        		e.setQuantite(e.getQuantite() + q);
        	}
        }
        listeElement.add(e);
        //System.out.println("Erreur aujouterStock | Classe GestionnaireStock\nListeElement ne contient pas cette élément");
        
    }

    public void retirerStock(Element e, int q) {
    	for (Element elem : listeElement) {
    		if (elem.equals(e)) {
    			e.setQuantite(e.getQuantite() - q);
    		}else {
    			if (e.getQuantite() < q) {
    				System.out.println("Erreur : Stock insuffisant !\n");
    			}
    		}
    	}
    }
    /*
    public void afficherStock() { // need ?
        String s = "";
        for (Element e : this.listeElementStock.keySet()) {
            s += e.getNom() + " : " + e.getQuantite() + "\n";
        }
        System.out.println(s);
    }
    */

    /*public static boolean verifierStockCommande(Commande c) { // estStockSuffisant()
    	Element produitStock = FichierCSV.trouverElementParCode(c.getCodeProduit());
    	return (c.getQuantite() <= produitStock.getQuantite());
    }*/
    
    public boolean verifierStockCommande(Commande commande) {
        // Trouver le produit fini correspondant au code produit de la commande
        Element produitFinal = trouverElementParCode(commande.getCodeProduit());

        // Création d'une Map pour stocker les éléments nécessaires et leurs quantités requises pour la commande
        HashMap<Element, Float> elementsNecessaires = new HashMap<>();

        // Calcul des éléments nécessaires pour produire le produit final dans la quantité demandée
        calculerElementsNecessaires(produitFinal, commande.getQuantite(), elementsNecessaires);
        
        System.out.println(commande);

        // Parcourir tous les éléments nécessaires pour vérifier si le stock est suffisant
        for (Map.Entry<Element, Float> entree : elementsNecessaires.entrySet()) {
        	System.out.println("________________________________________________________________________");
        	System.out.println(entree);
        	Element element = entree.getKey();
        	float quantiteNecessaire = entree.getValue();
        	if (element.getQuantite() < quantiteNecessaire) {
        	    return false; // Retourne faux si la quantité en stock d'un élément est insuffisante
        	}
        }
        System.out.println("\n##### Commande validé #####\n");
        return true; // Tous les éléments nécessaires sont en quantité suffisante dans le stock
    }
    
    private void calculerElementsNecessaires(Element element, float quantite, HashMap<Element, Float> elementsNecessaires) {
    	if (estMatierePremiere(element)) {
            // Si l'élément est une matière première, on l'ajoute simplement à la liste des éléments nécessaires avec sa quantité
            elementsNecessaires.merge(element, quantite, Float::sum);
            return; // On sort de la méthode car il n'y a pas de décomposition à faire
        }
    	
        ChaineDeProduction chaine = GestionnaireProduction.getChaineParElementSortie(element); // Méthode pour obtenir la chaîne de production basée sur l'élément de sortie
        if (chaine != null) {
            for (Map.Entry<Element, Float> entree : chaine.getElementsEntree().entrySet()) {           	
            	Element elemEntree = entree.getKey();           	
            	float quantiteRequiseParUniteProduite = entree.getValue();            	
            	float quantiteTotaleRequise = quantite * quantiteRequiseParUniteProduite;
            	
            	// Récursivité pour prendre en compte les éléments d'entrée des éléments d'entrée
            	calculerElementsNecessaires(elemEntree, quantiteTotaleRequise, elementsNecessaires);
            }
        }
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

    /*public HashMap<Commande,Boolean> verifierStockListeCommande(ArrayList<Commande> listeCommande) {
        HashMap<Commande,Boolean> listeCverifiée = new HashMap<>();
        for (Commande c : listeCommande) {
            listeCverifiée.put(c, verifierStockCommande(c));
        }
        return listeCverifiée;
    }*/
    
    public void satisfaireCommande (Commande c) {
    	retirerStock(trouverElementParCode(c.getCodeProduit()), c.getQuantite());
    }
    
    public static ObservableList<Element> getListeElement() { // Permet d'accéder à ma liste d'élément depuis n'importe où
        return listeElement;
    }
}
