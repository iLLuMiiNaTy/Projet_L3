package app;

import java.util.ArrayList;

import javafx.application.Application;
import javafx.geometry.Orientation;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.Separator;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class MonApplication extends Application{

    public static void main(String[] args) {
    	
    	launch(args);
    	
    	FichierCSV csv = new FichierCSV();
    	GestionnaireFinance GeFi = new GestionnaireFinance();
		MonApplication.affichageCSV(csv, GeFi);
		MonApplication.affichageTransaction(GeFi);
	}
    
  
    
    private static void affichageCSV(FichierCSV csv, GestionnaireFinance GeFi) {
		csv.chargerDonnees();
		
		System.out.println("##########################");
		System.out.println("AFFICHAGE ELEMENTS");
		System.out.println("##########################\n");
		csv.afficherElements();

		System.out.println("\n##########################");
		System.out.println("AFFICHAGE CHAINES");
		System.out.println("##########################");
		csv.afficherChaines();

		System.out.println("\n##########################");
		System.out.println("AFFICHAGE COMMANDES");
		System.out.println("##########################");
		csv.afficherCommandes(/*GeFi*/); //paramètre mis en place pour des tests sur les transactions
		
        csv.sauvegarderElements();
    }
    
   @Override
    public void start(Stage primaryStage )throws Exception {
	    FichierCSV csv = new FichierCSV();
	    csv.chargerDonnees(); // Charger les données à partir du fichier CSV

	    ArrayList<Element> elements = csv.afficherElements(); // Récupérer les éléments

	    VBox vbox = new VBox();

	    for (Element element : elements) {
	    	
	    	 Image image = new Image("file:" + element.getImage());
	         
	         // Calcul de la hauteur en conservant le rapport hauteur/largeur de l'image d'origine
	         double aspectRatio = image.getWidth() / image.getHeight();
	         double imageHeight = 100; // Vous pouvez ajuster cette valeur selon vos besoins
	         double imageWidth = imageHeight * aspectRatio;
	         
	         ImageView imageView = new ImageView(image);
	         imageView.setFitWidth(imageWidth);
	         imageView.setFitHeight(imageHeight);
	         
	        Label codeLabel = new Label("Code : " + element.getCode());
	        Label nomLabel = new Label("Nom : " + element.getNom());
	        Label quantiteLabel = new Label("Quantité : " + element.getQuantite() + " " + element.getUniteDeMesure());
	        Label prixAchatLabel = new Label("Prix d'achat : " + element.getPrixAchat() + " €");
	        Label prixVenteLabel = new Label("Prix de vente : " + element.getPrixVente() + " €");



	        vbox.getChildren().addAll(codeLabel, nomLabel, quantiteLabel, prixAchatLabel, prixVenteLabel);
	        
	        // Ajout d'un séparateur entre chaque élément
	        vbox.getChildren().addAll(new Separator(Orientation.HORIZONTAL));
	    }
	    
	    // Création de la scène avec la VBox comme racine
	    Scene scene = new Scene(vbox, 600, 600);

	    // Configuration de la scène et affichage de la fenêtre
	    primaryStage.setScene(scene);
	    primaryStage.setTitle("Affichage des éléments");
	    primaryStage.show();
	}
    
    private static void affichageTransaction(GestionnaireFinance GeFi) {
    	System.out.println("\n##########################");
		System.out.println("AFFICHAGE TRANSACTION");
		System.out.println("##########################");
    	GeFi.afficherVentes();
    }



	
	




}