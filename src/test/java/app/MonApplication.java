package app;

import controleur.ControleurChaines;
import controleur.ControleurCommandes;
import controleur.ControleurStocks;
import javafx.application.Application;
import javafx.collections.ObservableList;
import javafx.geometry.HPos;
import javafx.geometry.Pos;
import javafx.geometry.VPos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import vue.VueChaines;
import vue.VueCommandes;
import vue.VueStocks;

/**
 * Classe MonApplication permettant de lancer l'application
 * 
 */
public class MonApplication extends Application {

    public static void main(String[] args) {
        launch(args);
    }
    /**
     * Méthode start de la classe Application
     * @param primaryStage
     */
    @Override
    public void start(Stage primaryStage) {
        FichierCSV csv = new FichierCSV();
        GridPane root = new GridPane();
        
        // Configuration du GridPane
        root.setHgap(10); // Espace horizontal entre les cellules
        root.setVgap(10); // Espace vertical entre les cellules
        
        creerScenes(csv, root);
        primaryStage.setTitle("Stock Master");
        primaryStage.setFullScreen(true);
        
        Image icon = new Image(getClass().getResourceAsStream("/images/stock.png"));
        primaryStage.getIcons().add(icon); // Ajouter l'icône

        // Utiliser un ScrollPane pour permettre le défilement si nécessaire
        ScrollPane scrollPane = new ScrollPane();
        scrollPane.setContent(root);
        scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.AS_NEEDED);
        scrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.AS_NEEDED);

        Scene scene = new Scene(scrollPane, 1260, 700);
        scene.getStylesheets().add(getClass().getResource("style.css").toExternalForm());

        primaryStage.setScene(scene);
        primaryStage.show();
    }

	/**
	 * Méthode permettant de créer les différentes scènes de l'application
	 * 
	 * @param csv
	 * @param root
	 */
    public void creerScenes(FichierCSV csv, GridPane root) {
        GestionnaireStock GeStock = new GestionnaireStock();
        sceneStock(csv, root, GeStock);
        sceneCommande(csv, root, GeStock);
        sceneChaine(csv, root, GeStock);
    }

	/**
	 * Méthode permettant de créer la scène de gestion des stocks
	 * 
	 * @param csv
	 * @param root
	 * @param GeStock
	 */
    public void sceneStock(FichierCSV csv, GridPane root, GestionnaireStock GeStock) {
        ObservableList<Element> listeElement = csv.chargerElements(GeStock);
        ObservableList<Stockage> listeStockage = csv.chargerStockages(GeStock);
        ControleurStocks ControlStock = new ControleurStocks(GeStock);
        VueStocks vue = new VueStocks(listeElement, ControlStock);
        root.add(vue.getVue(), 0, 0); // Position dans le GridPane
        
     // Création et ajout du bouton 'Lancer la production'
        Button btnLancerProd = new Button("Lancer la production");
        btnLancerProd.setOnAction(e -> {
        	GestionnaireStock.actualiserStock();
        });
        
        Button btnExport = new Button("Exporter résultat financier");
        btnExport.setOnAction(e -> {
        	FichierCSV.sauvegarderElements();
        });
        
        HBox hboxButtonProd = new HBox();
        hboxButtonProd.setAlignment(Pos.TOP_CENTER);
        hboxButtonProd.getChildren().add(btnLancerProd);
        root.add(hboxButtonProd, 0, 3);
        
        HBox hboxButtonExport = new HBox();
        hboxButtonExport.setAlignment(Pos.TOP_CENTER);
        hboxButtonExport.getChildren().add(btnExport);
        root.add(hboxButtonExport, 0, 4);
    }

	/**
	 * Méthode permettant de créer la scène de gestion des commandes
	 * 
	 * @param csv
	 * @param root
	 * @param GeStock
	 */
    public void sceneCommande(FichierCSV csv, GridPane root, GestionnaireStock GeStock) {
        GestionnaireCommande GeCom = new GestionnaireCommande(GeStock);
        ObservableList<Commande> listeCommande = csv.chargerCommandes(GeCom);
        ControleurCommandes ControlCom = new ControleurCommandes(GeCom);
        VueCommandes vue = new VueCommandes(listeCommande, ControlCom);
        HBox hbox = new HBox();
        hbox.setAlignment(Pos.CENTER);
        hbox.getChildren().add(vue.getVue());
        root.add(hbox, 0, 1);
    }

	/**
	 * Méthode permettant de créer la scène de gestion des chaines de production
	 * 
	 * @param csv
	 * @param root
	 * @param GeStock
	 */
    private void sceneChaine(FichierCSV csv, GridPane root, GestionnaireStock GeStock) {
        GestionnaireProduction GeProd = new GestionnaireProduction(GeStock);
        ObservableList<ChaineDeProduction> listeChaine = csv.chargerChaines(GeProd);        
        ControleurChaines ControlChaine = new ControleurChaines(GeProd);
        VueChaines vue = new VueChaines(listeChaine, ControlChaine);
        HBox hbox = new HBox();
        hbox.setAlignment(Pos.CENTER);
        hbox.getChildren().add(vue.getVue());
        root.add(hbox, 0, 2);
    }
}