package app;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
/**
 * Classe permettant de gérer les commandes
 * 
 * Cette classe contient une liste de commandes et permet d'ajouter une commande à cette liste
 */
public class GestionnaireCommande {
	
	private static ObservableList<Commande> listeCommande = FXCollections.observableArrayList();
	private GestionnaireStock GeStock;
	/**
	 * Constructeur de la classe GestionnaireCommande
	 * @param GeStock
	 */
	public GestionnaireCommande(GestionnaireStock GeStock) {
		this.GeStock = GeStock;
	}

	/**
	 * Méthode permettant d'ajouter une commande à la liste des commandes
	 * 
	 * @param com
	 */
	public void ajouterCommande(Commande com) {
		listeCommande.add(com);
	}
	/**
	 * Méthode permettant de retourner la liste des commandes
	 * @return
	 */
	public static ObservableList<Commande> getListeCommande(){
		return listeCommande;
	}
	/**
	 * Méthode permettant de marquer une commande comme terminée si elle est réalisable
	 */
	public static void completerCommande() {
		for (Commande c : listeCommande) {
			if (c.getRealisable() && !c.getStatut()) {
				c.setStatut(true);
			}
		}
	}

}
