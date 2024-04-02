package appelli;

import java.util.Scanner;

public class Esame {

	static Scanner input;

	static boolean[][] matrice;

	static boolean[] visitati, soluzione;

	static int n, z;

	public static void main(String[] args) {

		input = new Scanner(System.in);
		n = input.nextInt();
		z = input.nextInt();

		matrice = new boolean[n][n];
		for(int i=0; i<n; i++) {
			for(int j=0; j<n; j++) {
				matrice[i][j] = false;
			}
		}

		int from = input.nextInt();
		int to;
		while(from != -1) {
			input.next();
			to = input.nextInt();
			matrice[from-1][to-1] = true;
			from = input.nextInt();
		}

		visitati = new boolean[n];
		ripristina();

		soluzione = new boolean[n];
		for(int i=0; i<n; i++) {
			soluzione[i] = false;
		}

		if(risolvi(0)) {
			System.out.println("SI");
		}
		else {
			System.out.println("NO");
		}

	}
	
	


	public static boolean risolvi(int x) {

//		stampa();

		if(x == n) {
			return false;
		}

		for(int k=0; k<n; k++) {
			ripristina();
			if(!soluzione[k] && ciclo(k,k)) {
				
				if(nonPosso()) {   // numero di stazioni spente >= z
					return false;
				}
				
				soluzione[x] = true;    // spengo la stazione
				
				if(noCicli()) {
					return true;
				}
				else {
					if(risolvi(x+1)) {
						return true;
					}
					soluzione[x] = false;
					if(risolvi(x+1)) {
						return true;
					}
					return false;
				}
			}
		}
		return true;
	}

	public static boolean ciclo(int x, int j) {

		visitati[x] = true;
		for(int i=0; i<n; i++) {
			if(matrice[x][i]) {
				if(i == j) {
					return true;
				}
				else {
					if(!visitati[i] && !soluzione[i]) {
						if(ciclo(i,j)) {
							return true;
						}
					}
				}
			}
		}
		return false;
	}

	public static boolean noCicli() {
		for(int i=0; i<n; i++) {
			ripristina();
			if(ciclo(i,i)) {
				return false;
			}
		}
		return true;
	}

	public static boolean nonPosso() {
		int cont = 0;
		for(int i=0; i<n; i++) {
			if(soluzione[i]) {
				cont++;
			}
		}
		if(cont >= z) {
			return true;
		}
		return false;
	}
	
	public static void ripristina() {
		for(int i=0; i<n; i++) {
			visitati[i] = false;
		}
	}

	public static void stampa() {
		for(int i=0; i<n; i++) {
			if(soluzione[i]) {
				System.out.print("1 ");
			}
			else {
				System.out.print("0 ");
			}
		}
		System.out.println();
	}

}






















