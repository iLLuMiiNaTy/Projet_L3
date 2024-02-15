package controleur;

import app.GestionnaireStock;
import vue.VueStocks;

public class ControleurStocks {
	private GestionnaireStock GeStock;
    private VueStocks vue;

    public ControleurStocks(GestionnaireStock GeStock, VueStocks vue) {
        this.GeStock = GeStock;
        this.vue = vue;
        initControleur();
    }

    private void initControleur() {
        // Ajoutez la logique pour gérer les actions de l'utilisateur, comme des clics sur des boutons
    }
}
