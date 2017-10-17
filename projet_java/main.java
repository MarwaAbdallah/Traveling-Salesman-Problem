/*     Welcome !
 *  This project was realized in 2014, with my two team mates Ahcene and Dian (which I thank really much
 *  Please run the class: main.java
 *  The initial output presents the initial population: list of ordered cities, 
 *  with the performance associated
 *  The final output represents the best solution found after a maximum of 1000 iterations
 *  (list of cities, distance)
 *  
*/


import java.util.*;
import java.io.*;
@SuppressWarnings("unused")
public class main{
    public static int [][] matrice;
    public static void main(String []args){
		int n=100;
		int x,i,j;
		int indice;
		matrice = new int[n][n];
		for(indice = 0; indice<n; indice++){
		    ListeVille.addVille(new Ville(indice));
		}
			for (i=0; i<n; i++){
			    for(j=0;j<n;j++){
					if (i==j){
					    matrice[i][j]=101;
					}
				else{
					    x = (int)(Math.random()*100);
					    matrice[i][j] = x+1;
					    matrice[j][i] = x+1;
					}
			    }
		    }
		
	
			//afficher(matrice);
			long tempsDebut = System.currentTimeMillis();
			Population pop = new Population(n, true);
			
			System.out.println("Solution initiale : " + pop.getFittest());
			System.out.println("Distance initiale : " + pop.getFittest().getDistance());
			//pop = GA.evoluerPopulation(pop);
			//Evaluer populationde 100 générations
			
			for (i = 0; i <1000 ; i++) {
			    pop = GA.evoluerPopulation(pop);
			}
			
			System.out.println("\n Finished \n");
			System.out.println("Distance finale: " + pop.getFittest().getDistance());
			System.out.println("Solution:");
			System.out.println(pop.getFittest());
			long tempsFin = System.currentTimeMillis();
			
			float seconds = (float)(tempsFin - tempsDebut)/1000 ;
			System.out.println("\n Opération effectuée en: "+ Float.toString(seconds) + " secondes.");
		   }

    public static void afficher(int [][] matrice){
	int i, j;
	for (i=0; i<matrice.length; i++){
	    for(j=0;j<matrice.length;j++){	
		System.out.print(matrice[i][j]);
		System.out.print(" | ");
	    }
	    System.out.println("\n");
	}
    }
}
