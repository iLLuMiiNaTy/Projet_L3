package app;

import controleur.ControleurChaines;
import controleur.ControleurCommandes;
import controleur.ControleurStocks;
import javafx.application.Application;
import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import vue.VueChaines;
import vue.VueCommandes;
import vue.VueStocks;
/*
public class MonApplication extends Application{

    public static void main(String[] args) {		
		launch(args);
	}
    
    @Override
    public void start(Stage primaryStage){
        FichierCSV csv = new FichierCSV();
        BorderPane root = new BorderPane();
        
     // Créer un ScrollPane et y ajouter le BorderPane comme son contenu
        ScrollPane scrollPane = new ScrollPane();
        scrollPane.setContent(root);
        // Activer les barres de défilement selon le besoin
        scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.AS_NEEDED);
        scrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.AS_NEEDED);
        
        creerScenes(csv, root);
        primaryStage.setTitle("Stock Master");
        
        Image icon = new Image(getClass().getResourceAsStream("/images/stock.png"));
        primaryStage.getIcons().add(icon); 
        //Ajouter l'icône
        
        Scene scene = new Scene(root, 1200, 800);
        scene.getStylesheets().add(getClass().getResource("style.css").toExternalForm());
      
        primaryStage.setScene(scene);
        primaryStage.show();
    }
    
    public void creerScenes(FichierCSV csv, BorderPane root) {
    	GestionnaireStock GeStock = new GestionnaireStock();
    	sceneStock(csv, root, GeStock);
    	sceneCommande(csv, root, GeStock);
    	sceneChaine(csv, root, GeStock);
    }
    
    public void sceneStock(FichierCSV csv, BorderPane root, GestionnaireStock GeStock) {
    	ObservableList<Element> listeElement = csv.chargerElements(GeStock);
    	
    	ControleurStocks ControlStock = new ControleurStocks(GeStock);
    	VueStocks vue = new VueStocks(listeElement, ControlStock);
    	root.setTop(vue.getVue());
    }
    
    
    public void sceneCommande(FichierCSV csv, BorderPane root, GestionnaireStock GeStock) {
    	GestionnaireCommande GeCom = new GestionnaireCommande(GeStock);
        ObservableList<Commande> listeCommande = csv.chargerCommandes(GeCom);
        
        ControleurCommandes ControlCom = new ControleurCommandes(GeCom);
        VueCommandes vue = new VueCommandes(listeCommande, ControlCom);
        root.setCenter(vue.getVue());
    }
    
    private void sceneChaine(FichierCSV csv, BorderPane root, GestionnaireStock GeStock) {
		GestionnaireProduction GeProd = new GestionnaireProduction(GeStock);
		ObservableList<ChaineDeProduction> listeChaine = csv.chargerChaines(GeProd);
		
		ControleurChaines ControlChaine = new ControleurChaines(GeProd);
		VueChaines vue = new VueChaines(listeChaine, ControlChaine);
		root.setBottom(vue.getVue());
    }
}
*/

public class MonApplication extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        FichierCSV csv = new FichierCSV();
        GridPane root = new GridPane();
        
        // Configuration du GridPane
        root.setHgap(10); // Espace horizontal entre les cellules
        root.setVgap(10); // Espace vertical entre les cellules
        
        creerScenes(csv, root);
        primaryStage.setTitle("Stock Master");
        
        Image icon = new Image(getClass().getResourceAsStream("/images/stock.png"));
        primaryStage.getIcons().add(icon); // Ajouter l'icône

        // Utiliser un ScrollPane pour permettre le défilement si nécessaire
        ScrollPane scrollPane = new ScrollPane();
        scrollPane.setContent(root);
        scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.AS_NEEDED);
        scrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.AS_NEEDED);

        Scene scene = new Scene(scrollPane, 1200, 700);
        scene.getStylesheets().add(getClass().getResource("style.css").toExternalForm());

        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public void creerScenes(FichierCSV csv, GridPane root) {
        GestionnaireStock GeStock = new GestionnaireStock();
        sceneStock(csv, root, GeStock);
        sceneCommande(csv, root, GeStock);
        sceneChaine(csv, root, GeStock);
    }

    public void sceneStock(FichierCSV csv, GridPane root, GestionnaireStock GeStock) {
        ObservableList<Element> listeElement = csv.chargerElements(GeStock);
        ControleurStocks ControlStock = new ControleurStocks(GeStock);
        VueStocks vue = new VueStocks(listeElement, ControlStock);
        root.add(vue.getVue(), 0, 0); // Position dans le GridPane
    }

    public void sceneCommande(FichierCSV csv, GridPane root, GestionnaireStock GeStock) {
        GestionnaireCommande GeCom = new GestionnaireCommande(GeStock);
        ObservableList<Commande> listeCommande = csv.chargerCommandes(GeCom);
        ControleurCommandes ControlCom = new ControleurCommandes(GeCom);
        VueCommandes vue = new VueCommandes(listeCommande, ControlCom);
        root.add(vue.getVue(), 0, 1); // Position suivante dans le GridPane
    }

    private void sceneChaine(FichierCSV csv, GridPane root, GestionnaireStock GeStock) {
        GestionnaireProduction GeProd = new GestionnaireProduction(GeStock);
        ObservableList<ChaineDeProduction> listeChaine = csv.chargerChaines(GeProd);
        ControleurChaines ControlChaine = new ControleurChaines(GeProd);
        VueChaines vue = new VueChaines(listeChaine, ControlChaine);
        root.add(vue.getVue(), 0, 2); // Position suivante dans le GridPane
    }
}