package vue;

import app.Element;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;

public class VueStocks {
    private HBox carrousel;
    
    public VueStocks(ObservableList<Element> elements) {
        this.carrousel = new HBox(10); // Espacement de 10 entre chaque élément du carrousel
        carrousel.setPadding(new javafx.geometry.Insets(15)); // Un peu d'espacement autour du carrousel
        afficherElements(elements);
    }
    
    private void afficherElements(ObservableList<Element> elements) {
        for (Element e : elements) {
            carrousel.getChildren().add(creerVueElement(e));
        }
    }
    
    private Node creerVueElement(Element e) {
    	
    	StackPane imageContainer = new StackPane();
    	imageContainer.setPrefSize(280, 200); // Taille désirée du conteneur
    	
    	//Charge l'image depuis une URL
    	ImageView imageView = null;
    	try {
    		Image image = new Image(e.getUrlImage(), true);//true pour charger l'image en arrière plan
    		imageView = new ImageView(image);
    		imageView.setPreserveRatio(true);
    		StackPane.setAlignment(imageView, Pos.CENTER); //Pour centrer l'image dans son conteneur StackPane
    	} catch (Exception ex) {
    		imageView = new ImageView();
    	}
    	
    	imageView.fitHeightProperty().bind(imageContainer.heightProperty()); // L'image s'adapte à la hauteur du conteneur
    	imageView.fitWidthProperty().bind(imageContainer.widthProperty()); // L'image s'adapte à la largeur du conteneur

    	imageContainer.getChildren().add(imageView);
    	
    	VBox vueElement = new VBox(10); // Espacement vertical entre les éléments de la VBox
        vueElement.setPadding(new Insets(15)); // Définit le padding autour des éléments dans la VBox
        vueElement.getStyleClass().add("card");
        
        Label nom = new Label(e.getNom());
        Label quantite = new Label("Quantité: " + e.getQuantite() + " " + e.getUniteDeMesure());
        Label prixVente = new Label("Prix vente: " + e.getPrixVente() + " €");
        
        vueElement.getChildren().addAll(imageContainer, nom, quantite, prixVente);
        
        return vueElement;
    }
    
    public HBox getVue() {
        return carrousel;
    }
}




/*
public class VueCommandes {
    private TableView<Commande> table = new TableView<>();
    private ObservableList<Commande> listeCommande;

    public VueCommandes(ObservableList<Commande> listeCommande) {
        this.listeCommande = listeCommande;
        creerTableauCommandes();
    }

    private void creerTableauCommandes() {
    	 // Configuration du TableView et ajout des colonnes
        TableColumn<Commande, String> colonneNumero = new TableColumn<>("Numéro");
        colonneNumero.setCellValueFactory(cellData -> cellData.getValue().numeroCommandeProperty());

        TableColumn<Commande, String> colonneClient = new TableColumn<>("Client");
        colonneClient.setCellValueFactory(cellData -> cellData.getValue().clientProperty());

        TableColumn<Commande, String> colonneCode = new TableColumn<>("Code Produit");
        colonneCode.setCellValueFactory(cellData -> cellData.getValue().codeProduitProperty());
        
        TableColumn<Commande, String> colonneProduit = new TableColumn<>("Produit");
        colonneProduit.setCellValueFactory(cellData -> cellData.getValue().ProduitProperty());
        
        TableColumn<Commande, String> colonneQuantite = new TableColumn<>("Quantité");
        colonneQuantite.setCellValueFactory(cellData -> cellData.getValue().QuantiteProperty());

        table.getColumns().addAll(colonneNumero, colonneClient, colonneCode, colonneProduit, colonneQuantite); // Ajoutez les autres colonnes ici

        table.setItems(listeCommande); // Liaison des données
    }

    public VBox getVue() {
        return new VBox(table);
    }
}*/