package app;

import java.util.HashMap;
import java.util.Map;

public class ChaineDeProduction {
    private String code;
    private String nom;
    private HashMap<Element, Float> elementsEntree;
    private HashMap<Element, Float> elementsSortie;
    private int niveauActivation;

    public ChaineDeProduction(String code, String nom, HashMap<Element, Float> elementsEntree, HashMap<Element, Float> elementsSortie) {
        this.code = code;
        this.nom = nom;
        this.elementsEntree = elementsEntree;
        this.elementsSortie = elementsSortie;
        this.niveauActivation = 1;
    }

    @Override
	public String toString() {
    	String s = "";
    	s += 	"#####" + this.nom + "#####" +
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
    			"\n|Niveau d'activation : " + this.niveauActivation +
    			"\n|--------------------|";
    	return s;
    }

    public String getCode() {
        return code;
    }

    public String getNom() {
        return nom;
    }

    public HashMap<Element, Float> getElementsEntree() {
        return elementsEntree;
    }

    public void setElementsEntree(HashMap<Element, Float> elementsEntree) {
        this.elementsEntree = elementsEntree;
    }

    public HashMap<Element, Float> getElementsSortie() {
        return elementsSortie;
    }

    public void setElementsSortie(HashMap<Element, Float> elementsSortie) {
        this.elementsSortie = elementsSortie;
    }

    public int getNiveauActivation() {
        return niveauActivation;
    }

    public void setNiveauActivation(int niveauActivation) {
        this.niveauActivation = niveauActivation;
    }

    public void activer(int niveau) {
        this.niveauActivation = niveau;
    }
}
