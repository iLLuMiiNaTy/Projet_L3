package app;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class GestionnaireProduction {

	ObservableList<ChaineDeProduction> listeChaine;
	
	public GestionnaireProduction(){
		listeChaine = FXCollections.observableArrayList();
	}
	
	public void ajouterChaine(ChaineDeProduction c) {
		listeChaine.add(c);
	}
	

	public void ajusterNiveauActivation(String codeChaine, SimpleIntegerProperty niveauActivation) {
		boolean chaineOK = false;
		for (ChaineDeProduction c: listeChaine) {
			if (c.getCode().equals(codeChaine)) {
				c.activer(niveauActivation);
				chaineOK = true;
			}
		}
		if (chaineOK = false) {
			System.out.println("Aucune chaîne de production trouvée avec le code : " + codeChaine);
		}
		
	}
	
	//méthode SimulerProduction
}



