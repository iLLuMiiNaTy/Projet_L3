package app;

public class Transaction {
	
	private String numCommande;
	private String nomElement;
	private int quantite;
	private int prix;
	private String type;//achat ou vente
	
	public Transaction(String numCommande, String nomElement, int quantite, int prix, String type) {
		this.numCommande = numCommande;
		this.nomElement = nomElement;
		this.quantite = quantite;
		this.prix = prix;
		this.type = type;
	}
	
	@Override
	public String toString() {
		String s = "";
		s += 	"##### Commande n°" + this.numCommande + "#####" +
				"\n|--------------------|" +
				"\n|Nom produit : " + this. nomElement +
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

	public int getQuantite() {
		return quantite;
	}

	public int getPrix() {
		return prix;
	}

	public String getType() {
		return type;
	}
	
	

}
