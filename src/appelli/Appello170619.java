package appelli;

import java.util.Scanner;

public class Appello170619 {

	static Scanner input;
	
	static int n;
	static String[] città;
	
	public static void main(String[] args) {
		
		input = new Scanner(System.in);
		
		n = input.nextInt();
		int serbMax = input.nextInt();
		
		città = new String[n];
		
		for(int i=0; i<n; i++) {
			città[i] = input.next();
		}
		
		boolean[][] collegamenti = new boolean[n][n];
		for(int i=0; i<n; i++) {
			for(int j=0; j<n; j++) {				
				collegamenti[i][j] = false;
			}
		}
		
		while(input.hasNext()) {
			String c1 = input.next();
			if(c1.equals(".")) {   // rimuovere quando si esegue da terminale
				break;
			}
			input.next();
			String c2 = input.next();
			collegamenti[getPos(c1)][getPos(c2)] = true;
			collegamenti[getPos(c2)][getPos(c1)] = true;
		}
		
		int[] sol = new int[n];
		for(int i=0; i<n; i++) {
			sol[i] = 0;
		}
		
		int[] solOttima = new int[n];
		
		boolean[] coperte = new boolean[n];
		
		boolean ultima = false;
		while(!ultima) {
			sol[0]++;
			ultima = true;
			for(int i=0; i<n; i++) {
				if(sol[i] == 2) {
					sol[i] = 0;
					if(i != n-1) {
						sol[i+1]++;
					}
				}
				if(sol[i] == 0) {
					ultima = false;
				}
			}
			
			stampa(sol);
			ripristina(coperte);
			
			boolean buona = true;
			for(int i=0; i<n; i++) {
				if(sol[i] == 1) {
					coperte[i] = true;
				}
				else {
					for(int j=0; j<n; j++) {
						if(collegamenti[i][j] && sol[j]==1) {
							coperte[i] = true;
						}
					}
				}
				if(!coperte[i]) {
					buona = false;
				}
			}
			if(buona) {
				int cont = 0;
				for(int i=0; i<n; i++) {
					if(sol[i] == 1) {
						cont++;
					}
				}
				if(cont <= serbMax) {
					serbMax = cont;
					for(int i=0; i<n; i++) {
						solOttima[i] = sol[i];
					}
				}
			}
		}
		
		System.out.println(serbMax);
		for(int i=0; i<n; i++) {
			if(solOttima[i] == 1) {
				System.out.print(città[i] + " ");
			}
		}

	}
	
	public static int getPos(String cit) {
		for(int i=0; i<n ; i++) {
			if(città[i].equals(cit)) {
				return i;
			}
		}
		return -1;
	}

	public static void ripristina(boolean[] coperte) {
		for(int i=0; i<coperte.length; i++) {
			coperte[i] = false;
		}
	}
	
	public static void stampa(int[] sol) {
		for(int i=0; i<n; i++) {
			System.out.print(sol[i] + " ");
		}
		System.out.println();
	}
	
}


















