import java.util.*;

@SuppressWarnings("unused")
public class GA {

    /* GA parameters */
    private static final double mutationRate = 0.015;
    private static final int tournoiSize = 5;
    private static final boolean elitism = true;

    // Evolue une population 
    public static Population evoluerPopulation(Population pop) {
	//creation d'une population vide
        Population newPopulation = new Population(pop.Taillepopulation(), false);

        // Garder notre mailleur individu si elitism est active
        int elitismOffset = 0;
        if (elitism) {
            newPopulation.tours[0] =  pop.getFittest();
	    //System.out.println(newPopulation.tours[0].toString());

            elitismOffset = 1;
        }

        // Croisement population
        //Boucle sur la nouvelle population et cree des individus a partir de la population actulle
        for (int i = elitismOffset; i < newPopulation.Taillepopulation(); i++) {
            // Selection des parents
            Tour parent1 = Selection(pop);
            Tour parent2 = Selection(pop);

            // Croisement des parents
            Tour fils = croisement(parent1, parent2);

            // Ajoute un fils a la nouvelle population
            newPopulation.tours[i] =  fils;
        }
	//System.out.println(newPopulation.tours[0].toString());
	//System.out.println(newPopulation.tours.length);
	
        // Faire un changement aléatoire dans dans la génétique de chaque individu
        for (int i = elitismOffset; i < newPopulation.Taillepopulation(); i++) {
            mutation(newPopulation.getTour(i));
        }
	

        return newPopulation;
    }

    // Appliquée sur deux parents et cree un fils
    public static Tour   croisement(Tour parent1, Tour parent2) {
        // cree un fils
        Tour fils = new Tour();

        // obtenir les deux indices debut et fin pour la tournée parent1
        int startPos = (int) (Math.random() * parent1.Tailletour());
        int endPos = (int) (Math.random() * parent1.Tailletour());

        // Ajoute les ville visitées dans la tournée parents dans notre fils
        for (int i = 0; i < fils.Tailletour(); i++) {
            // Si la position de départ est inférieure à la fin
            if (startPos < endPos && i > startPos && i < endPos) {
                fils.setVille(i, parent1.getVille(i));
            } // Si notre position de départ est plus grande
            else if (startPos > endPos) {
                if (!(i < startPos && i > endPos)) {
                    fils.setVille(i, parent1.getVille(i));
                }
            }
        }

        // Boucler sur la tournée parent2
        for (int i = 0; i < parent2.Tailletour(); i++) {
            // Si le fils contient pas la ville à l'indice i, on l'ajoute
            if (!fils.contientVille(parent2.getVille(i))) {
                // Boucle su la tournée fils
                for (int ii = 0; ii < fils.Tailletour(); ii++) {
                    // Teste si la case à l'indice ii est vide à ce moment là on ajoute la ville
                    if (fils.getVille(ii) == null) {
                        fils.setVille(ii, parent2.getVille(i));
                        break;
                    }
                }
            }
        }
        return fils;
    }

    // effectuer un changement aléatoire dans une tournée
    private static void mutation(Tour tour) {
        // Boucle à travers les villes
        for(int tourPos1=0; tourPos1 < tour.Tailletour(); tourPos1++){
            // Appliquer de taux de mutation
            if(Math.random() < mutationRate){
                // Obtenir une seconde position aléatoire dans la tournée
                int tourPos2 = (int) (tour.Tailletour() * Math.random());

                // Obtenir les deux villes qui correcpondent à la position tourPos1 et tourPos2
                Ville ville1 = tour.getVille(tourPos1);
                Ville ville2 = tour.getVille(tourPos2);

                // Faire un swap des deux villes
                tour.setVille(tourPos2, ville1);
                tour.setVille(tourPos1, ville2);
            }
        }
    }

    // Selectionne les candidats pour le croiesement
    private static Tour Selection(Population pop) {
        // Cree un tournoi à partir  de la population de départ
        Population tournoi = new Population(tournoiSize, false);
        // Pour chaque place dans le tournoi on ajoute un idividu au hasard
        
        for (int i = 0; i < tournoiSize; i++) {
            int randomId = (int) (Math.random() * pop.Taillepopulation());
            tournoi.tours[i] = pop.getTour(randomId);
        }
        // Obtenir la tourée meilleure dans le tournoi
        Tour fittest = tournoi.getFittest();
        return fittest;
    }
}
