package app;

public class Stockage {
	private String code;
	private String nom;
	private int capacite;
	private int quantite;

	public Stockage(String code, String nom, int capacite, int quantite) {
        this.code = code;
        this.nom = nom;
        this.capacite = capacite;
        this.quantite = quantite;
	}
	
	public String toString() {
		return this.code + " " + this.nom + " " + this.capacite + " " + this.quantite;
	}

	public int getCapacite() {
		return this.capacite;
	}

	public int getQuantite() {
		return this.quantite;
	}

	public String getCode() {
		return this.code;
	}

}
