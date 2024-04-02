package appelli;

import java.util.ArrayList;
import java.util.Scanner;

public class Appello180122 {

	static Scanner input;

	static int dirMax;
	static int persMin;
	static ArrayList<ArrayList<Integer>> persone;
	
	public static void main(String[] args) {

		input = new Scanner(System.in);

		int nDirectory = input.nextInt();
		dirMax = input.nextInt();
		persMin = input.nextInt();

		String[] directory = new String[nDirectory];
		persone = new ArrayList<ArrayList<Integer>>();

		for(int i=0; i<nDirectory; i++) {
			directory[i] = input.next();
			input.next();
			persone.add(new ArrayList<Integer>());
			while(input.hasNextInt()) {
				persone.get(i).add(input.nextInt());
			}
		}

		int[] sol = new int[nDirectory];
		for(int i=0; i<nDirectory; i++) {
			sol[i] = 0;
		}
		
		if(risolvi(sol,0)) {
			for(int i=0; i<nDirectory; i++) {
				if(sol[i] == 1) {
					System.out.print(directory[i] + " ");
				}
			}
		}
		else {
			System.out.print("NO");
		}
		
		
	}
	
	public static boolean risolvi(int[] sol, int k) {
		if(k == sol.length) {
			return false;
		}
		for(int i=1; i>=0; i--) {
			if(canAdd(i,sol,k) ) {
				sol[k] = i;
				if(completo(sol)) {
					return true;
				}
				else {
					if(risolvi(sol,k+1)) {
						return true;
					}
					else {
						sol[k] = 0;
					}
				}
			}
		}
		return false;
	}
	
	public static boolean canAdd(int x, int[] sol, int k) {
		if(x == 0) {
			return true;
		}
		int cont = 0;
		for(int i=0; i<sol.length; i++) {
			if(sol[i] == 1) {
				cont++;
			}
		}
		if(cont < dirMax) {
			return true;
		}
		return false;
	}
	
	public static boolean completo(int[] sol) {
		ArrayList<Integer> personeCorrenti = new ArrayList<Integer>();
		for(int i=0; i<sol.length; i++) {
			if(sol[i] == 1) {
				for(Integer n : persone.get(i)) {
					if(!presente(n,personeCorrenti)) {
						personeCorrenti.add(n);
					}
				}
			}
		}
		if(personeCorrenti.size() >= persMin) {
			return true;
		}
		return false;
	}
	
	public static boolean presente(int n, ArrayList<Integer> lista) {
		for(Integer num : lista) {
			if(num == n) {
				return true;
			}
		}
		return false;
	}

}





















