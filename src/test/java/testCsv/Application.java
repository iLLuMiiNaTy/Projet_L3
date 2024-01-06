package testCsv;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Application {

    private static List <Element> elements;
    private List <ChaineDeProduction> chaines;
    
    public static void main(String[] args) {
		Application application = new Application();
		application.chargerDonnees();
		System.out.println("##########################");
		System.out.println("AFFICHAGE ELEMENTS");
		System.out.println("##########################");
		application.afficherElements();
		//System.out.println("##########################");
		//System.out.println("AFFICHAGE CHAINES");
		//System.out.println("##########################");
		//application.afficherChaines();
		Element element = new Element("Test1", "Elem1", 50, "Test unité");
        elements.add(element);
        application.sauvegarderElements();
		System.out.println("##########################");
		System.out.println("AFFICHAGE ELEMENTS VERSION 2");
		System.out.println("##########################");
		application.afficherElements();
		

	}

    public void chargerDonnees() {
        chargerElements();
        chargerChaines();
        //chargerPrix();
    }
    
//#########################################################################################################    
										//ELEMENTS
//#########################################################################################################  
    
    
    
    

    private void chargerElements() {
        String elementsFilePath = "src/test/resources/elements.csv";
        
        elements = new ArrayList<>();// Initialisation de la liste des éléments

        try (BufferedReader reader = new BufferedReader(new FileReader(elementsFilePath))) {
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
                int quantite = Integer.parseInt(data[2]);
                String uniteDeMesure = data[3];

                Element element = new Element(code, nom, quantite, uniteDeMesure);
                elements.add(element);
                //gestionnaireStock.ajouterStock(element, quantite);
            }
        } catch (IOException e) {
            System.out.println("Erreur lors du chargement du fichier elements.csv : " + e.getMessage());
        }
    }
    
    public void afficherElements() {
        for (Element element : elements) {
            System.out.println("Code: " + element.getCode());
            System.out.println("Nom: " + element.getNom());
            System.out.println("Quantité: " + element.getQuantite());
            System.out.println("Unité de mesure: " + element.getUniteDeMesure());
            System.out.println("\n-----------------------------\n");
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

    private void chargerChaines() {
        String chainesFilePath = "src/test/resources/chaines.csv";

        chaines = new ArrayList<>();// Initialisation de la liste des chaines

        try (BufferedReader reader = new BufferedReader(new FileReader(chainesFilePath))) {
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
        System.out.println("Liste des chaînes de production :");
        for (ChaineDeProduction chaine : chaines) {
            System.out.println("Code : " + chaine.getCode());
            System.out.println("Nom : " + chaine.getNom());
            System.out.println("Elements en entrée :");
            for (Map.Entry<Element, Float> entry : chaine.getElementsEntree().entrySet()) {
                Element element = entry.getKey();
                String quantite = entry.getValue().toString();
                System.out.println("- " + element.getNom() + " (" + quantite + " " + element.getUniteDeMesure() + ")");
            }
            System.out.println("Elements en sortie :");
            for (Map.Entry<Element, Float> entry : chaine.getElementsSortie().entrySet()) {
                Element element = entry.getKey();
                String quantite = entry.getValue().toString();
                System.out.println("- " + element.getNom() + " (" + quantite + " " + element.getUniteDeMesure() + ")");
            }
            System.out.println("Niveau d'activation : " + chaine.getNiveauActivation());
            System.out.println("\n---------------------------------------\n");
        }
    }

    private void chargerPrix() {
        String prixFilePath = "chemin/vers/fichier/prix.csv";

        try (BufferedReader reader = new BufferedReader(new FileReader(prixFilePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] data = line.split(",");
                String codeElement = data[0];
                float prixAchat = data[1].equals("NA") ? 0.0f : Float.parseFloat(data[1]);
                float prixVente = data[2].equals("NA") ? 0.0f : Float.parseFloat(data[2]);
                int quantiteCommandee = Integer.parseInt(data[3]);

                Element element = trouverElementParCode(codeElement);
                Prix prix = new Prix(element, prixAchat, prixVente, quantiteCommandee);
                //gestionnaireFinances.ajouterPrix(prix);
            }
        } catch (IOException e) {
            System.out.println("Erreur lors du chargement du fichier prix.csv : " + e.getMessage());
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

    // ...
}