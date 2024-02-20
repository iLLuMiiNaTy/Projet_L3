package app;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class GestionnaireCommande {
	
	ObservableList<Commande> listeCommande = FXCollections.observableArrayList();
	
	public void ajouterCommande(Commande com) {
		listeCommande.add(com);
	}

}
