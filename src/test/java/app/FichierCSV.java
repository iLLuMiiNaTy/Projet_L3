package app;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 * Classe FichierCSV
 * 
 * Cette classe permet de charger les éléments, les chaînes de production, les
 * commandes et les stockages depuis les fichiers CSV correspondants.
 * 
 * Elle permet également de sauvegarder les éléments dans le fichier
 * resultatFinancier.csv
 * 
 */
public class FichierCSV {

//#########################################################################################################
										//ELEMENTS
//#########################################################################################################
	
	/**
     * Charge les éléments depuis le fichier elements.csv et les ajoute à la liste d'éléments
     * 
     * @param GeStock Gestionnaire de stock
     * @return La liste d'éléments
     */
	public ObservableList<Element> chargerElements(GestionnaireStock GeStock) {
		// Création de la liste d'éléments
		ObservableList<Element> listeElement = FXCollections.observableArrayList();
		
		// Lecture du fichier elements.csv
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
                String codeStockage = data[7];

                // Création de l'objet Element
                Element element = new Element(code, nom, quantite, uniteDeMesure, prixAchat, prixVente, urlImage, codeStockage);
                listeElement.add(element);
                GeStock.ajouterStock(element, quantite);
            }
        } catch (IOException e) {
            System.out.println("Erreur lors du chargement du fichier elements.csv : " + e.getMessage());
        }
        return listeElement;
    }
	
	/**
	 * Sauvegarde les éléments dans le fichier resultatFinancier.csv
	 */
	public static void sauvegarderElements() {
        String elementsFilePath = "src/test/resources/resultatFinancier.csv";

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(elementsFilePath))) {
            writer.write("Total des achats :"); // Première ligne du fichier CSV
            
            writer.newLine(); // Deuxième ligne du fichier CSV
            int totalAchat = GestionnaireFinance.getTotalAchat();
            String line2 = String.valueOf(totalAchat);
            writer.write(line2);
            
            writer.newLine(); // Troisième ligne
            writer.write("Total des ventes :");
            
            writer.newLine(); // 4ème ligne
            int totalVente = GestionnaireFinance.getTotalVente();
            String line4 = String.valueOf(totalVente);
            writer.write(line4);
            
            writer.newLine(); // 5ème ligne
            writer.write("Résultat financier :");
            
            writer.newLine(); // 6ème ligne
            int resFinancier = GestionnaireFinance.calculerResultatFinancier();
            String line6 = String.valueOf(resFinancier);
            writer.write(line6);
            
            writer.newLine(); // 7ème ligne
            writer.write("Liste des transactions :");
            
            writer.newLine(); // 8ème ligne
            writer.write("|------------------------------------------------|");
            writer.newLine();
            writer.write("| Type  |    Element         | Quantite | Valeur |");
            writer.newLine();

            // Parcours de la liste des transactions
            for (Transaction t : GestionnaireFinance.getListeTransaction()) {
            	String line = "| %5s | %18s | %8d | %5d€ |";
                //String line = "| "+ t.getType() + " | " + t.getNomElement() + " | " + t.getQuantite() + " | " + t.getPrix() + "€";
            	line = String.format(line, t.getType(), t.getNomElement(), t.getQuantite(), t.getPrix());
                writer.write("|------------------------------------------------|");
                writer.newLine();
                writer.write(line);
                writer.newLine();
            }
            writer.write("|------------------------------------------------|");

            writer.flush();
        } catch (IOException e) {
            System.out.println("Erreur lors de la sauvegarde des éléments : " + e.getMessage());
        }
    }

//#########################################################################################################
									//CHAINE DE PRODUCTION
//#########################################################################################################
	
									/**
									 * Charge les chaînes de production depuis le fichier chaines.csv et les ajoute
									 * à la liste de chaînes
									 * 
									 * @param GeProd Gestionnaire de production
									 * @return La liste de chaînes
									 */
	public ObservableList<ChaineDeProduction> chargerChaines(GestionnaireProduction GeProd) {
		// Création de la liste de chaînes
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

                // Création de la liste des éléments d'entrée
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

                // Création de la liste des éléments de sortie
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

                // Création de l'objet ChaineDeProduction
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
	
										/**
										 * Charge les commandes depuis le fichier commandes.csv et les ajoute à la liste
										 * de commandes
										 * 
										 * @param GeCom Gestionnaire de commande
										 * @return La liste de commandes
										 */
	public ObservableList<Commande> chargerCommandes(GestionnaireCommande GeCom) {
		// Création de la liste de commandes
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

                // Création de l'objet Commande
                Commande commande = new Commande(numeroCommande, client, codeProduit, produit, quantite);
                listeCommande.add(commande);
				GeCom.ajouterCommande(commande);
            }
        } catch (IOException e) {
            System.out.println("Erreur lors du chargement du fichier commandes.csv : " + e.getMessage());
        }
		return listeCommande;
    }

//#########################################################################################################
													//Stockage
//#########################################################################################################

													/**
													 * Charge les stockages depuis le fichier stockages.csv et les
													 * ajoute à la liste de stockages
													 * 
													 * @param GeStock Gestionnaire de stock
													 * @return La liste de stockages
													 */
	public ObservableList<Stockage> chargerStockages(GestionnaireStock GeStock) {
		// Création de la liste de stockages
		ObservableList<Stockage> listeStockage = FXCollections.observableArrayList();
		
        try (BufferedReader reader = new BufferedReader(new FileReader("src/test/resources/stockages.csv"))) {
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
                int capacite = Integer.parseInt(data[2]);
                int quantite = Integer.parseInt(data[3]);

                // Création de l'objet Stockage
                Stockage stockage = new Stockage(code, nom, capacite, quantite);
                listeStockage.add(stockage);
                GeStock.ajouterStockage(stockage);
            }
        } catch (IOException e) {
            System.out.println("Erreur lors du chargement du fichier elements.csv : " + e.getMessage());
        }
        return listeStockage;
    }



}
