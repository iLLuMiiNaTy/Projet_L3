package app;

import java.util.HashMap;
import java.util.Map;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
/**
 * Classe qui permet de gérer les finances de l'entreprise
 * 
 * Cette classe contient une liste de transactions et de commandes déjà traitées
 */
public class GestionnaireFinance {
	
	private static ObservableList<Transaction> listeTransactions = FXCollections.observableArrayList();
	private static Map<String, Commande> commandesDejaTraitees = new HashMap<>();

	/**
	 * Méthode qui permet de créer une nouvelle vente
	 * et de l'ajouter à la liste des transactions
	 * 
	 * @param com : la commande à traiter
	 */
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

	/**
	 * Méthode qui permet d'afficher les ventes
	 */
	public void afficherVentes() {
		System.out.println("\nAffichage des ventes :");
        for (Transaction transaction : listeTransactions) {
        	System.out.println("\n");
        	System.out.println(transaction);
        }
    }

	/**
	 * Méthode qui permet d'ajouter les achats à la liste des transactions
	 */
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
	/**
	 * Méthode qui calcul le total des transactions
	 * @param type
	 * @return
	 */
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

	/**
	 * Méthode qui permet de calculer le total des achats
	 * 
	 * @return
	 */
	public static int getTotalAchat() {
		int resultat;
		resultat = totalTransaction("Achat");
		System.out.println(resultat);
		return resultat;
	}

	/**
	 * Méthode qui permet de calculer le total des ventes
	 * 
	 * @return
	 */
	public static int getTotalVente() {
		int resultat;
		resultat = totalTransaction("Vente");
		System.out.println(resultat);
		return resultat;
	}
	/**
	 * Méthode qui permet de créer une nouvelle vente en fontion de la commande réalisée
	 */
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

	/**
	 * Méthode qui permet de calculer le résultat financier
	 * 
	 * @return
	 */
	public static int calculerResultatFinancier() {
		int achat = getTotalAchat();
		int vente = getTotalVente();
		return vente - achat;
	}

	/**
	 * Méthode qui permet de retourner la liste des transactions
	 * 
	 * @return
	 */
	public static ObservableList<Transaction> getListeTransaction(){
		return listeTransactions;
	}
}
