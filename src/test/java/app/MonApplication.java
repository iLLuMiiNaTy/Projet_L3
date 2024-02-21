package app;

import controleur.ControleurChaines;
import controleur.ControleurCommandes;
import controleur.ControleurStocks;
import javafx.application.Application;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import vue.VueChaines;
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
        primaryStage.setTitle("Stock Master");
        
        Image icon = new Image(getClass().getResourceAsStream("/images/stock.png"));
        primaryStage.getIcons().add(icon); 
        //Ajouter l'icône
        
        Scene scene = new Scene(root, 1300, 850);
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