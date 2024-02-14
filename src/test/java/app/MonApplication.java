package app;

import java.util.List;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class MonApplication extends Application{
	
	TableView<Commande> table = new TableView<>();
	TableView<Commande> table1 = new TableView<>();
    ObservableList<Commande> listeCommande = FXCollections.observableArrayList();

    public static void main(String[] args) {		
		launch(args);
	}
    
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
		csv.afficherCommandes(GeFi); //paramètre mis en place pour des tests sur les transactions
		
        csv.sauvegarderElements();
    }
    
    
    
    private static void affichageTransaction(GestionnaireFinance GeFi) {
    	System.out.println("\n##########################");
		System.out.println("AFFICHAGE TRANSACTION");
		System.out.println("##########################");
    	GeFi.afficherVentes();
    }
}