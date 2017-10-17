import java.util.*;

public class ListeVille{

    // tournee de villes
    private static ArrayList<Ville> destinationVilles = new ArrayList<Ville>();

    // Ajoute une ville
    public static void addVille(Ville ville) {
        destinationVilles.add(ville);
    }
    
    // Recupere une ville a partir de index
    public static Ville getVille(int index){
        return destinationVilles.get(index);
    }
    
    // donne le nombre de villes
    public static int nombreDeVilles(){
        return destinationVilles.size();
    }
}