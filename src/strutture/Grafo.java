package strutture;

public class Grafo {

	int n;
	boolean[][] matrice;
	
	public Grafo(int n) {
		this.n = n;
		this.matrice = new boolean[n][n];
		for(int i=0; i<n; i++) {
			for(int j=0; j<n; j++) {
				matrice[i][j] = false;
			}
		}
	}
	
	public int getN() { return n; }
	
	public boolean getArco(int i, int j) { return matrice[i][j]; }
	
	public void setArco(int i, int j, boolean b) { matrice[i][j] = b; }
	
}
