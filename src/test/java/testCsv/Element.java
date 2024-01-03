package testCsv;

public class Element {
	private String code;
	private String nom;
	private int quantite;
	private String uniteDeMesure;

	public Element(String code, String nom, int quantite, String uniteDeMesure) {
		this.code = code;
		this.nom = nom;
		this.quantite = quantite;
		this.uniteDeMesure = uniteDeMesure;
	}

	@Override
	public String toString() {
		String s = "";
		s += "Code : " + this.code + "\nNom : " + this.nom + 
				"\nQuantité : " + this.quantite + "\nUnité de mesure : " + this.uniteDeMesure;
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

	public String getUniteDeMesure() {
		return uniteDeMesure;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public void setQuantite(int quantite) {
		this.quantite = quantite;
	}

	public void setUniteDeMesure(String uniteDeMesure) {
		this.uniteDeMesure = uniteDeMesure;
	}

}
