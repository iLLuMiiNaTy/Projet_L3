package app;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class FichierCSV {

//#########################################################################################################
										//ELEMENTS
//#########################################################################################################

	public ObservableList<Element> chargerElements(GestionnaireStock GeStock) {
		
		ObservableList<Element> listeElement = FXCollections.observableArrayList();
		
        try (BufferedReader reader = new BufferedReader(new FileReader("src/test/resources/elements.csv"))) {
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
                float quantite = Float.parseFloat(data[2]);
                String uniteDeMesure = data[3];
                String prixAchat = data[4];
                String prixVente = data[5];
                String urlImage = data[6];

                Element element = new Element(code, nom, quantite, uniteDeMesure, prixAchat, prixVente, urlImage);
                listeElement.add(element);
                GeStock.ajouterStock(element, quantite);
            }
        } catch (IOException e) {
            System.out.println("Erreur lors du chargement du fichier elements.csv : " + e.getMessage());
        }
        return listeElement;
    }

	public void sauvegarderElements() {
        String elementsFilePath = "src/test/resources/elementsExport.csv";

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(elementsFilePath))) {
            writer.write("code;nom;quantite;uniteDeMesure"); // Première ligne du fichier CSV

            writer.newLine();

            for (Element element : GestionnaireStock.getListeElement()) {
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
	
	public ObservableList<ChaineDeProduction> chargerChaines(GestionnaireProduction GeProd) {
		
		ObservableList<ChaineDeProduction> listeChaine = FXCollections.observableArrayList();

        try (BufferedReader reader = new BufferedReader(new FileReader("src/test/resources/chaines.csv"))) {
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
                    Float quantite = Float.parseFloat(elementInfo[1]);

                    Element element = GestionnaireStock.trouverElementParCode(elementCode);
                    elementsEntree.put(element, quantite);
                }

                HashMap<Element, Float> elementsSortie = new HashMap<>();
                String[] elementsSortieData = data[3].split(",");
                for (String elementData : elementsSortieData) {
                    elementData = elementData.replaceAll("[()\\s]+", "");
                    String[] elementInfo = elementData.split(":");
                    String elementCode = elementInfo[0];
                    float quantite = Float.parseFloat(elementInfo[1]);

                    Element element = GestionnaireStock.trouverElementParCode(elementCode);
                    elementsSortie.put(element, quantite);
                }
                String urlImage = data[4];

                ChaineDeProduction chaine = new ChaineDeProduction(code, nom, elementsEntree, elementsSortie, urlImage);
                listeChaine.add(chaine);
                GeProd.ajouterChaine(chaine);
            }
        } catch (IOException e) {
            System.out.println("Erreur lors du chargement du fichier chaines.csv : " + e.getMessage());
        }
        return listeChaine;
    }

//#########################################################################################################
										//COMMANDES
//#########################################################################################################
	
	public ObservableList<Commande> chargerCommandes(GestionnaireCommande GeCom) {
		
		ObservableList<Commande> listeCommande = FXCollections.observableArrayList();

        try (BufferedReader reader = new BufferedReader(new FileReader("src/test/resources/commandes.csv"))) {
            String line;
            boolean firstLine = true; // Variable pour suivre la première ligne
            while ((line = reader.readLine()) != null) {
                if (firstLine) {
                    firstLine = false;
                    continue; // Ignorer la première ligne
                }

                // Traitement des lignes suivantes
                String[] data = line.split(";");
                String numeroCommande = data[0];
                String client = data[1];
                String codeProduit = data[2];
                String produit = data[3];
                String quantiteString = data[4]; // Conversion de la quantité en entier
                int quantite = Integer.parseInt(quantiteString);

                Commande commande = new Commande(numeroCommande, client, codeProduit, produit, quantite);
                listeCommande.add(commande);
				GeCom.ajouterCommande(commande);
            }
        } catch (IOException e) {
            System.out.println("Erreur lors du chargement du fichier commandes.csv : " + e.getMessage());
        }
        GeCom.simulerCommande();
		return listeCommande;
    }




}
