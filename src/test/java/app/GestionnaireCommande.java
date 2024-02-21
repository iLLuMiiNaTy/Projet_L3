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
	
	public boolean simulerCommande() {
		ObservableList<Element> listeElementTemporaire = FichierCSV.getListeElement();
		//GestionnaireProduction.simulerProduction(listeElementTemporaire, this.listeCommande);
		//appel à simulerProduction avec en paramètre une liste d'éléments temporaires pour ne pas affecter le stocks d'éléments
		return false;
		
	}
	
	public static ObservableList<Commande> getListeCommande(){
		return listeCommande;
	}

}
