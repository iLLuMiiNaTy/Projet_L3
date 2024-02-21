package controleur;

import app.GestionnaireCommande;
import vue.VueCommandes;

public class ControleurCommandes {
    private GestionnaireCommande GeCom;

    public ControleurCommandes(GestionnaireCommande GeCom) {
        this.GeCom = GeCom;
        initControleur();
    }

    private void initControleur() {
        // Ajoutez la logique pour gérer les actions de l'utilisateur, comme des clics sur des boutons
    }
}
