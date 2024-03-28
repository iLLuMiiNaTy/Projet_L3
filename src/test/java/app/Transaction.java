package app;

import javafx.beans.property.SimpleFloatProperty;
import javafx.beans.property.SimpleIntegerProperty;

/**
 * Classe Transaction
 * 
 * Cette classe permet de créer des objets de type Transaction
 */
public class Transaction {
	
	private String nomElement;
	private SimpleFloatProperty quantite;
	private SimpleIntegerProperty prix;
	private String type;//achat ou vente

	/**
	 * Constructeur de la classe Transaction
	 * 
	 * @param nomElement
	 * @param quantite
	 * @param prix
	 * @param type
	 */
	public Transaction(String nomElement, float quantite, int prix, String type) {
		this.nomElement = nomElement;
		this.quantite = new SimpleFloatProperty(quantite);
		this.prix = new SimpleIntegerProperty(prix);
		this.type = type;
	}

	/**
	 * Méthode toString
	 */
	public String toString() {
		String s = "";
		s+= this.type + " " + this.quantite + " " + this.nomElement +
				"\n Prix : " + this.getPrix();
		return s;
	}

	/**
	 * Méthode getNomElement
	 */
	public String getNomElement() {
		return nomElement;
	}

	/**
	 * Méthode getQuantite
	 */
	public final int getQuantite() {
		return (int) quantite.get();
	}

	/**
	 * Méthode getPrix
	 */
	public final int getPrix() {
		return prix.get();
	}

	/**
	 * Méthode getType
	 */
	public String getType() {
		return type;
	}
}
