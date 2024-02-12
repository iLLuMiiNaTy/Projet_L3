package app;

public class Application {
    
    public static void main(String[] args) {
		FichierCSV csv = new FichierCSV();
		GestionnaireProduction gestionnaire = new GestionnaireProduction();
		csv.chargerDonnees();
		
		System.out.println("##########################");
		System.out.println("AFFICHAGE ELEMENTS");
		System.out.println("##########################\n");
		csv.afficherElements();
		
		System.out.println("\n##########################");
		System.out.println("AFFICHAGE CHAINES");
		System.out.println("##########################");
		csv.afficherChaines();
		
		System.out.println("\n##########################");
		System.out.println("AFFICHAGE COMMANDES");
		System.out.println("##########################");
		csv.afficherCommandes();
		
        csv.sauvegarderElements();
        
       
        //appel de la méthode ajouterChaine
        for (ChaineDeProduction chaine : FichierCSV.getChaines()) {
            gestionnaire.ajouterChaine(chaine);
        }
        
        //appel de la méthode pour modifier le niveau d'activation
        gestionnaire.ajusterNiveauActivation("C001", 3);
        System.out.println("\nChaînes de production après ajustement :");
        for (ChaineDeProduction chaine : FichierCSV.getChaines()) {
            System.out.println(chaine);
        }
    }
        
	}
