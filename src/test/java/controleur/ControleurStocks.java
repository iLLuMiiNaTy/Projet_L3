package controleur;

import app.Element;
import app.GestionnaireStock;
import vue.VueStocks;

public class ControleurStocks {
	private GestionnaireStock GeStock;

    public ControleurStocks(GestionnaireStock GeStock) {
        this.GeStock = GeStock;
        initControleur();
    }

    private void initControleur() {
        // Ajoutez la logique pour gérer les actions de l'utilisateur, comme des clics sur des boutons
    }
    
    public void augmenterQuantite(Element e) {
    	e.setQuantite(e.getQuantite() + 1);
    }

    public void diminuerQuantite(Element e) {
        if (e.getQuantite() > 0) {
            e.setQuantite(e.getQuantite() - 1);
        }
    }
}
