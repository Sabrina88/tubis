package com.example.baumarkt.model;

public class Position {
	
	private Artikel artikel;
	private int menge;
	
	public Position() {
		
	}
	
	public Position(Artikel artikel, int menge)
	{
		this.artikel = artikel;
		this.menge = menge;
	}
	
	public Position(Artikel artikel) {
		this.artikel = artikel;
		this.menge = 1;
	}
	
	public Artikel getArtikel() {
		return artikel;
	}
	public void setArtikel(Artikel artikel) {
		this.artikel = artikel;
	}
	public int getMenge() {
		return menge;
	}
	public void setMenge(int menge) {
		this.menge = menge;
	}
	
	@Override
	public boolean equals(Object obj) {
		
		if (obj == null) {
			return false;
		}
		
		if (obj == this) {
			return true;
		}
		
		if (!(obj instanceof Position)) {
			return false;
		}
		
		Position p = (Position) obj;
		
		return artikel.equals(p.getArtikel());
	}
	
	@Override
	public int hashCode() {
		return artikel.hashCode();
	}

	public void increaseMenge() {
		menge++;
	}
}
