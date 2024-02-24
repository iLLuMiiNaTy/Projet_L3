package vue;

import app.Element;
import app.GestionnaireStock;
import controleur.ControleurStocks;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;

public class VueStocks{
    private HBox carrousel;
    private static ControleurStocks  ControlStocks;
    
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
        imageContainer.setPrefSize(90, 90); // Taille désirée du conteneur

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
        quantite.textProperty().bind(e.quantiteProperty().asString());
        Label prixVente = new Label("Prix vente: " + e.getPrixVente() + " €");
        TextField quantiteTemporaireText = new TextField("0");
        quantiteTemporaireText.setMaxWidth(65);
        quantiteTemporaireText.textProperty().bind(e.quantiteTemporaireProperty().asString().concat(" ").concat(e.getUniteDeMesure()));

        // Boutons "+" et "-" pour augmenter/diminuer la quantité
        Button buttonPlus = new Button("+");
        buttonPlus.getStyleClass().add("button-plus-minus");
        buttonPlus.setOnAction(event -> {
        	e.setQuantiteTemporaire(e.getQuantiteTemporaire() + 1);//Modifie la quantité temporaire
        	});
        
        Button buttonMoins = new Button("-");
        buttonMoins.getStyleClass().add("button-plus-minus");
        buttonMoins.setOnAction(event -> {
            //Pour afficher dynamiquement les changements de quantités
        	if (e.getQuantiteTemporaire() > 0) {//Empèche une diminution en dessous de 0
        		e.setQuantiteTemporaire(e.getQuantiteTemporaire() - 1);	
        	}
            
        });
        
        Button validerAchat = new Button("Valider Achat");
        validerAchat.setOnAction(event ->{
        	ControlStocks.augmenterQuantite(e, e.getQuantiteTemporaire());//Utilise la méthode du ControleurStocks pour ajouter la quantiteTemporaire au stock
        	e.setQuantiteTemporaire(0);
        	//quantite.setText("Quantité: " + e.getQuantite() + " " + e.getUniteDeMesure());
        });
       

        //ça c'est pour l'emplacement du button
        HBox buttonsBox = new HBox(10, buttonPlus, quantiteTemporaireText, buttonMoins); // 10 est l'espacement que vous pouvez ajuster selon vos besoins
        buttonsBox.setAlignment(Pos.CENTER);

        vueElement.getChildren().addAll(imageContainer, nom, quantite, prixVente, buttonsBox, validerAchat);

        return vueElement;
    }
    
    public HBox getVue() {
        return carrousel;
    }
}