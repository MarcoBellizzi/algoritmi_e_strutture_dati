package appelli;

import java.util.Scanner;

public class P1617a2 {

	static Scanner input;
	
	public static void main(String[] args) {
		
		input = new Scanner(System.in);
		
		String conveyor = input.next();
		
		while(conveyor.equals("conveyor")) {
			
			int nConveyor = input.nextInt();
			int nMaterie = input.nextInt();
			int nAmici = input.nextInt();
			
			int differenzaMinima = Integer.MAX_VALUE;
			
			int[] materie = new int[nMaterie];
			for(int i=0; i<nMaterie; i++) {
				materie[i] = input.nextInt();
			}
			
			int[] pesiTot = new int[nAmici];  
			for(int i=0; i<nAmici; i++) {
				pesiTot[i] = 0;
			}
			
			int[] soluzioneCorrente = new int[nMaterie];
			int[] soluzione = new int[nMaterie];
			for(int i=0; i<nMaterie; i++) {
				soluzioneCorrente[i] = 0;
				soluzione[i] = 0;     // da rimuovere
			}
			soluzioneCorrente[0] = -1;
			
			int cont = 1;
			for(int i=0; i<nMaterie; i++) {
				cont = cont * nAmici;
			}
			
			for(int i=0; i<cont; i++) {
				soluzioneCorrente[0]++;
				for(int a=0; a<nMaterie; a++) {
					if(soluzioneCorrente[a] == nAmici) {
						soluzioneCorrente[a] = 0;
						if(a != nMaterie-1) {
							soluzioneCorrente[a+1]++;
						}
					}
				}
				
				if(ammissibile(soluzioneCorrente)) {

					for(int x=0; x<nAmici; x++) {
						pesiTot[x] = 0;
					}
					
					for(int x=0; x<nMaterie; x++) {
						pesiTot[soluzioneCorrente[x]]+=materie[x];
					}
					
					int differenza = calcolaDifferenzaMax(pesiTot);
					if(differenza < differenzaMinima) {
						differenzaMinima = differenza;
						for(int x=0; x<nMaterie; x++) {
							soluzione[x] = soluzioneCorrente[x];
						}
					}
					
				}
				
			}
			
			if(nConveyor > 1) {
				System.out.println();
			}
			
			System.out.println("result " + nConveyor);
			System.out.print(materie[0]);
			int amicoCorrente = 0;
			for(int i=1; i<nMaterie; i++) {
				if(soluzione[i] > amicoCorrente) {
					amicoCorrente = soluzione[i];
					System.out.print(" |" );
				}
				System.out.print(" " + materie[i]);
			}
			
			conveyor = input.next();
		}

	}
	
	static boolean ammissibile(int[] soluzione) {
		for(int i=0; i<soluzione.length-1; i++)  {
			if(soluzione[i] > soluzione[i+1]) {
				return false;
			}
		}
		return true;
	}
	
	static int calcolaDifferenzaMax(int[] pesi) {
		int max = 0;
		int min = Integer.MAX_VALUE;
		for(int i=0; i<pesi.length; i++) {
			if(pesi[i] < min) {
				min = pesi[i] ;
			}
			if(pesi[i] > max) {
				max = pesi[i];
			}
		}
		return max - min;
	}

}
