/*
* Population.java
* Manages a population of candidate tours
*/
import java.util.*;


@SuppressWarnings("unused")
public class Population {

    // Holds population of tours
    Tour[] tours;

    // Construit une popolation
    public Population(int Taillepopulation, boolean initialisee) {
        tours = new Tour[Taillepopulation];
        // si on a besoin d initialiser une une population
        if (initialisee) {
            // cree des individus
            for (int i = 0; i < Taillepopulation(); i++) {
                tours[i] = new Tour();
		tours[i].genererIndividu();
		//System.out.println(tours[i].toString());
                //saveTour(i, newTour);
            }
        }
	
    }
    
    // Saves a tour
    public void saveTour(int index, Tour tour) {
        tours[index] = tour;
    }
    
    // Recuperer une tournee dans une  population
    public Tour getTour(int index) {
        return tours[index];
    }

    // Recuperer la meilleure tournee dans une population
    public Tour getFittest() {
        Tour fittest = tours[0];
        // Parcourt les individus pour trouver le meilleur
        for (int i = 1; i < Taillepopulation(); i++) {
            if (fittest.getFitness() <= getTour(i).getFitness()) {
                fittest = getTour(i);
            }
        }
        return fittest;
    }

    // recuperer la taille de la population
    public int Taillepopulation() {
        return tours.length;
    }
}