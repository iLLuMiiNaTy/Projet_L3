package app;

import java.util.List;

import controleur.ControleurCommandes;
import controleur.ControleurStocks;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import vue.VueCommandes;
import vue.VueStocks;

public class MonApplication extends Application{

    public static void main(String[] args) {		
		launch(args);
	}
    
    @Override
    public void start(Stage primaryStage){
        FichierCSV csv = new FichierCSV();
        BorderPane root = new BorderPane();
        
        creerScenes(csv, root);
        
        primaryStage.setTitle("Mon Application JavaFX");
        
        Scene scene = new Scene(root, 1920, 1080);
        scene.getStylesheets().add(getClass().getResource("style.css").toExternalForm());
        
        primaryStage.setScene(scene);
        primaryStage.show();
    }
    
    public void creerScenes(FichierCSV csv, BorderPane root) {
    	sceneStock(csv, root);
    	sceneCommande(csv, root);
    }
    
    public void sceneCommande(FichierCSV csv, BorderPane root) {
    	GestionnaireCommande GeCom = new GestionnaireCommande();
        ObservableList<Commande> listeCommande = csv.chargerCommandes(GeCom);
        
        VueCommandes vue = new VueCommandes(listeCommande);
        ControleurCommandes ControlCom = new ControleurCommandes(GeCom, vue);
        root.setCenter(vue.getVue());
    }
    
    public void sceneStock(FichierCSV csv, BorderPane root) {
    	GestionnaireStock GeStock = new GestionnaireStock();
    	ObservableList<Element> listeElement = csv.chargerElements(GeStock);
    	
    	VueStocks vue = new VueStocks(listeElement);
    	ControleurStocks ControlStock = new ControleurStocks(GeStock, vue);
    	root.setTop(vue.getVue());
    }
    
    
    
    
    
    
 /*   
    @Override
    public void start(Stage primaryStage){
    	
    	FichierCSV csv = new FichierCSV();
    	GestionnaireFinance GeFi = new GestionnaireFinance();
    	GestionnaireCommande GeCom = new GestionnaireCommande();
		
        primaryStage.setTitle("Mon Application JavaFX");
        
        TableColumn<Commande, String> colonneNumero = new TableColumn<>("Numéro");
        colonneNumero.setCellValueFactory(cellData -> cellData.getValue().numeroCommandeProperty());
        
        TableColumn<Commande, String> colonneClient = new TableColumn<>("Client");
        colonneClient.setCellValueFactory(cellData -> cellData.getValue().clientProperty());
        
        table.getColumns().addAll(colonneNumero, colonneClient); // ajoutez les autres colonnes ici

        listeCommande = csv.chargerCommandes(GeCom);
        
        table.setItems(listeCommande);
        
        VBox vbox = new VBox(table);
        
        Scene scene = new Scene(vbox);
        primaryStage.setScene(scene);
        primaryStage.show();
    }*/
    
   /* private static void affichageCSV(FichierCSV csv, GestionnaireFinance GeFi) {
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
		csv.afficherCommandes(GeFi); //paramètre mis en place pour des tests sur les transactions
		
        csv.sauvegarderElements();
    }
    
    
    
    private static void affichageTransaction(GestionnaireFinance GeFi) {
    	System.out.println("\n##########################");
		System.out.println("AFFICHAGE TRANSACTION");
		System.out.println("##########################");
    	GeFi.afficherVentes();
    }*/
}