package app;

import java.util.ArrayList;

public class GestionnaireCommande {
	
	private ArrayList<Commande> listeCommande = new ArrayList<>();
	
	public void ajouterCommande(Commande com) {
		listeCommande.add(com);
	}
	
	public void testAjouterVente() {// Test pour ajouter toute les commandes dans les ventes (ne sera normalement pas le cas si les stocks sont insuffisants pour une commande)
		for (Commande commande : listeCommande) {
			GestionnaireFinance.nouvelleVente(commande);
		}
	}

}
