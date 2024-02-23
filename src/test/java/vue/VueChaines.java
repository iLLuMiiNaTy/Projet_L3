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
	
	private VBox vuePrincipale; // Pour organiser verticalement
	private HBox carrousel;
	private ControleurChaines ControlChaine;

	public VueChaines(ObservableList<ChaineDeProduction> listeChaine, ControleurChaines ControlChaine) {
		this.carrousel = new HBox(10); // Espacement de 10 entre chaque élément du carrousel
        carrousel.setPadding(new javafx.geometry.Insets(15)); // Un peu d'espacement autour du carrousel
        this.ControlChaine = ControlChaine;
        
        // Initialisation de la VBox principale
        this.vuePrincipale = new VBox(20); // Espacement de 20 entre les éléments
        vuePrincipale.setAlignment(Pos.CENTER); // Centre les éléments dans la VBox
        
        afficherChaines(listeChaine);
        
        // Ajout du carrousel à la vue principale
        vuePrincipale.getChildren().add(carrousel);
        
        // Création et ajout du bouton 'Lancer la production' à la vue principale
        Button btnLancerProd = new Button("Lancer la production");
        btnLancerProd.setOnAction(e -> {
            
        });
        
        vuePrincipale.getChildren().add(btnLancerProd);
	}
	
	private void afficherChaines(ObservableList<ChaineDeProduction> listeChaine) {
        for (ChaineDeProduction c : listeChaine) {
            carrousel.getChildren().add(creerVueChaines(c));
        }
    }
	
	private Node creerVueChaines(ChaineDeProduction c) {
    	
    	StackPane imageContainer = new StackPane();
    	imageContainer.setPrefSize(280, 200); // Taille désirée du conteneur
    	
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
        Label niveauActivation = new Label("Niveau d'activation: " + c.getActivation());
        
        // Bouton pour ajuster le niveau d'activation
        /*Button btnSimuler = new Button("Lancer la simulation");
        btnSimuler.setOnAction(e -> {
            GestionnaireProduction.simulerProduction();
        });*/
        
        vueElement.getChildren().addAll(imageContainer, code, niveauActivation/*, btnSimuler*/);
        
        return vueElement;
    }
    
    public HBox getVue() {
        return carrousel;
    }

}
