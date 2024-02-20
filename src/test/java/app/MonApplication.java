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
        
        Scene scene = new Scene(root, 750, 750);
        scene.getStylesheets().add(getClass().getResource("style.css").toExternalForm());
      
        
        // Full Screen
     	//============
        primaryStage.setFullScreen(true); 
        //primaryStage.setFullScreenExitHint("You can't escape unless you press q");
        //primaryStage.setFullScreenExitKeyCombination(KeyCombination.valueOf("q"));
      
        primaryStage.setScene(scene);
        primaryStage.setFullScreen(true);
        primaryStage.show();
    }
    
    public void creerScenes(FichierCSV csv, BorderPane root) {
    	sceneStock(csv, root);
    	sceneCommande(csv, root);
    	sceneChaine(csv, root);
    }
    
    public void sceneStock(FichierCSV csv, BorderPane root) {
    	GestionnaireStock GeStock = new GestionnaireStock();
    	ObservableList<Element> listeElement = csv.chargerElements(GeStock);
    	
    	VueStocks vue = new VueStocks(listeElement);
    	ControleurStocks ControlStock = new ControleurStocks(GeStock, vue);
    	root.setTop(vue.getVue());
    }
    
    
    public void sceneCommande(FichierCSV csv, BorderPane root) {
    	GestionnaireCommande GeCom = new GestionnaireCommande();
        ObservableList<Commande> listeCommande = csv.chargerCommandes(GeCom);
        
        VueCommandes vue = new VueCommandes(listeCommande);
        ControleurCommandes ControlCom = new ControleurCommandes(GeCom, vue);
        root.setCenter(vue.getVue());
    }
    
    private void sceneChaine(FichierCSV csv, BorderPane root) {
		GestionnaireProduction GeProd = new GestionnaireProduction();
		ObservableList<ChaineDeProduction> listeChaine = csv.chargerChaines(GeProd);
		
		VueChaines vue = new VueChaines(listeChaine);
		ControleurChaines ControlChaine = new ControleurChaines(GeProd, vue);
		root.setBottom(vue.getVue());
		
    }
}