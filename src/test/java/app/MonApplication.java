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
    }*/
}