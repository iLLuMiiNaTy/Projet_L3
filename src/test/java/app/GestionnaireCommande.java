package app;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class GestionnaireCommande {
	
	ObservableList<Commande> listeCommande = FXCollections.observableArrayList();
	
	public GestionnaireCommande() {
		this.listeCommande = FichierCSV.getListeCommande();
	}
	
	public void ajouterCommande(Commande com) {
		listeCommande.add(com);
	}
	
	public boolean simulerCommande() {
		ObservableList<Element> listeElementTemporaire = FichierCSV.getListeElement();
		GestionnaireProduction.simulerProduction(listeElementTemporaire, this.listeCommande);
		//appel à simulerProduction avec en paramètre une liste d'éléments temporaires pour ne pas affecter le stocks d'éléments
		return false;
		
	}

}
