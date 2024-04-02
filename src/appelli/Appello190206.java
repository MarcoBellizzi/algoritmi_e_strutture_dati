package appelli;

import java.util.Scanner;

import strutture.Albero;

public class Appello190206 {

	static Scanner input;
	
	static int passiMin;
	static boolean trovato;
	
	public static void main(String[] args) {
		
		input = new Scanner(System.in);
		
		passiMin = Integer.MAX_VALUE;
		trovato = false;
		
		int nParole = input.nextInt();
		
		String iniziale = input.next();
		String finale = input.next();
		
		String[] parole = new String[nParole];
		boolean[] presenti = new boolean[nParole];
		
		for(int i=0; i<nParole; i++) {
			parole[i] = input.next();
			if(parole[i].equals(iniziale)) {
				presenti[i] = true;
			}
			else {
				presenti[i] = false;
			}
		}
		
		Albero albero = new Albero(iniziale,presenti);
		riempi(albero,parole);
		cerca(albero,finale,0);
		
		if(trovato) {
			System.out.println(passiMin);
		}
		else {
			System.out.println(-1);
		}
		
	}
	
	
	static void riempi(Albero albero, String[] parole) {
		
		for(int i=0; i<parole.length; i++) {
			
			if(!albero.getPresenti()[i]) {
				int differenza = 0;
				for(int j=0; j<parole[i].length(); j++) {
					if(parole[i].charAt(j) != albero.getRadice().charAt(j)) {
						differenza++;
					}
				}
				if(differenza == 1) {
					boolean[] presenti = new boolean[parole.length];
					for(int j=0; j<presenti.length; j++) {
						presenti[j] = albero.getPresenti()[j];
					}
					presenti[i] = true;
					albero.addFiglio(new Albero(parole[i],presenti));
				}
			}
			
		}
		
		for(Albero figlio : albero.getFigli()) {
			riempi(figlio,parole);
		}
		
	}
	
	static void stampa(Albero albero) {
		System.out.println(albero.getRadice());
		for(Albero figlio : albero.getFigli()) {
			stampa(figlio);
		}
	}
	
	static void cerca(Albero albero, String parola, int passi) {
		if(albero.getRadice().equals(parola)) {
			if(passi < passiMin) {
				passiMin = passi;
			}
			trovato = true;
			return;
		}
		else {
			for(Albero figlio : albero.getFigli()) {
				cerca(figlio,parola,passi+1);
			}
		}
	}

}


















