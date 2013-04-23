package com.example.baumarkt.model;

import java.util.Set;

public class Produktkategorie {
	
	private int id;
	private String bezeichnung;
	private String standort;
	private Unterkategorie unterkategorien;
	private Set<Artikel> artikel;
	
	public Produktkategorie() {
		
	}
	
	public Produktkategorie(int id, String bezeichnung, String stanort) {
		this.id = id;
		this.bezeichnung = bezeichnung;
		this.standort = stanort;
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

	public Unterkategorie getUnterkategorien() {
		return unterkategorien;
	}

	public void setUnterkategorien(Unterkategorie unterkategorien) {
		this.unterkategorien = unterkategorien;
	}

	public Set<Artikel> getArtikel() {
		return artikel;
	}

	public void setArtikel(Set<Artikel> artikel) {
		this.artikel = artikel;
	}
	
	public void addArtikel(Artikel a) {
		this.artikel.add(a);
	}

	@Override
	public String toString() {
		return bezeichnung;
	}
}
