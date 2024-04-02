package appelli;

import java.util.ArrayList;
import java.util.Scanner;

public class Appello180618 {
	
	static class Coppia {
		String corso1;
		String corso2;
		
		public Coppia(String corso1, String corso2) {
			this.corso1 = corso1;
			this.corso2 = corso2;
		}
		
		public String getCorso1() {
			return corso1;
		}
		
		public String getCorso2() {
			return corso2;
		}
	}
	
	static class Mappa {
		String[] corsi;
		
		public Mappa(int n) {
			corsi = new String[n];
		}
		
		public void add(String corso, int i) {
			corsi[i] = corso;
		}
		
		public int getPosizione(String corso) {
			for(int i=0; i<corsi.length; i++) {
				if(corsi[i].equals(corso)) {
					return i;
				}
			}
			return -1;
		}
		
	}
	
	static Scanner input;
	static boolean condizione;
	
	public static void main(String[] args) {
		
		input = new Scanner(System.in);
		condizione = false;
		
		int nCorsi = input.nextInt();
		int nScelte = input.nextInt();
		
		Mappa mappa = new Mappa(nCorsi);
		for(int i=0; i<nCorsi; i++) {
			mappa.add(input.next(), i);
		}
		
		ArrayList<Coppia> coppie = new ArrayList<Coppia>();
		for(int i=0; i<nScelte; i++) {
			String s1 = input.next();
			String s2 = input.next();
			String c1 = "";
			String c2 = "";
			for(int j=1; j<s1.length(); j++) {
				c1 += s1.charAt(j);
			}
			for(int j=0; j<s2.length()-1; j++) {
				c2 += s2.charAt(j);
			}
			coppie.add(new Coppia(c1,c2));
		}
		
		int[] soluzione = new int[nCorsi];
		for(int i=0; i<nCorsi; i++) {
			soluzione[i] = 0;
		}
		soluzione[0] = -1;
		
		int passi = 1;
		for(int i=0; i<nCorsi; i++) {
			passi = passi * 3; 
		}

		for(int i=0; i<passi && !condizione; i++) {
			
			soluzione[0]++;
			for(int a=0; a<nCorsi; a++) {
				if(soluzione[a] == 3) {
					soluzione[a] = 0;
					if(a!=nCorsi-1) {
						soluzione[a+1]++;
					}
				}
			}
			
			boolean bene = true;
			for(Coppia coppia : coppie) {
				if(soluzione[mappa.getPosizione(coppia.getCorso1())] == soluzione[mappa.getPosizione(coppia.getCorso2())]) {                       
					bene = false;
				}
			}
			if(bene) {
				condizione = true;
			}
				
		}
		
		if(condizione) {
			System.out.println("SI");
		}
		else {
			System.out.println("NO");
		}
		
	}

}
