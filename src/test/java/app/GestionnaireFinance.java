package app;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

public class GestionnaireFinance {
	
	private static List <Transaction> transactions = new ArrayList<>();;
	
	public void nouvelleVente(Commande com) {
    	String codeProduit = com.getCodeProduit();
    	Element element = FichierCSV.trouverElementParCode(codeProduit);
    	String nomElement = element.getNom();
    	int quantite = com.getQuantite();
    	int prix = element.getPrixVente()* quantite;
    	String type = "vente";
    	Transaction transaction = new Transaction(nomElement, quantite, prix, type);
    	transactions.add(transaction);
    }
	
	public void afficherVentes() {
		System.out.println("\nAffichage des ventes :");
        for (Transaction transaction : transactions) {
        	System.out.println("\n");
        	System.out.println(transaction);
        }
    }
	
	public void nouvelAchat(Element element, int quantite) {//récupère depuis une boucle la quantité (pour les paramètres), pour chaque élément qu'on souhaite acheter et fais ensuite un traitement similaire à la méthode nouvellVente
		//String numCommande, String nomElement, int quantite, int prix, String type
		String nomElement = element.getNom();
		//la quantité est donnée en paramètre
		int prix = element.getPrixAchat() * quantite;
		String type = "achat";
		Transaction transaction = new Transaction(nomElement, quantite, prix, type);
    	transactions.add(transaction);
	}
	
	public void totalTransaction(String type) {//type = achat/vente, s'il est appelé par la fonction getTotalAchats, alors le paramètre sera achat, la méthode parcours alors toutes les transactions 
												//et additionne celle qui sont des achats pour retourner le total des achats, même procédé pour les ventes
		
	}

}
