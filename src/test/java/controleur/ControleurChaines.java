package controleur;

import app.GestionnaireProduction;
import vue.VueChaines;

/**
 * Controleur des chaines de production
 */
public class ControleurChaines {
	private GestionnaireProduction GeProd;
	/**
	 * Constructeur du controleur des chaines de production
	 * @param GeProd
	 */
    public ControleurChaines(GestionnaireProduction GeProd) {
        this.GeProd = GeProd;
    }
}
