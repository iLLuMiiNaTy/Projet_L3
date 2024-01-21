package testCsv;

public class Element {
	private String code;
	private String nom;
	private int quantite;
	private String uniteDeMesure;
	private String prixAchat;
	private String prixVente;

	public Element(String code, String nom, int quantite, String uniteDeMesure, String prixAchat, String prixVente) {
		this.code = code;
		this.nom = nom;
		this.quantite = quantite;
		this.uniteDeMesure = uniteDeMesure;
		this.prixAchat = prixAchat;
		this.prixVente = prixVente;
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

	public String getPrixAchat() {
		return prixAchat;
	}

	public String getPrixVente() {
		return prixVente;
	}
}
