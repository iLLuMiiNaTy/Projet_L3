package app;

import java.util.ArrayList;

public class GestionnaireCommande {
	
	private ArrayList<Commande> listeCommande = new ArrayList<>();
	
	public void ajouterCommande(Commande com) {
		listeCommande.add(com);
	}

}
