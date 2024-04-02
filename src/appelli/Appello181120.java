package appelli;

import java.util.Scanner;

public class Appello181120 {

	static Scanner input;

	public static void main(String[] args) {

		int contMax = 0;

		input = new Scanner(System.in);

		boolean ok = false;

		int numParole = input.nextInt();
		int q = input.nextInt();

		String[] parole = new String[numParole];
		for(int i=0; i<numParole; i++) {
			parole[i] = input.next();
		}

		for(int i=0; i<numParole && !ok; i++) {
			for(int j=0; j<numParole && !ok; j++) {

				if(simili(parole[i],parole[j])) {
					System.out.println("simili");
					
					int cont = 2;					
					if(cont == q) {
						ok = true;
					}
					for(int k=0; k<numParole; k++) {
						if(k!=i && k!=j && simili(parole[k],parole[i]) && simili(parole[k],parole[j])) {
							cont++;
						}
						if(cont == q) {
							ok = true;
						}
					}
					if(cont>contMax) {
						contMax = cont;
					}
				}

			}
		}

		if(ok || q == 1) {
			System.out.println("SI");
		}
		else {
			System.out.println("NO");
		}
		
		System.out.println(contMax);

	}

	public static boolean simili(String parola1, String parola2) {
		int differenza = 0;
		if(parola1.length() == parola2.length()) {
			for(int i=0; i<parola1.length(); i++) {
				if(parola1.charAt(i) != parola2.charAt(i)) {
					differenza++;
				}
			}
			if(differenza == 1) {
				return true;
			}
		}
		else {
			if(parola1.length() == parola2.length()-1) {
				for(int i=0; i<parola1.length(); i++) {
					if(parola1.charAt(i) != parola2.charAt(i)) {
						differenza++;
					}
				}
				if(differenza == 0) {
					return true;
				}
			}
			else {
				if(parola1.length() == parola2.length()+1) {
					for(int i=0; i<parola2.length(); i++) {
						if(parola1.charAt(i) != parola2.charAt(i)) {
							differenza++;
						}
					}
					if(differenza == 0) {
						return true;
					}
				}
			}
		}
		return false;
	}


}




















