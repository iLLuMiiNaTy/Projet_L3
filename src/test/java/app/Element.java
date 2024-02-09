package app;

public class Element {
	private String code;
	private String nom;
	private int quantite;
	private String uniteDeMesure;
	private int prixAchat;
	private int prixVente;

	public Element(String code, String nom, int quantite, String uniteDeMesure, String prixAchat, String prixVente) {
		this.code = code;
		this.nom = nom;
		this.quantite = quantite;
		this.uniteDeMesure = uniteDeMesure;
		if (prixAchat.equals("NA")) {
			this.prixAchat = 0;
		} else {
			this.prixAchat = Integer.parseInt(prixAchat);
		}
		if (prixVente.equals("NA")) {
			this.prixVente = 0;
		} else {
			this.prixVente = Integer.parseInt(prixVente);
		}
	}

	@Override
	public String toString() {
		String s = "";
		s += 	"#####" + this.nom + "#####" +
				"\n|--------------------|" +
				"\n|Code : " + this.code +
				"\n|--------------------|" +
				"\n|Quantité : " + this.quantite +
				" " + this.uniteDeMesure +
				"\n|--------------------|" +
				"\n|Prix d'achat : " + this.prixAchat + " € " +
				"\n|--------------------|" +
				"\n|Prix de vente : " + this.prixVente + " € " +
				"\n|--------------------|";
		return s;
	}
	
	

	public String getCode() {
		return code;
	}
	
	public String getNom() {
		return nom;
	}
	
	public int getQuantite() {
		return quantite;
	}
	
	public void setQuantite(int quantite) {
		this.quantite = quantite;
	}
	
	public String getUniteDeMesure() {
		return uniteDeMesure;
	}

	public int getPrixAchat() {
		return prixAchat;
	}

	public int getPrixVente() {
		return prixVente;
	}
}
