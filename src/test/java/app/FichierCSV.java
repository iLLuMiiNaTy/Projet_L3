package app;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FichierCSV {
	
	 private static List <Element> elements;
	 private List <ChaineDeProduction> chaines;
	 private static String pathElement = "src/test/resources/elements.csv";
	 private static String pathChaine = "src/test/resources/chaines.csv";
	
	public void chargerDonnees() {
        chargerElements(pathElement);
        chargerChaines(pathChaine);
    }
	
//#########################################################################################################    
										//ELEMENTS
//#########################################################################################################  
	
	public void chargerElements(String pathElement) {
        elements = new ArrayList<>();// Initialisation de la liste des éléments

        try (BufferedReader reader = new BufferedReader(new FileReader(pathElement))) {
            String line;
            //Partie pour supprimer la première ligne des fichiers csv qui sert uniquement d'en tête
            boolean firstLine = true; // Variable pour suivre la première ligne
            while ((line = reader.readLine()) != null) {
                if (firstLine) {
                    firstLine = false;
                    continue; // Ignorer la première ligne
                }// Suppression de la première ligne terminée

                // Traitement des lignes suivantes
                String[] data = line.split(";");
                String code = data[0];
                String nom = data[1];
                int quantite = Integer.parseInt(data[2]);
                String uniteDeMesure = data[3];
                String prixAchat = data[4];
                String prixVente = data[5];

                Element element = new Element(code, nom, quantite, uniteDeMesure, prixAchat, prixVente);
                elements.add(element);
                //gestionnaireStock.ajouterStock(element, quantite);
            }
        } catch (IOException e) {
            System.out.println("Erreur lors du chargement du fichier elements.csv : " + e.getMessage());
        }
    }
	
	public void afficherElements() {
		System.out.println("\nAffichage des éléments :");
        for (Element element : elements) {
        	System.out.println("\n");
        	System.out.println(element);
        }
    }
	
	public void sauvegarderElements() {
        String elementsFilePath = "src/test/resources/elementsExport.csv";

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(elementsFilePath))) {
            writer.write("code;nom;quantite;uniteDeMesure"); // Première ligne du fichier CSV

            writer.newLine();

            for (Element element : elements) {
                String line = element.getCode() + ";" + element.getNom() + ";" + element.getQuantite() + ";" + element.getUniteDeMesure();
                writer.write(line);
                writer.newLine();
            }

            writer.flush();
        } catch (IOException e) {
            System.out.println("Erreur lors de la sauvegarde des éléments : " + e.getMessage());
        }
    }
	
//#########################################################################################################    
									//CHAINE DE PRODUCTION
//######################################################################################################### 

	private void chargerChaines(String pathChaine) {
        chaines = new ArrayList<>();// Initialisation de la liste des chaines

        try (BufferedReader reader = new BufferedReader(new FileReader(pathChaine))) {
            String line;
            boolean firstLine = true; // Variable pour suivre la première ligne
            while ((line = reader.readLine()) != null) {
                if (firstLine) {
                    firstLine = false;
                    continue; // Ignorer la première ligne
                }

                // Traitement des lignes suivantes
                String[] data = line.split(";");
                String code = data[0];
                String nom = data[1];

                HashMap<Element, Float> elementsEntree = new HashMap<>();
                String[] elementsEntreeData = data[2].split(",");
                for (String elementData : elementsEntreeData) {
                    elementData = elementData.replaceAll("[()\\s]+", "");
                    String[] elementInfo = elementData.split(":");
                    String elementCode = elementInfo[0];
                    float quantite = Float.parseFloat(elementInfo[1]);

                    Element element = trouverElementParCode(elementCode); // Nous supposons que la méthode trouverElementParCode existe pour trouver l'élément correspondant au code
                    elementsEntree.put(element, quantite);
                }

                HashMap<Element, Float> elementsSortie = new HashMap<>();
                String[] elementsSortieData = data[3].split(",");
                for (String elementData : elementsSortieData) {
                    elementData = elementData.replaceAll("[()\\s]+", "");
                    String[] elementInfo = elementData.split(":");
                    String elementCode = elementInfo[0];
                    float quantite = Float.parseFloat(elementInfo[1]);

                    Element element = trouverElementParCode(elementCode); // Nous supposons que la méthode trouverElementParCode existe pour trouver l'élément correspondant au code
                    elementsSortie.put(element, quantite);
                }

                ChaineDeProduction chaine = new ChaineDeProduction(code, nom, elementsEntree, elementsSortie);
                chaines.add(chaine);
                //gestionnaireProduction.ajouterChaine(chaine);
            }
        } catch (IOException e) {
            System.out.println("Erreur lors du chargement du fichier chaines.csv : " + e.getMessage());
        }
    }
	
	public void afficherChaines() {
        System.out.println("\nListe des chaînes de production :");
        for (ChaineDeProduction chaine : chaines) {
        	System.out.println("\n");
        	System.out.println(chaine);
            
        }
    }
	
	private Element trouverElementParCode(String code) {
        for (Element element : elements) {
            if (element.getCode().equals(code)) {
                return element;
            }
        }
        return null; // Si aucun élément correspondant n'est trouvé
    }

}
