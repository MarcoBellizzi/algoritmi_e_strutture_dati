package appelli;

import java.util.Scanner;

import strutture.Grafo;

public class Appello190227 {

	static Scanner input;

	public static void main(String[] args) {

		input = new Scanner(System.in);

		int nZone = input.nextInt();    // nodi
		int nCollegamenti = input.nextInt();     // archi
		int nCollMin = input.nextInt();

		Grafo grafo = new Grafo(nZone);
		for(int i=0; i<nCollegamenti; i++) {
			int coll1 = input.nextInt();
			input.next();
			int coll2 = input.nextInt();
			grafo.setArco(coll1-1, coll2-1, true);
			grafo.setArco(coll2-1, coll1-1, true);
		}

		boolean soluzione = false;

		int[] divisione = new int[nZone];
		for(int i=0; i<nZone; i++) {
			divisione[i] = 0;
		}

		int passi = 1;
		for(int i=0; i<nZone; i++) {
			passi = passi * 2;      // passi = 2 alla n
		}

		for(int i=0; i<passi; i++) {
			divisione[0]++;

			for(int a=0; a<nZone; a++) {
				if(divisione[a] == 2) {
					divisione[a] = 0;
					if(a!=nZone-1) {
						divisione[a+1]++;
					}
				}
			}


			boolean sol = true;

			int[] nCollegamentiDiv = new int[2];

			for(int d=0; d<2; d++) {

				nCollegamentiDiv[d] = 0;

				for(int ii=0; ii<nZone; ii++) {

					for(int jj=ii+1; jj<nZone; jj++) {

						if(divisione[ii]==d && divisione[jj]==d && grafo.getArco(ii, jj)) {
							nCollegamentiDiv[d]++;

							for(int kk=0; kk<nZone; kk++) {
								if(grafo.getArco(ii, kk) && grafo.getArco(jj, kk) && divisione[kk]==d) {
									sol = false;
								}
							}
						}

					}
				}

				if(nCollegamentiDiv[d]/2 < nCollMin) {
					sol = false;
				}

				System.out.print(nCollegamentiDiv[d]);
				System.out.print(" ");
			}

			System.out.println();

			if(sol) {
				soluzione = true;
			}

		}

		if(soluzione) {
			System.out.println("SI");
		}
		else {
			System.out.println("NO");
		}

	}

}










