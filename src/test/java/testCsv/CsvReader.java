package testCsv;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class CsvReader {

	public static void main(String[] args) {
		String path = "C:\\Users\\Nicolas\\Desktop\\Hack\\CSV\\Test\\chaines.csv\\"; // Remplacez par le chemin de votre fichier CSV
        String line;
        
        try (BufferedReader br = new BufferedReader(new FileReader(path))) {
            
            while ((line = br.readLine()) != null) { // Tant qu'il y a des lignes à lire
                
                // Utilisation de la virgule comme séparateur pour le fichier CSV
                String[] values = line.split(",");
                
                // Traitement des valeurs (exemple d'affichage)
                for (String value : values) {
                    System.out.print(value + " ");
                }
                System.out.println(); // Saut de ligne pour la prochaine entrée
                
            }
            
        } catch (IOException e) {
            e.printStackTrace();
        }

	}

}