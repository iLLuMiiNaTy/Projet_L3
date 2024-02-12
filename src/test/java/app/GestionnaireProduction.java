package app;

import java.util.ArrayList;



public class GestionnaireProduction {

private ArrayList <ChaineDeProduction>chaine;
	
	public GestionnaireProduction(){
		chaine = new ArrayList<>();
	}
	
	public void ajouterChaine(ChaineDeProduction c) {
		chaine.add(c);
	}
	

	public void ajusterNiveauActivation(String codeChaine, int niveauActivation) {
		boolean chaineOK = false;
		for (ChaineDeProduction c: chaine) {
			if (c.getCode().equals(codeChaine)) {
				c.setNiveauActivation(niveauActivation);
				chaineOK = true;
				c.activer(niveauActivation);
			}
		}
		if (chaineOK = false) {
			System.out.println("Aucune chaîne de production trouvée avec le code : " + codeChaine);
		}
		
	}
	
	//méthode SimulerProduction
}
