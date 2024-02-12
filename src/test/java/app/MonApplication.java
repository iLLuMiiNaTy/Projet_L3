package app;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;

public class MonApplication extends Application{

    public static void main(String[] args) {
    	
    	launch(args);
    	
    	FichierCSV csv = new FichierCSV();
    	GestionnaireFinance GeFi = new GestionnaireFinance();
		MonApplication.affichageCSV(csv, GeFi);
		MonApplication.affichageTransaction(GeFi);
	}
    
    @Override
    public void start(Stage primaryStage) {
        Label label = new Label("Hello, JavaFX!");
        Scene scene = new Scene(label, 400, 300);
        
        primaryStage.setTitle("JavaFX App");
        primaryStage.setScene(scene);
        primaryStage.show();
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
    
    
    
    private static void affichageTransaction(GestionnaireFinance GeFi) {
    	System.out.println("\n##########################");
		System.out.println("AFFICHAGE TRANSACTION");
		System.out.println("##########################");
    	GeFi.afficherVentes();
    }
}