package appelli;

import java.util.Scanner;

public class Appello180712 {

	static Scanner input;

	static int righe;
	static int colonne;

	static int[][] matrice;

	public static void main(String[] args) {

		input = new Scanner(System.in);

		righe = input.nextInt();
		colonne = input.nextInt();

		matrice = new int[righe][colonne];
		for(int i=0; i<righe; i++) {
			for(int j=0; j<colonne; j++) {
				matrice[i][j] = input.nextInt();
			}
		}

		int[][] picchi = new int[righe][colonne];
		for(int i=0; i<righe; i++) {
			for(int j=0; j<colonne; j++) {
				picchi[i][j] = picco(i,j);	
			}
		}

		int iMax = 0;
		int jMax = 0;
		int piccoMax = 0;

		for(int i=0; i<righe; i++) {
			for(int j=0; j<colonne; j++) {
				if(picchi[i][j] != 0) {
					int cont = 0;
					for(int k=0; k<colonne; k++) {
						if(picchi[i][k] != 0 && picchi[i][k] < picchi[i][j]) {
							cont++;
							break;
						}
					}
					for(int k=0; k<righe; k++) {
						if(picchi[k][j] != 0 && picchi[k][j] < picchi[i][j]) {
							cont++;
							break;
						}
					}
					for(int k=0; k<colonne; k++) {
						if(i-j+k>=0 && i-j+k<righe) {
							if(picchi[i-j+k][k] != 0 && picchi[i-j+k][k] < picchi[i][j]) {                                                         
								cont++;
								break;
							}
						}
					}
					for(int k=0; k<colonne; k++) {
						if(i+j-k>=0 && i+j-k<righe) {
							if(picchi[i+j-k][k] != 0 && picchi[i+j-k][k] < picchi[i][j]) {
								cont++;
								break;
							}
						}
					}
					if(cont > piccoMax) {
						piccoMax = cont;
						iMax = i;
						jMax = j;
					}
				}
			}
		}
		System.out.println("<" + iMax + "," + jMax + ">");
	}

	public static int picco(int i, int j) {   // 0 se non è un picco(zona), n se è un picco
		int somma = 0;
		for(int k=-2; k<3; k++) {
			for(int l=-2; l<3; l++) {

				if(k+l>-3 && k+l<3 && l-k<3 && l-k>-3 && !(k==0 && l==0)) {
					if(i+k>=0 && i+k<righe && j+l>=0 && j+l<colonne) {
						somma += matrice[i+k][j+l];
					}
				}

			}
		}
		if(matrice[i][j] > somma) {
			return matrice[i][j];
		}
		return 0;
	}
}














