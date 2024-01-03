package testCsv;

import java.util.HashMap;

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

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
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
