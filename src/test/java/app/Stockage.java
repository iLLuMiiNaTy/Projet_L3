package app;

/**
 * Classe Stockage
 * 
 * Cette classe permet de définir un stockage
 */
public class Stockage {
	private String code;
	private String nom;
	private int capacite;
	private int quantite;

	/**
	 * Constructeur de la classe Stockage
	 * 
	 * @param code
	 * @param nom
	 * @param capacite
	 * @param quantite
	 */
	public Stockage(String code, String nom, int capacite, int quantite) {
        this.code = code;
        this.nom = nom;
        this.capacite = capacite;
        this.quantite = quantite;
	}

	/**
	 * Méthode toString
	 */
	public String toString() {
		return this.code + " " + this.nom + " " + this.capacite + " " + this.quantite;
	}

	/**
	 * Méthode getCapacite
	 */
	public int getCapacite() {
		return this.capacite;
	}

	/**
	 * Méthode getQuantite
	 */
	public int getQuantite() {
		return this.quantite;
	}

	/**
	 * Méthode getCode
	 */
	public String getCode() {
		return this.code;
	}
}
