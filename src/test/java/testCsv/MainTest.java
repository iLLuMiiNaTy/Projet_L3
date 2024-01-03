package testCsv;



public class MainTest {

	public static void main(String[] args) {
		Application application = new Application();
		application.chargerDonnees();
		System.out.println("##########################");
		System.out.println("AFFICHAGE ELEMENTS");
		System.out.println("##########################");
		application.afficherElements();
		System.out.println("##########################");
		System.out.println("AFFICHAGE CHAINES");
		System.out.println("##########################");
		application.afficherChaines();
		

	}



}
