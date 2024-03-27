package vue;

import app.Commande;
import app.GestionnaireCommande;
import controleur.ControleurCommandes;
import javafx.beans.binding.Bindings;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.util.Callback;

public class VueCommandes {
    private TableView<Commande> table = new TableView<>();
    private ObservableList<Commande> listeCommande;
    private ControleurCommandes ControlCom;

	/**
	 * Constructeur de la vue des commandes
	 * 
	 * @param listeCommande
	 * @param ControlCom
	 */
    public VueCommandes(ObservableList<Commande> listeCommande, ControleurCommandes ControlCom) {
        this.listeCommande = listeCommande;
        this.ControlCom = ControlCom;
        creerVueCommandes();
    }

	/**
	 * Crée la vue des commandes
	 */
    private void creerVueCommandes() {
    	// Création des colonnes de la table
        TableColumn<Commande, String> colonneNumero = new TableColumn<>("Numéro de commande");
        colonneNumero.setCellValueFactory(new PropertyValueFactory<>("numeroCommande"));

        TableColumn<Commande, String> colonneClient = new TableColumn<>("Nom du client");
        colonneClient.setCellValueFactory(new PropertyValueFactory<>("client"));

        TableColumn<Commande, String> colonneProduit = new TableColumn<>("Type de produit");
        colonneProduit.setCellValueFactory(new PropertyValueFactory<>("produit"));

        TableColumn<Commande, Integer> colonneQuantite = new TableColumn<>("Quantité commandée");
        colonneQuantite.setCellValueFactory(new PropertyValueFactory<>("quantite"));
        
        TableColumn<Commande, String> colonneRealisable = new TableColumn<>("Réalisable");
        // Bindings.when permet de faire un if else dans une cellule de tableau
        colonneRealisable.setCellValueFactory(cellData -> Bindings.when(cellData.getValue().realisableProperty())
        	    .then("Oui")
        	    .otherwise("Non"));

        TableColumn<Commande, Void> colonneSimulation = new TableColumn<>("Simulation");
        
        // Création du bouton "Lancer Vérification"
        Callback<TableColumn<Commande, Void>, TableCell<Commande, Void>> cellFactory = new Callback<TableColumn<Commande, Void>, TableCell<Commande, Void>>() {
            @Override
            public TableCell<Commande, Void> call(final TableColumn<Commande, Void> param) {
                final TableCell<Commande, Void> cell = new TableCell<Commande, Void>() {
                    private final Button btn = new Button("Lancer Vérification");

                    {
                        btn.setOnAction((ActionEvent event) -> {
                            Commande commande = getTableView().getItems().get(getIndex());
                            ControlCom.simulerCommande(commande);
                        });
                    }
                    
                    // Affiche le bouton dans la cellule
                    @Override
                    public void updateItem(Void item, boolean empty) {
                        super.updateItem(item, empty);
                        if (empty) {
                            setGraphic(null);
                        } else {
                            setGraphic(btn);
                        }
                    }
                };
                return cell;
            }
        };
        
        TableColumn<Commande, String> colonneStatut = new TableColumn<>("Statut");
        colonneStatut.setCellValueFactory(cellData -> Bindings.when(cellData.getValue().statutProperty())
        	    .then("Complète")
        	    .otherwise("En cours"));

        colonneSimulation.setCellFactory(cellFactory);
        
        // Ajout des colonnes à la table
        table.getColumns().addAll(colonneNumero, colonneClient, colonneProduit, colonneQuantite, colonneRealisable, colonneStatut, colonneSimulation);
        table.setMinWidth(762);
        table.setMaxHeight(300);
        table.setItems(listeCommande);
    }

	/**
	 * Retourne la vue des commandes
	 * 
	 * @return TableView<Commande>
	 */
    public TableView<Commande> getVue() {
        return table;
    }
}