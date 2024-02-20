package vue;

import app.Commande;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Accordion;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TitledPane;
import javafx.scene.layout.VBox;

public class VueCommandes {
    //private TableView<Commande> table = new TableView<>();
    private Accordion accordion = new Accordion();
    private ObservableList<Commande> listeCommande;

    public VueCommandes(ObservableList<Commande> listeCommande) {
        this.listeCommande = listeCommande;
        creerVueCommandes();
    }

    private void creerVueCommandes() {
    	// Pour chaque commande, créez un TitledPane
        for (Commande commande : listeCommande) {
            VBox contenuCommande = new VBox();
            
         // Pour chaque détail de la commande, ajoutez-le au VBox
            contenuCommande.getChildren().add(new Label("Numéro commande: " + commande.getNumeroCommande()));
            contenuCommande.getChildren().add(new Label("Client: " + commande.getClient()));
            contenuCommande.getChildren().add(new Label("Produit: " + commande.getProduit()));
            contenuCommande.getChildren().add(new Label("Quantité: " + commande.getQuantite()));
            
            // Créez un TitledPane avec les détails de la commande et ajoutez-le à l'accordéon
            TitledPane pane = new TitledPane("Commande n° " + commande.getNumeroCommande() + " - " + commande.getClient(), contenuCommande);
            accordion.getPanes().add(pane);
        }
    }

    public Accordion getVue() {
        return accordion;
    }
}
