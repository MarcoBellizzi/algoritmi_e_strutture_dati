package appelli;

import java.util.Scanner;

public class P1415a4v {

	static class Torre {

		int stato; // 0 vuoto   1 destra   2 sotto   3 sinistra   4 sopra
		int i, j;

		public Torre(int i, int j) {
			this.stato = 0;
			this.i = i;
			this.j = j;
		}

		public int getStato() { return stato; }
		public int getI() { return i; }
		public int getJ() { return j; }
		public void next() { stato++ ; }
		public void reset() { stato = 0; }

	}


	static Scanner input;

	static Torre[] soluzione;
	static int nOccupati;
	
	public static void main(String[] args) {

		input = new Scanner(System.in);

		String territory = input.next();

		while(territory.equals("territory")) {

			int nTerritory = input.nextInt();
			int n = input.nextInt();
			int m = input.nextInt();

			nOccupati = 0;
			String[][] matrice = new String[n][m];
			for(int i=0; i<n; i++) {
				for(int j=0; j<m; j++) {
					matrice[i][j] = input.next();
					if(matrice[i][j].equals("*")) {
						nOccupati++;
					}
				}
			}

			soluzione = new Torre[nOccupati];
			int k = 0;
			for(int i=0; i<n; i++) {
				for(int j=0; j<m; j++) {
					if(matrice[i][j].equals("*")) {
						soluzione[k++] = new Torre(i,j);
					}
				}
			}

			int sMin = nOccupati;

			int passi = 1;
			for(int i=0; i<nOccupati; i++) {
				passi = passi * 5;
			}

			for(int p=0; p<passi; p++) {
				soluzione[0].next();

				for(int a=0; a<nOccupati; a++) {
					if(soluzione[a].getStato() == 5) {
						soluzione[a].reset();
						if(a!=nOccupati-1) {
							soluzione[a+1].next();
						}
					}
				}
				
				boolean sorvegliatiTutti = true;
				int cont = 0;
				for(int i=0; i<nOccupati; i++) {
					if(soluzione[i].getStato() == 0) {
						if(!sorvegliato(i)) {
							sorvegliatiTutti = false;
						}
					}
					else {
						cont++;
					}
				}
				if(sorvegliatiTutti) {
					if(cont<sMin) {
						sMin = cont;
					}
				}

			}

			System.out.println("result " + nTerritory);
			System.out.println(sMin);
			
			territory = input.next();
			
		}

	}
	
	public static boolean sorvegliato(int t) {
		int i = soluzione[t].getI();
		int j = soluzione[t].getJ();
		
		for(int k=0; k<nOccupati; k++) {
			if(soluzione[k].getI() == i && soluzione[k].getJ() == j+1 && soluzione[k].getStato() == 3)
				return true;
			if(soluzione[k].getI() == i && soluzione[k].getJ() == j-1 && soluzione[k].getStato() == 1)
				return true;
			if(soluzione[k].getI() == i+1 && soluzione[k].getJ() == j && soluzione[k].getStato() == 4)
				return true;
			if(soluzione[k].getI() == i-1 && soluzione[k].getJ() == j && soluzione[k].getStato() == 2)
				return true;
		}
		return false;
	}
	

}
