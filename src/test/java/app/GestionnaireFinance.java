package app;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

public class GestionnaireFinance {
	
	private List <Transaction> transactions;
	private static HashMap<Element, Integer> listeAchat = new HashMap<>();
	private static HashMap<Commande, Integer> listeVente = new HashMap<>();
	
	public void nouvelleVente(Commande com) {
    	String codeProduit = com.getCodeProduit();
    	Element element = FichierCSV.trouverElementParCode(codeProduit);
    	String numCommande = com.getNumeroCommande();
    	String nomElement = element.getNom();
    	int quantite = com.getQuantite();
    	int prix = element.getPrixVente()* quantite;
    	String type = "vente";
    	Transaction transaction = new Transaction(numCommande, nomElement, quantite, prix, type);
    	transactions.add(transaction);
    	//ajouterVente(com, prix);
    }
	
	public static void ajouterVente(Commande com, int prix) {
	}
	
	public void afficherVente() {
		for(Entry<Commande, Integer> entry:listeVente.entrySet()) {
			Commande com = entry.getKey();
			String numComande = com.getNumeroCommande();
			String produit = com.getProduit();
			int quantite = entry.getValue();
			System.out.println("\n");
		}
	}
	
	public void afficherVentes() {
		System.out.println("\nAffichage des ventes :");
        for (Transaction transaction : transactions) {
        	System.out.println("\n");
        	System.out.println(transaction);
        }
    }

}
