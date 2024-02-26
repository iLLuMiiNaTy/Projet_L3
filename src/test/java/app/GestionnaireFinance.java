package app;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class GestionnaireFinance {
	
	private static ObservableList<Transaction> listeTransactions = FXCollections.observableArrayList();
	private static Map<String, Commande> commandesDejaTraitees = new HashMap<>();
	
	public static void test() {
		for (Transaction t : listeTransactions) {
			System.out.println("Transaction : " + t);
		}
		int res = calculerResultatFinancier();
		System.out.println("\n Résultat financier : " + res);
		FichierCSV.sauvegarderElements();
	}
	
	public static void nouvelleVente(Commande com) {
    	String codeProduit = com.getCodeProduit();
    	Element element = GestionnaireStock.trouverElementParCode(codeProduit);
    	String nomElement = element.getNom();
    	float quantite = com.getQuantite();
    	int prix = (int) (element.getPrixVente()* quantite);
    	String type = "Vente";
    	Transaction transaction = new Transaction(nomElement, quantite, prix, type);
    	listeTransactions.add(transaction);
    }
	
	public void afficherVentes() {
		System.out.println("\nAffichage des ventes :");
        for (Transaction transaction : listeTransactions) {
        	System.out.println("\n");
        	System.out.println(transaction);
        }
    }
	
	public static void nouvelAchat(Element element, float quantite) {//récupère depuis une boucle la quantité (pour les paramètres), pour chaque élément qu'on souhaite acheter et fais ensuite un traitement similaire à la méthode nouvellVente
		if (quantite != 0) {
			String nomElement = element.getNom();
			//la quantité est donnée en paramètre
			int prix = (int) (element.getPrixAchat() * quantite);
			String type = "Achat";
			Transaction transaction = new Transaction(nomElement, quantite, prix, type);
			listeTransactions.add(transaction);
		}
	}
	
	public static int totalTransaction(String type) {//type = achat/vente, s'il est appelé par la fonction getTotalAchats, alors le paramètre sera achat, la méthode parcours alors toutes les transactions 
												//et additionne celle qui sont des achats pour retourner le total des achats, même procédé pour les ventes
		int resultat = 0;
		for (Transaction transaction : listeTransactions) {
			if (transaction.getType().equals(type)) {
				resultat += transaction.getPrix();
			}
		}
		return resultat;
	}
	
	public static int getTotalAchat() {
		int resultat;
		resultat = totalTransaction("Achat");
		System.out.println(resultat);
		return resultat;
	}
	
	public static int getTotalVente() {
		int resultat;
		resultat = totalTransaction("Vente");
		System.out.println(resultat);
		return resultat;
	}

	public static void ajouterCommandeListeTransaction() {
		for (Commande c : GestionnaireCommande.getListeCommande()) {
			if (c.getStatut()) {
				if (!commandesDejaTraitees.containsKey(c.getNumeroCommande())) {
	                nouvelleVente(c);
	                commandesDejaTraitees.put(c.getNumeroCommande(), c);
	            }
			}
		}
	}
	
	public static int calculerResultatFinancier() {
		int achat = getTotalAchat();
		int vente = getTotalVente();
		return vente - achat;
	}
	
	public static ObservableList<Transaction> getListeTransaction(){
		return listeTransactions;
	}

}
