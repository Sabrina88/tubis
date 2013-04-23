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
}
