package controleur;

import app.GestionnaireCommande;
import vue.VueCommandes;

public class ControleurCommandes {
    private GestionnaireCommande gestionnaireCommande;
    private VueCommandes vue;

    public ControleurCommandes(GestionnaireCommande gestionnaireCommande, VueCommandes vue) {
        this.gestionnaireCommande = gestionnaireCommande;
        this.vue = vue;
        initControleur();
    }

    private void initControleur() {
        // Ajoutez la logique pour gérer les actions de l'utilisateur, comme des clics sur des boutons
    }
}
