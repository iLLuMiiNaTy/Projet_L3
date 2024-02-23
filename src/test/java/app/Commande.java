package app;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableBooleanValue;

public class Commande {

	private SimpleStringProperty numeroCommande;
	private SimpleStringProperty client;
	private SimpleStringProperty codeProduit;
	private SimpleStringProperty produit;
	private SimpleIntegerProperty quantite;
	private SimpleBooleanProperty realisable;

	public Commande(String numeroCommande, String client, String codeProduit, String produit, int quantite) {
		this.numeroCommande = new SimpleStringProperty(numeroCommande);
		this.client = new SimpleStringProperty(client);
		this.codeProduit = new SimpleStringProperty(codeProduit);
		this.produit = new SimpleStringProperty(produit);
		this.quantite = new SimpleIntegerProperty(quantite);
		this.realisable = new SimpleBooleanProperty(false);
	}

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

	public final int getQuantite() {
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





}
