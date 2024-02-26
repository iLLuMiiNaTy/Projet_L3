package app;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class GestionnaireCommande {
	
	private static ObservableList<Commande> listeCommande = FXCollections.observableArrayList();
	private GestionnaireStock GeStock;
	
	public GestionnaireCommande(GestionnaireStock GeStock) {
		this.GeStock = GeStock;
	}
	
	public void ajouterCommande(Commande com) {
		listeCommande.add(com);
	}
	
	public static ObservableList<Commande> getListeCommande(){
		return listeCommande;
	}
	
	public static void completerCommande() {
		for (Commande c : listeCommande) {
			if (c.getRealisable() && !c.getStatut()) {
				c.setStatut(true);
			}
		}
	}

}
