package app;

public class Transaction {
	
	private String nomElement;
	private int quantite;
	private int prix;
	private String type;//achat ou vente
	
	public Transaction(String nomElement, int quantite, int prix, String type) {
		this.nomElement = nomElement;
		this.quantite = quantite;
		this.prix = prix;
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
