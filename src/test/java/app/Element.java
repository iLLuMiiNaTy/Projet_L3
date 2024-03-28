package app;

import javafx.beans.property.FloatProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleFloatProperty;
import javafx.beans.property.SimpleIntegerProperty;
/**
 * Classe Element
 * 
 * Cette classe permet de créer un élément avec les attributs suivants :
 * - code : code de l'élément
 * - nom : nom de l'élément
 * - quantite : quantité de l'élément
 * - uniteDeMesure : unité de mesure de l'élément
 * - prixAchat : prix d'achat de l'élément
 * - prixVente : prix de vente de l'élément
 * - urlImage : url de l'image de l'élément
 * - quantiteTemporaire : quantité temporaire de l'élément
 * - codeStockage : code de stockage de l'élément
 * 
 * Cette classe contient les méthodes suivantes :
 * - Constructeur Element
 * - Méthode toString
 * - Méthode getCode
 * - Méthode getNom
 * - Méthode setQuantite
 * - Méthode getQuantite
 * - Méthode getUniteDeMesure
 * - Méthode getPrixAchat
 * - Méthode getPrixVente
 * - Méthode getUrlImage
 * - Méthode getQuantiteTemporaire
 * - Méthode setQuantiteTemporaire
 * - Méthode quantiteTemporaireProperty
 * - Méthode getCodeStockage
 * - Méthode setCodeStockage
 */
public class Element{
	private String code;
	private String nom;
	private SimpleFloatProperty quantite;
	private String uniteDeMesure;
	private int prixAchat;
	private int prixVente;
	private String urlImage;
	private SimpleIntegerProperty quantiteTemporaire;
	private String codeStockage;

	/**
	 * Constructeur de la classe Element
	 * 
	 * @param code
	 * @param nom
	 * @param quantite
	 * @param uniteDeMesure
	 * @param prixAchat
	 * @param prixVente
	 * @param urlImage
	 */
	public Element(String code, String nom, float quantite, String uniteDeMesure, String prixAchat, String prixVente, String urlImage, String codeStockage) {
		this.code = code;
		this.nom = nom;
		this.quantite = new SimpleFloatProperty(quantite);
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
		this.urlImage = urlImage;
		this.quantiteTemporaire = new SimpleIntegerProperty(0);
		this.codeStockage = codeStockage;
	}

	/**
	 * Méthode toString
	 */
	public String toString() {
		String s = "";
		s += 	"#####" + this.nom + "#####" + " Quantité : " + this.quantite;
		return s;
	}

	/**
	 * Méthode getCode
	 */
	public String getCode() {
		return code;
	}

	/**
	 * Méthode getNom
	 */
	public String getNom() {
		return nom;
	}

	/**
	 * Méthode setQuantite Permet de modifier la quantité d'un élément
	 * 
	 * @param quantite
	 */
	public void setQuantite(float quantite) {
		this.quantite.set(quantite);
	}

	/**
	 * Méthode getQuantite
	 * 
	 * @return
	 */
	public final float getQuantite() {
		return quantite.get();
	}

	/**
	 * Méthode getQuantite
	 * 
	 * @return
	 */
	public FloatProperty quantiteProperty() {
	    return quantite;
	}
	/**
	 * Méthode getUniteDeMesure
	 * @return
	 */
	public String getUniteDeMesure() {
		return uniteDeMesure;
	}

	/**
	 * Méthode getPrixAchat
	 * 
	 * @return
	 */
	public int getPrixAchat() {
		return prixAchat;
	}

	/**
	 * Méthode getPrixVente
	 * 
	 * @return
	 */
	public int getPrixVente() {
		return prixVente;
	}

	/**
	 * Méthode getUrlImage
	 * 
	 * @return
	 */
	public String getUrlImage() {
		return urlImage;
	}

	/**
	 * Méthode getQuantiteTemporaire
	 * 
	 * @return
	 */
	public final int getQuantiteTemporaire() {
		return quantiteTemporaire.get();
	}

	/**
	 * Méthode setQuantiteTemporaire
	 * 
	 * @param quantite
	 */
	public final void setQuantiteTemporaire(int quantite) {
		this.quantiteTemporaire.set(quantite);
	}

	/**
	 * Méthode quantiteTemporaireProperty
	 * 
	 * @return
	 */
	public IntegerProperty quantiteTemporaireProperty() {
		return quantiteTemporaire;
	}

	/**
	 * Méthode getCodeStockage
	 * 
	 * @return
	 */
	public String getCodeStockage() {
		return codeStockage;
	}

	/**
	 * Méthode setCodeStockage
	 * 
	 * @param codeStockage
	 */
	public void setCodeStockage(String codeStockage) {
		this.codeStockage = codeStockage;
	}

}
