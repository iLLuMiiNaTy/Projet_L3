package vue;

import javax.swing.JOptionPane;

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

/**
 * Classe VueStocks
 * 
 * Cette classe permet de créer la vue des stocks
 */
public class VueStocks{
    private HBox carrousel;
    private static ControleurStocks  ControlStocks;

	/**
	 * Constructeur de la vue des stocks
	 * 
	 * @param listeElement     Liste des éléments à afficher
	 * @param ControlStocks Controleur des stocks
	 */
    public VueStocks(ObservableList<Element> listeElement, ControleurStocks ControlStocks) {
    	this.ControlStocks = ControlStocks;
        this.carrousel = new HBox(10); // Espacement de 10 entre chaque élément du carrousel
        carrousel.setPadding(new javafx.geometry.Insets(15)); // Un peu d'espacement autour du carrousel
        afficherElements(listeElement);
    }

	/**
	 * Affiche les éléments dans le carrousel
	 * 
	 * @param listeElement Liste des éléments à afficher
	 */
    private void afficherElements(ObservableList<Element> listeElement) {
        for (Element e : listeElement) {
            carrousel.getChildren().add(creerVueElement(e));
        }
    }

	/**
	 * Crée la vue d'un élément
	 * 
	 * @param element Element à afficher
	 * @return Node
	 */
    private Node creerVueElement(Element element) {
        StackPane imageContainer = new StackPane();
        imageContainer.setPrefSize(90, 90); // Taille désirée du conteneur

        //Charge l'image depuis une URL
        ImageView imageView = null;
        try {
            Image image = new Image(element.getUrlImage(), true);//true pour charger l'image en arrière-plan
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

        // Création des labels
        Label nom = new Label(element.getNom());
        Label quantite = new Label("Quantité: " + element.getQuantite() + " " + element.getUniteDeMesure());
        quantite.textProperty().bind(element.quantiteProperty().asString());
        Label uMesure = new Label(element.getUniteDeMesure());
        
        // Création de l'HBox pour aligner les labels
        HBox hbox = new HBox();
        hbox.getChildren().add(new Label("Quantité: "));
        hbox.getChildren().add(quantite);
        hbox.getChildren().add(new Label(" "));
        hbox.getChildren().add(uMesure);
        hbox.setAlignment(Pos.CENTER_LEFT);
        
        // Création des labels
        Label prixAchat = new Label("Prix achat: " + element.getPrixAchat() + " €");
        Label prixVente = new Label("Prix vente: " + element.getPrixVente() + " €");
        TextField quantiteTemporaireText = new TextField("0");
        quantiteTemporaireText.setMaxWidth(65);
        quantiteTemporaireText.textProperty().bind(element.quantiteTemporaireProperty().asString().concat(" ").concat(element.getUniteDeMesure()));

        // Boutons "+" et "-" pour augmenter/diminuer la quantité
        Button buttonPlus = new Button("+");
        buttonPlus.getStyleClass().add("button-plus-minus");
        buttonPlus.setOnAction(event -> {
        	element.setQuantiteTemporaire(element.getQuantiteTemporaire() + 1);//Modifie la quantité temporaire
        	});
        
        Button buttonMoins = new Button("-");
        buttonMoins.getStyleClass().add("button-plus-minus");
        buttonMoins.setOnAction(event -> {
            //Pour afficher dynamiquement les changements de quantités
        	if (element.getQuantiteTemporaire() > 0) {//Empèche une diminution en dessous de 0
        		element.setQuantiteTemporaire(element.getQuantiteTemporaire() - 1);	
        	}
            
        });
        
        // Bouton pour valider l'achat
        Button validerAchat = new Button("Valider Achat");
        validerAchat.setOnAction(event ->{
        	//Vérifie si la capacité du stockage est suffisante pour l'achat de cet élément
			if (ControlStocks.verifierQuantiteDisponible(element, element.getQuantiteTemporaire())) {// Vérifie si le stockage total est suffisant pour l'achat
				ControlStocks.augmenterQuantite(element, element.getQuantiteTemporaire());//Utilise la méthode du ControleurStocks pour ajouter la quantiteTemporaire au stock
				element.setQuantiteTemporaire(0);
			} else {//Si le stockage total est insuffisant
				JOptionPane.showMessageDialog(null,
					      "** Erreur : Stockage insuffisant pour l'élément " + element.getNom() + " **",
					      "Alerte", JOptionPane.ERROR_MESSAGE);
			}
        });
       

        // Création de l'HBox pour aligner les boutons
        HBox buttonsBox = new HBox(9, buttonPlus, quantiteTemporaireText, buttonMoins); // 10 est l'espacement que vous pouvez ajuster selon vos besoins
        buttonsBox.setAlignment(Pos.CENTER);

        vueElement.getChildren().addAll(imageContainer, nom, hbox, prixAchat, prixVente, buttonsBox, validerAchat);

        return vueElement;
    }

	/**
	 * Retourne la vue
	 * 
	 * @return HBox
	 */
    public HBox getVue() {
        return carrousel;
    }
}