package appelli;

import java.util.ArrayList;
import java.util.Scanner;

public class Appello171121 {

	static class Punto {
		int i, j;
		public Punto(int i, int j) {
			this.i = i;
			this.j = j;
		}

		public int getI() { return i; }

		public int getJ() { return j; }

		public boolean uguale(Punto p2) {
			return i == p2.getI() && j == p2.getJ();
		}

		public void stampa() {
			System.out.print("(" + i + "," + j + ")");
		}
	}

	static Scanner input;

	static int n;
	static int m;
	static char[][] matrice;

	static boolean[][] raggiunto;

	public static void main(String[] args) {

		input = new Scanner(System.in);

		n = input.nextInt();
		m = input.nextInt();
		matrice = new char[n][m];

		String vuoto = input.nextLine();
		System.out.println(vuoto);

		for(int i=0; i<n; i++) {
			String pars = input.nextLine();
			for(int j=0; j<m; j++) {
				matrice[i][j] = pars.charAt(j);
			}
		}

		ArrayList<Punto> punti = new ArrayList<Punto>();

		for(int i=0; i<n; i++) {
			for(int j=0; j<m; j++) {
				if(matrice[i][j] == '.') {
					if(intersezione(i,j)) {
						punti.add(new Punto(i,j));
					}
				}
			}
		}

		raggiunto = new boolean[n][m];

		ArrayList<ArrayList<Punto>> connessioni = new ArrayList<ArrayList<Punto>>();
		int cont = -1;
		for(Punto punto1 : punti) {
			connessioni.add(new ArrayList<Punto>());
			cont++;
			for(Punto punto2 : punti) {
				if(!punto1.uguale(punto2)) {
					ripristina();
					if(raggiungi(punto1.getI(),punto1.getJ(),punto2)) {
						connessioni.get(cont).add(punto2);
					}
				}
			}
		}

		for(int i=0; i<connessioni.size(); i++) {
			punti.get(i).stampa();
			System.out.print(" -> ");
			for(Punto punto : connessioni.get(i)) {
				punto.stampa();
			}
			System.out.println();
		}

	}

	public static boolean raggiungi(int i, int j, Punto p2) {
		raggiunto[i][j] = true;
		for(int k=0; k<4; k++) {
			boolean posCorretta = true;   // per non andare fuori dalla matrice
			int iNext = 0;
			int jNext = 0;
			if(k == 0) {   // su
				if(i == 0) {
					posCorretta = false;
				}
				iNext = i-1;
				jNext = j;
			}
			if(k == 1) {   // destra
				if(j == m-1) {
					posCorretta = false;
				}
				iNext = i;
				jNext = j+1;
			}
			if(k == 2) {   // giu
				if(i == n-1) {
					posCorretta = false;
				}
				iNext = i+1;
				jNext = j;
			}
			if(k == 3) {   // sinistra
				if(j == 0) {
					posCorretta = false;
				}
				iNext = i;
				jNext = j-1;
			}
			if(posCorretta) {
				if(matrice[iNext][jNext] == '.' && !raggiunto[iNext][jNext]) {
					if(iNext == p2.getI() && jNext == p2.getJ()) {
						return true;
					}
					else {
						if(raggiungi(iNext,jNext,p2)) {
							return true;
						}
					}
				}
			}
		}
		return false;
	}

	public static boolean intersezione(int i, int j) {
		int cont = 0;
		if(i>0) {
			if(matrice[i-1][j] == '.') {
				cont++;
			}
		}
		if(i<n-1) {
			if(matrice[i+1][j] == '.') {
				cont++;
			}
		}
		if(j>0) {
			if(matrice[i][j-1] == '.') {
				cont++;
			}
		}
		if(j<m-1) {
			if(matrice[i][j+1] == '.') {
				cont++;
			}
		}
		if(cont >= 3) {
			return true;
		}
		return false;
	}

	public static void ripristina() {
		for(int i=0; i<n; i++) {
			for(int j=0; j<m; j++) {
				raggiunto[i][j] = false;
			}
		}
	}

}


























