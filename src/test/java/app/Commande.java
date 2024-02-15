package app;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Commande {

	private SimpleStringProperty numeroCommande;
	private SimpleStringProperty client;
	private SimpleStringProperty codeProduit;
	private SimpleStringProperty produit;
	private SimpleStringProperty quantite;

	public Commande(String numeroCommande, String client, String codeProduit, String produit, String quantite) {
		this.numeroCommande = new SimpleStringProperty(numeroCommande);
		this.client = new SimpleStringProperty(client);
		this.codeProduit = new SimpleStringProperty(codeProduit);
		this.produit = new SimpleStringProperty(produit);
		this.quantite = new SimpleStringProperty(quantite);
	}

	@Override
	public String toString() {
		String s = "";
		s += 	"#####Commande n°" + this.numeroCommande + "#####" +
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

	public String getNumeroCommande() {
		return numeroCommande.get();
	}

	public String getClient() {
		return client.get();
	}

	public String getCodeProduit() {
		return codeProduit.get();
	}

	public String getProduit() {
		return produit.get();
	}

	public String getQuantite() {
		return quantite.get();
	}

	public StringProperty numeroCommandeProperty() {
		// TODO Auto-generated method stub
		return numeroCommande;
	}

	public StringProperty clientProperty() {
		// TODO Auto-generated method stub
		return client;
	}

	public StringProperty codeProduitProperty() {
		// TODO Auto-generated method stub
		return codeProduit;
	}

	public StringProperty ProduitProperty() {
		// TODO Auto-generated method stub
		return produit;
	}

	public StringProperty QuantiteProperty() {
		// TODO Auto-generated method stub
		return quantite;
	}





}
