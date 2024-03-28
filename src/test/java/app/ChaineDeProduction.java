package app;

import java.util.HashMap;
import java.util.Map;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
/**
 * Classe ChaineDeProduction
 * 
 * Cette classe permet de définir une chaine de production
 * Elle contient les attributs suivants :
 * - code : le code de la chaine de production
 * - nom : le nom de la chaine de production
 * - elementsEntree : les éléments nécessaires à l'entrée de la chaine de production
 * - elementsSortie : les éléments produits à la sortie de la chaine de production
 * - urlImage : l'url de l'image de la chaine de production
 * - niveauActivation : le niveau d'activation de la chaine de production
 * 
 * Elle contient les méthodes suivantes :
 * - Constructeur ChaineDeProduction
 * - Méthode toString
 * - Méthode activer
 * - Méthode getCode
 * - Méthode getNom
 * - Méthode getElementsEntree
 * - Méthode setElementsEntree
 * - Méthode getElementsSortie
 * - Méthode setElementsSortie
 * - Méthode getActivation
 * - Méthode setNiveauActivation
 * - Méthode activationProperty
 * - Méthode getUrlImage
 */
public class ChaineDeProduction {
    private String code;
    private String nom;
    private HashMap<Element, Float> elementsEntree;
    private HashMap<Element, Float> elementsSortie;
    private String urlImage;
    private SimpleIntegerProperty niveauActivation;

	/**
	 * Constructeur de la classe ChaineDeProduction
	 * 
	 * @param code
	 * @param nom
	 * @param elementsEntree
	 * @param elementsSortie
	 * @param urlImage
	 */
    public ChaineDeProduction(String code, String nom, HashMap<Element, Float> elementsEntree, HashMap<Element, Float> elementsSortie, String urlImage) {
        this.code = code;
        this.nom = nom;
        this.elementsEntree = elementsEntree;
        this.elementsSortie = elementsSortie;
        this.urlImage = urlImage;
        this.niveauActivation = new SimpleIntegerProperty(1);
    }

	/**
	 * Méthode toString
	 */
    @Override
	public String toString() {
    	String s = "";
    	s += 	"\n#####" + this.nom + "#####" +
    			"\n|--------------------|" +
    			"\n|Code : " + this.code +
    			"\n|--------------------|" +
    			"\n|Elements en entrée :";

    	for (Map.Entry<Element, Float> entry : this.getElementsEntree().entrySet()) {
            Element element = entry.getKey();
            String quantite = entry.getValue().toString();
            s += "\n|  - " + element.getNom() + " (" + quantite + " " + element.getUniteDeMesure() + ")";
        }
    	s += 	"\n|--------------------|" +
    			"\n|Elements en sortie :";
    	for (Map.Entry<Element, Float> entry : this.getElementsSortie().entrySet()) {
            Element element = entry.getKey();
            String quantite = entry.getValue().toString();
            s += "\n|  - " + element.getNom() + " (" + quantite + " " + element.getUniteDeMesure() + ")";
        }
    	s += 	"\n|--------------------|" +
    			"\n|Niveau d'activation : " + this.getActivation() +
    			"\n|--------------------|";
    	return s;
    }

	/**
	 * Méthode activer
	 * Permet d'activer une chaine de production
	 * 
	 * @param niveau
	 */
    public void activer(int niveau) {
        setNiveauActivation(niveau);
    }
    
    /**
     * Méthode getCode
     * @return
     */
    public String getCode() {
        return code;
    }

	/**
	 * Méthode getNom
	 * 
	 * @return
	 */
    public String getNom() {
        return nom;
    }

	/**
	 * Méthode getElementsEntree
	 * 
	 * @return
	 */
    public HashMap<Element, Float> getElementsEntree() {
        return elementsEntree;
    }

	/**
	 * Méthode setElementsEntree
	 * 
	 * @param elementsEntree
	 */
    public void setElementsEntree(HashMap<Element, Float> elementsEntree) {
        this.elementsEntree = elementsEntree;
    }

	/**
	 * Méthode getElementsSortie
	 * 
	 * @return
	 */
    public HashMap<Element, Float> getElementsSortie() {
        return elementsSortie;
    }

	/**
	 * Méthode setElementsSortie
	 * 
	 * @param elementsSortie
	 */
    public void setElementsSortie(HashMap<Element, Float> elementsSortie) {
        this.elementsSortie = elementsSortie;
    }

	/**
	 * Méthode getActivation
	 * 
	 * @return
	 */
    public final int getActivation() {
        return niveauActivation.get();
    }

	/**
	 * Méthode setNiveauActivation
	 * 
	 * @param niveauActivation
	 */
    public void setNiveauActivation(int niveauActivation) {
        this.niveauActivation.set(niveauActivation);;
    }

	/**
	 * Méthode activationProperty
	 * 
	 * @return
	 */
    public IntegerProperty activationProperty() {
	    return niveauActivation;
	}

	/**
	 * Méthode getUrlImage
	 * 
	 * @return
	 */
    public String getUrlImage() {
    	return urlImage;
    }
}
