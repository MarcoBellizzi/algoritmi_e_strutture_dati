package appelli;

import java.util.Scanner;

public class P1516a3 {                     //  INCOMPLETO 

	static Scanner input;

	public static void main(String[] args) {

		input = new Scanner(System.in);

		String assembly = input.next();

		while(assembly.equals("assembly")) {

			input.next();   // line
			int nAssembly = input.nextInt();

			int n = input.nextInt();
			int maxPerCella = input.nextInt();

			String[] parole = new String[n];
			String[][] dipendenze = new String[n][n];

			for(int i=0; i<n; i++) {
				for(int j=0; j<n; j++) {
					dipendenze[i][j] = "";				
				}
			}

			for(int i=0; i<n; i++) {
				parole[i] = input.next();
				input.next();   // freccia
				String sDipendenze = input.nextLine();

				int dip = 0;
				String parola = "";
				for(int j=2; j<sDipendenze.length()-1; j++) {
					if(sDipendenze.charAt(j) == ' ') {
						dipendenze[i][dip++] = parola;
						parola = "";
					}
					else {
						parola += sDipendenze.charAt(j);
					}
				}
				dipendenze[i][dip] = parola;
			}

			String[][] soluzione = new String[n][maxPerCella];
			for(int i=0; i<n; i++) {
				for(int j=0; j<maxPerCella; j++) {
					soluzione[i][j] = "";
				}
			}

			boolean[] posizionato = new boolean[n];
			for(int i=0; i<n; i++) {
				posizionato[i] = false;
			}

			int riga = 0;
			int colonna = 0;
			for(int r=0; r<n; r++) {
				for(int i=0; i<n; i++) {  // scorro le parole per posizionarle
					if(!posizionato[i]) {
						boolean possoMetterlo = true;
						for(int d=0; d<n; d++) {
							if(!dipendenze[i][d].equals("")) {
								for(int p=0; p<n; p++) {
									if(parole[p].equals(dipendenze[i][d])) {
										System.out.println("trovato la dipendenza");
										if(!posizionato[p]) {
											possoMetterlo = false;
										}
									}
								}
							}
						}
						if(possoMetterlo) {
							soluzione[riga][colonna++] = parole[i];
							posizionato[i] = true;
							if(colonna == maxPerCella) {
								riga++;
								colonna = 0;
							}
						}	
					}
				}
			}

			int sol = 0;
			for(int i=0; i<n; i++) {
				if(soluzione[i][0] != "") {
					sol++;
				}
			}

			System.out.println("result " + nAssembly);
			System.out.println(sol);
			
			for(int i=0; i<n; i++) {
				for(int j=0; j<maxPerCella; j++) {
					System.out.print(soluzione[i][j] + " ");
				}
				System.out.println();
			}

			assembly = input.next();
		}


	}

}
