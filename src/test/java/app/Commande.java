package app;

public class Commande {

	private String numeroCommande;
	private String client;
	private String codeProduit;
	private String produit;
	private int quantite;

	public Commande(String numeroCommande, String client, String codeProduit, String produit, String quantite) {
		super();
		this.numeroCommande = numeroCommande;
		this.client = client;
		this.codeProduit = codeProduit;
		this.produit = produit;
		this.quantite = Integer.parseInt(quantite);
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
		return numeroCommande;
	}

	public String getClient() {
		return client;
	}

	public String getCodeProduit() {
		return codeProduit;
	}

	public String getProduit() {
		return produit;
	}

	public int getQuantite() {
		return quantite;
	}





}
