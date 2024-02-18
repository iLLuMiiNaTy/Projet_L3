package controleur;

import app.GestionnaireProduction;
import vue.VueChaines;

public class ControleurChaines {
	private GestionnaireProduction GeProd;
    private VueChaines vue;

    public ControleurChaines(GestionnaireProduction GeProd, VueChaines vue) {
        this.GeProd = GeProd;
        this.vue = vue;
        initControleur();
    }

    private void initControleur() {
        // Ajoutez la logique pour gérer les actions de l'utilisateur, comme des clics sur des boutons
    }
}
