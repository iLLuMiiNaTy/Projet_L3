package app;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.FloatProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleFloatProperty;
import javafx.beans.property.SimpleIntegerProperty;

public class Element{
	private String code;
	private String nom;
	private SimpleFloatProperty quantite;
	private String uniteDeMesure;
	private int prixAchat;
	private int prixVente;
	private String urlImage;
	private SimpleIntegerProperty quantiteTemporaire;

	public Element(String code, String nom, float quantite, String uniteDeMesure, String prixAchat, String prixVente, String urlImage) {
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
	}

	/*@Override
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
	}*/
	
	public String toString() {
		String s = "";
		s += 	"#####" + this.nom + "#####" + " Quantité : " + this.quantite;
		return s;
	}



	public String getCode() {
		return code;
	}

	public String getNom() {
		return nom;
	}

	public void setQuantite(float quantite) {
		this.quantite.set(quantite);
	}
	
	public final float getQuantite() {
		return quantite.get();
	}

	public FloatProperty quantiteProperty() {
	    return quantite;
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
	
	public String getUrlImage() {
		return urlImage;
	}
	
	public final int getQuantiteTemporaire() {
		return quantiteTemporaire.get();
	}
	
	public final void setQuantiteTemporaire(int quantite) {
		this.quantiteTemporaire.set(quantite);
	}

	public IntegerProperty quantiteTemporaireProperty() {
		return quantiteTemporaire;
	}

}
