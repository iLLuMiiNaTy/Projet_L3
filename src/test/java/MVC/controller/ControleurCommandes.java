package MVC.controller;

import MVC.VueCommandes;
import app.GestionnaireCommande;

public class ControleurCommandes {
    private GestionnaireCommande gestionnaireCommande;
    private VueCommandes vue;

    public ControleurCommandes(GestionnaireCommande gestionnaireCommande, VueCommandes vue) {
        this.gestionnaireCommande = gestionnaireCommande;
        this.vue = vue;
        initContrôleur();
    }

    private void initContrôleur() {
        // Ajoutez la logique pour gérer les actions de l'utilisateur, comme des clics sur des boutons
    }
}
