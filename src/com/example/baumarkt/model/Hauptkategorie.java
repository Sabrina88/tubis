package com.example.baumarkt.model;

import java.util.Set;

public class Hauptkategorie {
	
	private int id;
	private String bezeichnung;
	private String standort;
	private Set<Unterkategorie> unterkategorien;
	
	public Hauptkategorie() {
		
	}
	
	public Hauptkategorie(int id, String becheichnung, String standort) {
		this.id = id;
		this.bezeichnung = becheichnung;
		this.standort = standort;
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getBezeichnung() {
		return bezeichnung;
	}
	public void setBezeichnung(String bezeichnung) {
		this.bezeichnung = bezeichnung;
	}
	public String getStandort() {
		return standort;
	}
	public void setStandort(String standort) {
		this.standort = standort;
	}

	public Set<Unterkategorie> getUnterkategorien() {
		return unterkategorien;
	}

	public void setUnterkategorien(Set<Unterkategorie> unterkategorien) {
		this.unterkategorien = unterkategorien;
	}
	
	public void addUnterkategorien(Unterkategorie u) {
		this.unterkategorien.add(u);
	}
	
	@Override
	public String toString()
	{
		return bezeichnung;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		
		if (obj == null) {
			return false;
		}
		
		if (!(obj instanceof Hauptkategorie)) {
			return false;
		}
		
		Hauptkategorie other = (Hauptkategorie) obj;
		return id == other.id && bezeichnung.equals(other.bezeichnung) && standort.equals(standort);
		
	}
	
	@Override
	public int hashCode() {
		int result = 37;
		int r = 17;
		
		result = result * r + id;
		result = result * r + bezeichnung.hashCode();
		result = result * r + standort.hashCode();
		
		return result;
	}
}
