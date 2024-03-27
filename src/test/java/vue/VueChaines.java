package vue;

import app.ChaineDeProduction;
import app.Element;
import app.GestionnaireProduction;
import controleur.ControleurChaines;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;

public class VueChaines {
	
	private HBox carrousel;
	private ControleurChaines ControlChaine;

	/**
	 * Constructeur
	 * 
	 * @param listeChaine   La liste des chaines à afficher
	 * @param ControlChaine Le controleur des chaines
	 */
	public VueChaines(ObservableList<ChaineDeProduction> listeChaine, ControleurChaines ControlChaine) {
		this.carrousel = new HBox(10); // Espacement de 10 entre chaque élément du carrousel
        carrousel.setPadding(new javafx.geometry.Insets(5)); // Un peu d'espacement autour du carrousel
        this.ControlChaine = ControlChaine;        
        afficherChaines(listeChaine);
	}

	/**
	 * Affiche les chaines de production
	 * 
	 * @param listeChaine La liste des chaines à afficher
	 */
	private void afficherChaines(ObservableList<ChaineDeProduction> listeChaine) {
        for (ChaineDeProduction c : listeChaine) {
            carrousel.getChildren().add(creerVueChaines(c));
        }
    }

	/**
	 * Crée la vue d'une chaine de production
	 * 
	 * @param c La chaine de production à afficher
	 * @return La vue de la chaine de production
	 */
	private Node creerVueChaines(ChaineDeProduction c) {
    	
    	StackPane imageContainer = new StackPane();
    	imageContainer.setPrefSize(100, 100); // Taille désirée du conteneur
    	
    	//Charge l'image depuis une URL
    	ImageView imageView = null;
    	try {
    		Image image = new Image(c.getUrlImage(), true);//true pour charger l'image en arrière plan
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
        
        Label code = new Label("Chaîne de production n°" + c.getCode());
        Label niveauActivation = new Label();
        niveauActivation.textProperty().bind(c.activationProperty().asString());
        
        HBox hbox = new HBox();
        hbox.getChildren().add(new Label("Niveau d'activation: "));
        hbox.getChildren().add(niveauActivation);
        hbox.setAlignment(Pos.CENTER_LEFT);

        
        vueElement.getChildren().addAll(imageContainer, code, hbox);
        
        return vueElement;
    }

	/**
	 * Retourne la vue des chaines
	 * 
	 * @return La vue des chaines
	 */
    public HBox getVue() {
        return carrousel;
    }

}
