package vue;

import java.util.List;

import app.Element;
import controleur.ControleurStocks;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;

public class VueStocks {
    private HBox carrousel;
    private ControleurStocks  ControlStocks;
    
    public VueStocks(ObservableList<Element> elements, ControleurStocks ControlStocks) {
    	this.ControlStocks = ControlStocks;
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
            Image image = new Image(e.getUrlImage(), true);//true pour charger l'image en arrière-plan
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

        // Boutons "+" et "-" pour augmenter/diminuer la quantité
        Button buttonPlus = new Button("+");
        buttonPlus.getStyleClass().add("button-plus-minus");
        buttonPlus.setOnAction(event -> {
            ControlStocks.augmenterQuantite(e);
            quantite.setText("Quantité: " + e.getQuantite() + " " + e.getUniteDeMesure());
        });
        
        Button buttonMoins = new Button("-");
        buttonMoins.getStyleClass().add("button-plus-minus");
        buttonMoins.setOnAction(event -> {
            ControlStocks.diminuerQuantite(e);
            quantite.setText("Quantité: " + e.getQuantite() + " " + e.getUniteDeMesure());
            
        });
       

        //ça c'est pour l'emplacement du button
        HBox buttonsBox = new HBox(10, buttonPlus, buttonMoins); // 10 est l'espacement que vous pouvez ajuster selon vos besoins
        buttonsBox.setAlignment(Pos.CENTER);

        vueElement.getChildren().addAll(imageContainer, nom, quantite, prixVente, buttonsBox);

        return vueElement;
    }
    
    public HBox getVue() {
        return carrousel;
    }
}