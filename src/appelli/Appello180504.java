package appelli;

import java.util.Scanner;

public class Appello180504 {

	static Scanner input;

	static int[][] matrice;

	static int nSettori;
	static int k;

	public static void main(String[] args) {

		input = new Scanner(System.in);

		int dim = input.nextInt();
		nSettori = input.nextInt();
		input.nextInt();
		k = input.nextInt();

		matrice = new int[dim][dim];
		
		int[][] sol = new int[dim][dim];
		for(int i=0; i<dim; i++) {
			for(int j=0; j<dim; j++) {
				matrice[i][j] = input.nextInt();
				sol[i][j] = 0;
			}
		}
		
		if(solve(sol,0,0)) {
			for(int i=0; i<dim; i++) {
				for(int j=0; j<dim; j++) {
					if(sol[i][j] == 1) {
						System.out.print("# ");
					}
					else {
						System.out.print(matrice[i][j] + " ");
					}
				}
				System.out.println();
			}
		}
		else {
			System.out.println("IMPOSSIBILE");
		}


	}

	public static boolean solve(int[][] sol, int i, int j) {
		int iNext, jNext;
		if(j == sol[0].length-1) {
			if(i == sol[0].length -1) {
				return false;   // lofio (ultima posizione)
			}
			iNext = i+1;
			jNext = 0;
		}
		else {
			iNext = i;
			jNext = j+1;
		}
		
		if(canAdd(i,j,sol)) {
			sol[i][j] = 1;
			if(completo(sol)) {
				return true;
			}
			else {
				if(solve(sol,iNext,jNext)) { 
					return true;
				}
				else {
					sol[i][j] = 0;
					if(solve(sol,iNext,jNext)) { 
						return true;
					}
				}
			}
		}
		if(solve(sol,iNext,jNext)) { 
			return true;
		}
		return false;
	}

	public static boolean canAdd(int i, int j, int[][] sol) {
		int contR = 0;
		int contC = 0;
		for(int k=0; k<sol[0].length; k++) {
			if(sol[i][k] == 1) {
				contR++;
			}
			if(sol[k][j] == 1) {
				contC++;
			}
		}
		if(contR >= k || contC >= k) {
			return false;
		}
		int contS = 0;
		for(int k=0; k<sol[0].length; k++) {
			for(int l=0; l<sol[0].length; l++) {
				if(sol[k][l] == 1 && matrice[k][l] == matrice[i][j]) {
					contS++;
				}
			}
		}
		if(contS >= k) {
			return false;
		}
		return true;
	}
	
	public static boolean completo(int[][] sol) {
		int[] transSettore = new int[nSettori];
		for(int i=0; i<nSettori; i++) {
			transSettore[i] = 0;
		}
		
		for(int i=0; i<sol[0].length; i++) {
			for(int j=0; j<sol[0].length; j++) {
				if(sol[i][j] == 1) {
					transSettore[matrice[i][j]]++;
				}
			}
		}
		
		for(int i=0; i<nSettori; i++) {
			if(transSettore[i] != k) {
				return false;
			}
		}
		return true;
	}
	
	public static void stampa(int[][] sol) {
		for(int i=0; i<sol[0].length; i++) {
			for(int j=0; j<sol[0].length; j++) {
				System.out.print(sol[i][j] + " ");
			}
			System.out.println();
		}
	}

}














