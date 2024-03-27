package controleur;

import app.Commande;
import app.GestionnaireCommande;
import app.GestionnaireProduction;
import vue.VueCommandes;

public class ControleurCommandes {
    private GestionnaireCommande GeCom;

	/**
	 * Constructeur du controleur des commandes
	 * 
	 * @param GeCom
	 */
    public ControleurCommandes(GestionnaireCommande GeCom) {
        this.GeCom = GeCom;
    }

	/**
	 * Appelle la méthode du Gestionnaire de Production pour simuler la production d'une commande
	 * @param commande
	 */
	public void simulerCommande(Commande commande) {
		GestionnaireProduction.simulerProduction(commande);
	}
}
