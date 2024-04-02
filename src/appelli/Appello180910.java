package appelli;

import java.util.Scanner;

public class Appello180910 {

	static Scanner input;

	static boolean finito = false;
	
	public static void main(String[] args) {

		input = new Scanner(System.in);

		int n = input.nextInt();

		int[] soluzioni = new int[n];

		for(int i=0; i<n; i++) {
			soluzioni[i] = -1;
		}

		avanza(soluzioni,0);


	}

	static boolean free(int x, int[] sol, int i) {
		for(int j=0; j<i; j++) {
			if(sol[j] == x) {
				return false;
			}
		}
		return true;
	}

	static boolean finale(int[] sol) {
		for(int i=0; i<sol.length; i++) {
			if(sol[i] != sol.length-i-1) {
				return false;
			}
		}
		return true;
	}
	
	static void avanza(int[] sol, int i) {
		int x = 0;
		while(x < sol.length) {
			if(free(x,sol,i)) {
				sol[i] = x;

				if(i == sol.length-1) {
					stampa(sol);
					
					if(finale(sol)) {
						System.out.println("finale");
						break;
					}
					
					sol[i] = -1;
					
					for(int n=1; i-n>=0; n++) {					

						boolean trovato = false;
						
						for(int k = sol[i-n]+1; k < sol.length; k++) {
							if(free(k,sol,i-n)) {
								sol[i-n]=k;
								
								trovato = true;
								avanza(sol, i-n+1);
								break;
							}
						}
						if(trovato) {
							break;
						}

					}
				}
				else {
					avanza(sol,i+1);
					break;
				}
			}
			else {
				x++;
			}
		}
	}

	static void stampa(int[] sol) {
		for(int i=0; i<sol.length; i++) {
			System.out.print(sol[i]+" ");
		}
		System.out.println();
	}

}



