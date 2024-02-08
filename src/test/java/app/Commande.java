package app;

public class Commande {
	
	private String numeroCommande;
	private String client;
	private String produit;
	private int quantite;
	
	
	public Commande(String numeroCommande, String client, String produit, int quantite) {
		super();
		this.numeroCommande = numeroCommande;
		this.client = client;
		this.produit = produit;
		this.quantite = quantite;
	}
	
	@Override
	public String toString() {
		String s = "";
		s += 	"##### Commande n°" + this.numeroCommande + "#####" +
				"\n|--------------------|" +
				"\n|Client : " + this. client +
				"\n|--------------------|" +
				"\n|Produit : " + this.produit +
				"\n|--------------------|" +
				"\n|Quantité : " + this.quantite +
				"\n|--------------------|";
		return s;
	}

	public String getNumeroCommande() {
		return numeroCommande;
	}

	public String getClient() {
		return client;
	}

	public String getProduit() {
		return produit;
	}

	public int getQuantite() {
		return quantite;
	}
	
	
	
	

}
