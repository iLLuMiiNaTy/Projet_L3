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
    	/*
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
        */
    }

    public Accordion getVue() {
        return accordion;
    }
}
