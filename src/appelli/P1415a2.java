package appelli;

import java.util.Scanner;

public class P1415a2 {

	static Scanner input;

	public static void main(String[] args) {

		input = new Scanner(System.in);

		String test = input.next();

		while(test.equals("test")) {

			int nTest = input.nextInt();

			int nStringhe = input.nextInt();
			int n = input.nextInt();
			int m = input.nextInt();

			String[] stringhe = new String[nStringhe];
			int[] pesi = new int[nStringhe];

			for(int i=0; i<nStringhe; i++) {
				stringhe[i] = input.next();
				input.next();
				pesi[i] = input.nextInt();
			}

			String[][] matrice = new String[n][m];
			for(int i=0; i<n; i++) {
				for(int j=0; j<m; j++) {
					matrice[i][j] = input.next();
				}
			}

			int iMax = 0;
			int jMax = 0;

			int sommaMax = 0;
			int somma = 0;

			for(int i=0; i<n; i++) {
				for(int j=0; j<m; j++) {

					String stringaCorrente = matrice[i][j];
					int pesoCorrente = 0;
					for(int p=0; p<nStringhe; p++) {
						if(stringhe[p].equals(stringaCorrente)) {
							pesoCorrente = pesi[p];
						}
					}

					int N = n-i;
					int M = m-j;
					int nMax = N;
					if(M<N) nMax = M;

					somma = 0;

					for(int k=1; k<=nMax; k++) {

						boolean quadrato = true;

						for(int i2=0; i2<k; i2++) {
							for(int j2=0; j2<k; j2++) {
								if(!stringaCorrente.equals(matrice[i+i2][j+j2])) {
									quadrato = false;
								}
							}
						}

						if(quadrato) {
							somma = pesoCorrente * k * k;
							if(somma > sommaMax) {
								sommaMax = somma;
								iMax = i;
								jMax = j;
							}
						}

					}

				}
			}

			System.out.println("result " + nTest);
			System.out.println("(" + iMax + "," + jMax + ")");
			System.out.println(sommaMax);

			test = input.next();
			
		}

	}

}
