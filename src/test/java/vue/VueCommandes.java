package vue;

import app.Commande;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.VBox;

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
}
