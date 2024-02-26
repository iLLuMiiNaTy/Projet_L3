package controleur;

import app.Element;
import app.GestionnaireFinance;
import app.GestionnaireProduction;
import app.GestionnaireStock;
import vue.VueStocks;

public class ControleurStocks {
	private GestionnaireStock GeStock;

    public ControleurStocks(GestionnaireStock GeStock) {
        this.GeStock = GeStock;
    }
    
    public void augmenterQuantite(Element e, int quantite) {
    	GeStock.ajouterStock(e, quantite);
    }

    public void diminuerQuantite(Element e) {
        if (e.getQuantite() > 0) {
            e.setQuantite(e.getQuantite() - 1);
        }
    }
}
