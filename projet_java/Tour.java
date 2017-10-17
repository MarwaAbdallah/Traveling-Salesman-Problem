import java.util.*;

public class Tour{

    // une tounee de villes
    private ArrayList<Ville> tour = new ArrayList<Ville>();
    // Cache
    private double fitness = 0;
    private int distance = 0;
    
    // Constructeur initialisant la liste a null
    public Tour(){
        for (int i = 0; i < ListeVille.nombreDeVilles(); i++) {
            tour.add(null);
        }
    }
    
    public Tour(ArrayList<Ville> tour){
        this.tour = tour;
    }

    // Cree des individus aleatoirement
    public void genererIndividu() {
        // Boucle dans toutes nos villes de destination et les ajouter à notre tour 

        for (int villeIndex = 0; villeIndex < ListeVille.nombreDeVilles(); villeIndex++) {
          setVille(villeIndex, ListeVille.getVille(villeIndex));
        }
        // reorganiser une tournee aleatoirement
        Collections.shuffle(tour);

    }

    // recupere une ville dans une tournee
    public Ville getVille(int tourPosition) {
        return tour.get(tourPosition);
    }

    // Definie une ville dans une position de tour
    public void setVille(int tourPosition, Ville ville) {
        tour.set(tourPosition, ville);
        fitness = 0;
        distance = 0;
    }
    
    // obtenir la fitness d'une tournée
    public double getFitness() {
        if (fitness == 0) {
            fitness = 1/(double)getDistance();
        }
        return fitness;
    }
    
    // Donne la distance totale d'une tournée
    public int getDistance(){
        if (distance == 0) {
            int tourDistance = 0;
            // Boucle à travers les villes de notre tournée
            for (int villeIndex=0; villeIndex < Tailletour(); villeIndex++) {
                // Obtenir l'indice de la ville courante
                Ville fromville = getVille(villeIndex);
                // prochaine ville à visiter
                Ville destinationville;
		//Si on est pas à la dernière ville à visiter
                if(villeIndex+1 < Tailletour()){
                    //distance+= w[getVille(villeIndex)][getVille(villeIndex+1)];
		    //La ville à visiter ça va etre la ville à l'indice villeIndex + 1
		    destinationville = getVille(villeIndex+1);
                }            
		else{
                    destinationville = getVille(0);
                }
                // Donne la destination entre deux villes
                tourDistance += main.matrice[fromville.valeur][destinationville.valeur];
            }
            distance = tourDistance;
        }
        return distance;
    }

    // Retourne le nombre de ville de notre tournée
    public int Tailletour() {
        return tour.size();
    }
    
    // verifie que si la tournee contient une ville
    public boolean contientVille(Ville ville){
        return tour.contains(ville);
    }
    
    //@Override
    public String toString() {
        String chaine = "|";
        for (int i = 0; i < Tailletour(); i++) {
            chaine += getVille(i).valeur+"|";
        }
        return chaine;
    }
}