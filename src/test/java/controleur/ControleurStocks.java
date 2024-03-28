package controleur;

import app.Element;
import app.GestionnaireFinance;
import app.GestionnaireProduction;
import app.GestionnaireStock;
import vue.VueStocks;
/**
 * Controleur de stocks
 */
public class ControleurStocks {
	private GestionnaireStock GeStock;
	/**
	 * Constructeur de la classe ControleurStocks
	 * @param GeStock
	 */
    public ControleurStocks(GestionnaireStock GeStock) {
        this.GeStock = GeStock;
    }

	/**
	 * Methode permettant d'ajouter un element au stock
	 * 
	 * @param element
	 * @param quantite
	 */
    public void augmenterQuantite(Element element, int quantite) {
    	GeStock.ajouterStock(element, quantite);
    }

	/**
	 * Methode permettant de diminuer la quantite d'un element
	 * 
	 * @param element
	 */
    public void diminuerQuantite(Element element) {
        if (element.getQuantite() > 0) {
        	element.setQuantite(element.getQuantite() - 1);
        }
    }

	/**
	 * Methode permettant de verifier si la quantite d'un element est suffisante
	 * 
	 * @param element
	 * @param quantite
	 * @return
	 */
	public boolean verifierQuantiteDisponible(Element element, int quantite) {
		boolean valide = GeStock.verifierQuantiteDisponible(element, quantite);
		return valide;
	}
}
