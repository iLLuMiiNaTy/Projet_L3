package app;

import javafx.beans.property.SimpleIntegerProperty;

public class Transaction {
	
	private String nomElement;
	private SimpleIntegerProperty quantite;
	private SimpleIntegerProperty prix;
	private String type;//achat ou vente
	
	public Transaction(String nomElement, int quantite, int prix, String type) {
		this.nomElement = nomElement;
		this.quantite = new SimpleIntegerProperty(quantite);
		this.prix = new SimpleIntegerProperty(prix);
		this.type = type;
	}
	
	@Override
	public String toString() {
		String s = "";
		s += 	"#####" + this.nomElement + "#####" +
				"\n|--------------------|" +
				"\n|Quantité : " + this.quantite +
				"\n|--------------------|" +
				"\n|Prix commande : " + this.prix +
				"\n|--------------------|" +
				"\n|Type transaction : " + this.type +
				"\n|--------------------|";
		return s;
	}

	public String getNomElement() {
		return nomElement;
	}

	public final int getQuantite() {
		return quantite.get();
	}

	public final int getPrix() {
		return prix.get();
	}

	public String getType() {
		return type;
	}
	
	

}
