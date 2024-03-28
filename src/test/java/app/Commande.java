package app;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleFloatProperty;
import javafx.beans.property.SimpleStringProperty;

/**
 * Classe Commande
 * 
 * Cette classe permet de créer des objets Commande
 * 
 * Cette classe contient les attributs suivants :
 * - numeroCommande : numéro de la commande
 * - client : nom du client
 * - codeProduit : code du produit
 * - produit : nom du produit
 * - quantite : quantité de produit
 * - realisable : indique si la commande est réalisable
 * - statut : indique si la commande est en cours de traitement
 * - simulee : indique si la commande est simulée
 * 
 */
public class Commande {

	private SimpleStringProperty numeroCommande;
	private SimpleStringProperty client;
	private SimpleStringProperty codeProduit;
	private SimpleStringProperty produit;
	private SimpleFloatProperty quantite;
	private SimpleBooleanProperty realisable;
	private SimpleBooleanProperty statut;
	private SimpleBooleanProperty simulee;

	/**
	 * Constructeur de la classe Commande
	 * 
	 * @param numeroCommande
	 * @param client
	 * @param codeProduit
	 * @param produit
	 * @param quantite
	 */
	public Commande(String numeroCommande, String client, String codeProduit, String produit, float quantite) {
		this.numeroCommande = new SimpleStringProperty(numeroCommande);
		this.client = new SimpleStringProperty(client);
		this.codeProduit = new SimpleStringProperty(codeProduit);
		this.produit = new SimpleStringProperty(produit);
		this.quantite = new SimpleFloatProperty(quantite);
		this.realisable = new SimpleBooleanProperty(false);
		this.statut = new SimpleBooleanProperty(false);
		this.simulee = new SimpleBooleanProperty(false);
	}

	/**
	 * Méthode toString
	 */
	@Override
	public String toString() {
		String s = "";
		s += 	"\n#####Commande n°" + this.numeroCommande + "#####" +
				"\n|--------------------|" +
				"\n|Client : " + this. client +
				"\n|--------------------|" +
				"\n|Code produit : " + this.codeProduit +
				"\n|--------------------|" +
				"\n|Produit : " + this.produit +
				"\n|--------------------|" +
				"\n|Quantité : " + this.quantite +
				"\n|--------------------|";
		return s;
	}

	/**
	 * Getters et Setters
	 */
	public final String getNumeroCommande() {
		return numeroCommande.get();
	}

	public final String getClient() {
		return client.get();
	}

	public final String getCodeProduit() {
		return codeProduit.get();
	}

	public final String getProduit() {
		return produit.get();
	}

	public final float getQuantite() {
		return quantite.get();
	}
	
	public final boolean getRealisable() {
		return realisable.get();
	}
	
	public void setRealisable(boolean realisable) {
	    this.realisable.set(realisable); // Utilisez `.set(realisable)` sur l'instance existante.
	}

	public BooleanProperty realisableProperty() {
	    return realisable;
	}
	
	public final boolean getStatut() {
		return statut.get();
	}
	
	public void setStatut(boolean statut) {
		this.statut.set(statut);
	}
	
	public BooleanProperty statutProperty() {
		return statut;
	}
	
	public final boolean getSimulee() {
		return simulee.get();
	}
	
	public void setSimulee(boolean simulee) {
		this.simulee.setValue(simulee);
	}
	
	public BooleanProperty simuleeProperty() {
		return simulee;
	}


}
