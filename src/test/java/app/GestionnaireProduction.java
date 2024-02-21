package app;

import java.util.Map;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class GestionnaireProduction {

	private static ObservableList<ChaineDeProduction> listeChaine;
	
	public GestionnaireProduction(){
		this.listeChaine = FichierCSV.getListeChaine();
	}
	
	public void ajouterChaine(ChaineDeProduction c) {
		listeChaine.add(c);
	}
	

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
		
	}
	/*
	public static void simulerProduction(ObservableList<Element> listeElement, ObservableList<Commande> listeCommande) {
		// Étape 1 : Simulation de la production
	    for (ChaineDeProduction chaine : listeChaine) {
	        boolean peutProduire = true;
	        // Vérifier si chaque élément nécessaire à l'entrée est disponible
	        for (Map.Entry<Element, Float> entree : chaine.getElementsEntree().entrySet()) {
	            Element elementRequis = entree.getKey();
	            float quantiteRequise = entree.getValue()*//* * chaine.getNiveauActivation()*//*;

	            // Trouver l'élément dans la liste
	            Element elementDansListe = listeElement.stream()
	                .filter(e -> e.getCode().equals(elementRequis.getCode()))
	                .findFirst().orElse(null);

	            if (elementDansListe == null || elementDansListe.getQuantite() < quantiteRequise) {
	                peutProduire = false;
	                break; // Pas assez d'élément pour produire, passer à la prochaine chaîne
	            }
	        }

	        if (peutProduire) {
	            // Produire : réduire éléments d'entrée et ajouter/supplémenter éléments de sortie
	            // Suppression ou réduction des éléments d'entrée
	            // Ajout ou incrémentation des éléments de sortie
	        }
	        
	    }
	}*/
	public ObservableList<Element> effectuerProduction(ObservableList<Element> listElement, ObservableList<ChaineDeProduction> chaine) {
		    // On crée une nouvelle liste à partir de l'existant pour éviter des modifications inattendues
		    ObservableList<Element> listElementApresProduction = FXCollections.observableArrayList(listElement);

		    // Parcourir chaque chaine de production disponible
		    for (ChaineDeProduction chaineCourante : chaine) {
		        boolean peutProduire = true;
		        
		        // Premièrement, vérifier si on peut procéder à la production pour cette chaine 
		        for (Map.Entry<Element, Float> entree : chaineCourante.getElementsEntree().entrySet()) {
		            Element elementRequis = entree.getKey();
		            float quantiteRequise = entree.getValue() /** chaineCourante.getNiveauActivation()*/;

		            // Chercher cet élément dans la liste des éléments disponibles
		            Element elementDansListe = listElementApresProduction.stream()
		                    .filter(e -> e.getCode().equals(elementRequis.getCode()))
		                    .findFirst()
		                    .orElse(null);

		            // Si l'élément n'existe pas ou qu'il n'y a pas assez de quantité, on ne peut pas produire
		            if (elementDansListe == null || elementDansListe.getQuantite() < quantiteRequise) {
		                peutProduire = false;
		                break;
		            }
		        }

		        if (peutProduire) {
		            // Réduire la quantité des éléments d'entrée
		            for (Map.Entry<Element, Float> entree : chaineCourante.getElementsEntree().entrySet()) {
		                Element elementRequis = entree.getKey();
		                float quantiteRequise = entree.getValue()/* * chaineCourante.getNiveauActivation()*/;

		                listElementApresProduction.forEach(e -> {
		                    if (e.getCode().equals(elementRequis.getCode())) {
		                        e.setQuantite(e.getQuantite() - (int) quantiteRequise); // Assurez-vous que la conversion de type est gérée proprement
		                    }
		                });
		            }

		            // Ajouter ou mettre à jour la quantité des éléments de sortie
		            for (Map.Entry<Element, Float> sortie : chaineCourante.getElementsSortie().entrySet()) {
		                Element elementProduit = sortie.getKey();
		                float quantiteProduite = sortie.getValue()/* * chaineCourante.getNiveauActivation()*/;

		                Element elementDansListe = listElementApresProduction.stream()
		                        .filter(e -> e.getCode().equals(elementProduit.getCode()))
		                        .findFirst()
		                        .orElse(null);

		                if (elementDansListe != null) {
		                    // Mettre à jour la quantité si l'élément existe déjà
		                    elementDansListe.setQuantite(elementDansListe.getQuantite() + (int) quantiteProduite);
		                } else {
		                    // Ajouter un nouvel élément si nécessaire
		                    Element nouvelElement = new Element(elementProduit.getCode(), elementProduit.getNom(), 
		                                                         (int) quantiteProduite, elementProduit.getUniteDeMesure(), 
		                                                         elementProduit.getPrixAchat(), elementProduit.getPrixVente());
		                    listElementApresProduction.add(nouvelElement);
		                }
		            }
		        }
		    }

		    // Retourner la liste mise à jour après avoir effectué la production
		    return listElementApresProduction;
		}

}



