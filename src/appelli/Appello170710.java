package appelli;

import java.util.Scanner;

public class Appello170710 {

	static Scanner input;
	
	static int iMax, jMax, distanzaMax, n, m;
	
	static char[][] matrice;
	
	public static void main(String[] args) {
		
		input = new Scanner(System.in);
		
		iMax = 0;
		jMax = 0;
		distanzaMax = 0;
		
		n = input.nextInt();
		m = input.nextInt();
		
		String vuoto = input.nextLine();
		System.out.print(vuoto);
		
		matrice = new char[n][m];
		for(int i=0; i<n; i++) {
			String pars = input.nextLine();
			for(int j=0; j<m; j++) {
				matrice[i][j] = pars.charAt(j);
			}
		}
		
		for(int i=0; i<n; i++) {
			for(int j=0; j<m; j++) {
				if(matrice[i][j] == '-') {
					calcolaDistanza(i,j);
				}
			}
		}
		
		System.out.println("<" + iMax + "," + jMax + ">");

	}
	
	public static void calcolaDistanza(int i, int j) {
		int d = 0;
		boolean trovato = false;
		while(!trovato) {
			d++;
			for(int k=0; k<d*2+1; k++) {    // riga in alto
				if(i-d >= 0 && j-d+k >=0 && j-d+k < m) {
					if(matrice[i-d][j-d+k] == '#') {
						trovato = true;
					}
				}
			}
			for(int k=0; k<d*2+1; k++) {    // colonna a destra
				if(j+d < m && i-d+k >=0 && i-d+k < n) {
					if(matrice[i-d+k][j+d] == '#') {
						trovato = true;
					}
				}
			}
			for(int k=0; k<d*2+1; k++) {    // riga in basso
				if(i+d < n && j-d+k >= 0 && j-d+k < m) {
					if(matrice[i+d][j-d+k] == '#') {
						trovato = true;
					}
				}
			}
			for(int k=0; k<d*2+1; k++) { 
				if(j-d >= 0 && i-d+k >=0 && i-d+k < n) {
					if(matrice[i-d+k][j-d] == '#') {
						trovato = true;
					}
				}
			}
		}
		if(d > distanzaMax) {
			distanzaMax = d;
			iMax = i;
			jMax = j;
		}
	}

}




















