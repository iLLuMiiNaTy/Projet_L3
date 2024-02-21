package vue;

import app.Commande;
import controleur.ControleurCommandes;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.ObservableList;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

public class VueCommandes {
    private TableView<Commande> table = new TableView<>();
    private ObservableList<Commande> listeCommande;
    private ControleurCommandes ControlCom;

    public VueCommandes(ObservableList<Commande> listeCommande, ControleurCommandes ControlCom) {
        this.listeCommande = listeCommande;
        this.ControlCom = ControlCom;
        creerVueCommandes();
    }

    private void creerVueCommandes() {
        TableColumn<Commande, String> colonneNumero = new TableColumn<>("Numéro de commande");
        colonneNumero.setCellValueFactory(new PropertyValueFactory<>("numeroCommande"));

        TableColumn<Commande, String> colonneClient = new TableColumn<>("Nom du client");
        colonneClient.setCellValueFactory(new PropertyValueFactory<>("client"));

        TableColumn<Commande, String> colonneProduit = new TableColumn<>("Type de produit");
        colonneProduit.setCellValueFactory(new PropertyValueFactory<>("produit"));

        TableColumn<Commande, Integer> colonneQuantite = new TableColumn<>("Quantité commandée");
        colonneQuantite.setCellValueFactory(new PropertyValueFactory<>("quantite"));
        
        TableColumn<Commande, String> colonneRealisable = new TableColumn<>("Réalisable");
        colonneRealisable.setCellValueFactory(cellData -> 
            new SimpleStringProperty(cellData.getValue().isRealisable() ? "Oui" : "Non"));

        table.getColumns().addAll(colonneNumero, colonneClient, colonneProduit, colonneQuantite, colonneRealisable);
        table.setItems(listeCommande);
    }

    public TableView<Commande> getVue() {
        return table;
    }
}