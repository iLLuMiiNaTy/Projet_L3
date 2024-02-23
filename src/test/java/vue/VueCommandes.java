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
        colonneRealisable.setCellValueFactory(cellData -> Bindings.when(cellData.getValue().realisableProperty())
        	    .then("Oui")
        	    .otherwise("Non"));

        TableColumn<Commande, Void> colonneSimulation = new TableColumn<>("Simulation");
        
        Callback<TableColumn<Commande, Void>, TableCell<Commande, Void>> cellFactory = new Callback<TableColumn<Commande, Void>, TableCell<Commande, Void>>() {
            @Override
            public TableCell<Commande, Void> call(final TableColumn<Commande, Void> param) {
                final TableCell<Commande, Void> cell = new TableCell<Commande, Void>() {
                    private final Button btn = new Button("Lancer Simulation");

                    {
                        btn.setOnAction((ActionEvent event) -> {
                            Commande commande = getTableView().getItems().get(getIndex());
                            ControlCom.simulerCommande(commande);
                        });
                    }

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

        colonneSimulation.setCellFactory(cellFactory);

        table.getColumns().addAll(colonneNumero, colonneClient, colonneProduit, colonneQuantite, colonneRealisable, colonneSimulation);
        //table.setRowHeight(20);
        table.setMaxHeight(121);
        table.setItems(listeCommande);
    }

    public TableView<Commande> getVue() {
        return table;
    }
}