package strutture;

import java.util.ArrayList;

public class Albero {

	String radice;
	ArrayList<Albero> figli;
	boolean[] presenti;
	
	public Albero(String radice, boolean[] presenti) {
		this.radice = radice;
		figli = new ArrayList<Albero>();
		this.presenti = presenti;
	}
	
	public String getRadice() { 
		return radice; 
	}

	public ArrayList<Albero> getFigli() {
		return figli;
	}
	
	public boolean[] getPresenti() {
		return presenti;
	}
	
	public int getNumeroFigli() {
		return figli.size();
	}
	
	public void addFiglio(Albero figlio) {
		figli.add(figlio);
	}
	
}
