package app;

import java.util.HashMap;
import java.util.ArrayList;

public class GestionnaireStock {

<<<<<<< HEAD
    private HashMap<Element, Integer> listeElementStock;

    public GestionnaireStock() {
        this.listeElementStock = new HashMap<>();
    }

    public void ajouterStock(Element e, int q) {
        if (this.listeElementStock.containsKey(e)){
            e.setQuantite(e.getQuantite() + q);
            System.out.println("Quantité ajoutée\n");
        }
        else {
            this.listeElementStock.put(e,q);
            System.out.println("Quantité ajoutée (nouvel element)\n");
        }
    }

    public void retirerStock(Element e, int q) {
        if (! this.listeElementStock.containsKey(e)) {
            System.out.println("Erreur : Element non stocké !\n");
        }
        else {
            if (e.getQuantite() < q) {
                System.out.println("Erreur : Stock insuffisant !\n");
            }
            else {
                e.setQuantite(e.getQuantite() - q);
            }
        }
    }
    
    public void afficherStock() { // need ?
        String s = "";
        for (Element e : this.listeElementStock.keySet()) {
            s += e.getNom() + " : " + e.getQuantite() + "\n";
        }
        System.out.println(s);
    }

    public boolean verifierStockCommande(Commande c) { // estStockSuffisant()
    	return (c.getQuantite() <= c.getProduit().getQuantite()); // getProduit() return Element
    }

    public HashMap<Commande,Boolean> verifierStockListeCommande(ArrayList<Commande> listeCommande) {
        HashMap<Commande,Boolean> listeCverifiée = new HashMap<>();
        for (Commande c : listeCommande) {
            listeCverifiée.put(c, verifierStockCommande(c));
        }
        return listeCverifiée;
    }
}
=======
}
>>>>>>> refs/remotes/origin/TestIliasse
