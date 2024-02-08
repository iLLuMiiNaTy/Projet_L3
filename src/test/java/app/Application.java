package app;

public class Application {
    
    public static void main(String[] args) {
		FichierCSV csv = new FichierCSV();
		csv.chargerDonnees();
		System.out.println("##########################");
		System.out.println("AFFICHAGE ELEMENTS");
		System.out.println("##########################\n");
		csv.afficherElements();
		System.out.println("\n##########################");
		System.out.println("AFFICHAGE CHAINES");
		System.out.println("##########################");
		csv.afficherChaines();
        csv.sauvegarderElements();
	}
}