package controleur;

import app.Commande;
import app.GestionnaireCommande;
import app.GestionnaireProduction;
import vue.VueCommandes;

public class ControleurCommandes {
    private GestionnaireCommande GeCom;

    public ControleurCommandes(GestionnaireCommande GeCom) {
        this.GeCom = GeCom;
    }

	public void simulerCommande(Commande commande) {
		GestionnaireProduction.simulerProduction(commande);
	}
}
