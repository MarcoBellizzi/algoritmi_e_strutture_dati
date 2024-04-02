package appelli;

import java.util.ArrayList;
import java.util.Scanner;

public class Appello180212 {

	static class Studente {
		int numStudente;
		ArrayList<Integer> conoscenti;

		public Studente(int s) {
			numStudente = s;
			conoscenti = new ArrayList<Integer>();
		}

		public int getNumStudente() {
			return numStudente;
		}

		public ArrayList<Integer> getConoscenti() {
			return conoscenti;
		}

		public void add(int s) {
			conoscenti.add(s);
		}

	}

	static Scanner input;

	static int nStudenti;
	static Studente[] studenti;
	
	public static void main(String[] args) {

		input = new Scanner(System.in);

		int n = input.nextInt();
		int m = input.nextInt();
		nStudenti = input.nextInt()+1;

		studenti = new Studente[nStudenti];
		for(int i=0; i<nStudenti-1; i++) {
			studenti[i] = new Studente(input.nextInt());
			input.next();
			String cast = input.nextLine();
			int corrente = 0;
			for(int j=1; j<cast.length(); j++) {
				if(cast.charAt(j) != ' ') {
					corrente *= 10;
					switch(cast.charAt(j)) {
					case '1' : {
						corrente += 1;
						break;
					}
					case '2' : {
						corrente += 2;
						break;
					}
					case '3' : {
						corrente += 3;
						break;
					}
					case '4' : {
						corrente += 4;
						break;
					}
					case '5' : {
						corrente += 5;
						break;
					}
					case '6' : {
						corrente += 6;
						break;
					}
					case '7' : {
						corrente += 7;
						break;
					}
					case '8' : {
						corrente += 8;
						break;
					}
					case '9' : {
						corrente += 9;
						break;
					}
					}
				}
				else {
					studenti[i].add(corrente);
					corrente = 0;
				}
			}
			studenti[i].add(corrente);
		}
		studenti[nStudenti-1] = new Studente(0); // banco vuoto
		
		int[][] sol = new int[n][m];
		for(int i=0; i<n; i++) {
			for(int j=0; j<m; j++) {
				sol[i][j] = 0;
			}
		}
		
		if(risolvi(sol,0,0)) {
			System.out.println("SI");
			for(int i=0; i<n; i++) {
				for(int j=0; j<m; j++) {
					System.out.print(sol[i][j] + " ");
				}
				System.out.println();
			}
		}
		else {
			System.out.println("NO");
		}


	}

	public static boolean risolvi(int[][] sol, int i, int j) {
		
		for(int k=0; k<nStudenti; k++) {
			if(canAdd(studenti[k].getNumStudente(), sol, i, j)) {
				sol[i][j] = studenti[k].getNumStudente();
				if(completo(sol)) {
					return true;
				}
				else {
					int iNext, jNext;
					if(j == sol[0].length-1) {
						if(i == sol.length-1) {
							return false;
						}
						iNext = i+1;
						jNext = 0;
					}
					else {
						iNext = i;
						jNext = j+1;
					}
					if(risolvi(sol,iNext,jNext)) { 
						return true;
					}
					else {
						sol[i][j] = 0;
					}
				}
			}
		}
		return false;
	}
	
	public static boolean canAdd(int x, int[][] sol, int i, int j) {
		if(x == 0) {
			return true;
		}
		for(int k=0; k<sol.length; k++) {
			for(int l=0; l<sol[0].length; l++) {
				if(sol[k][l] == x) {
					return false;
				}
			}
		}
		for(int k=-1; k<2; k++) {
			for(int l=-1; l<2; l++) {
				if(i+k>=0 && i+k<sol.length && j+l>=0 && j+l<sol[0].length) {
					int n = -1;
					for(int p=0; p<nStudenti-1; p++) {
						if(studenti[p].getNumStudente() == x) {
							n = p;
						}
					}
					for(int conoscente : studenti[n].getConoscenti()) {
						if(sol[i+k][j+l] == conoscente) {
							return false;
						}
					}
				}
			}
		}
		return true;
	}
	
	public static boolean completo(int[][] sol) {
		boolean[] presenti = new boolean[nStudenti-1];
 		for(int i=0; i<nStudenti-1; i++) {
			presenti[i] = false;
			for(int k=0; k<sol.length; k++) {
				for(int l=0; l<sol[0].length; l++) {
					if(studenti[i].getNumStudente() == sol[k][l]) {
						presenti[i] = true;
					}
				}
			}
			if(!presenti[i]) {
				return false;
			}
		}
		return true;
	}
	
}






















