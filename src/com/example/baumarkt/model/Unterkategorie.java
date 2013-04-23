package com.example.baumarkt.model;

import java.util.Set;

public class Unterkategorie {
	
	private int id;
	private String bezeichnung;
	private String standort;
	private Hauptkategorie hauptkategorie;
	private Set<Produktkategorie> produktkategorien;
	
	public Unterkategorie() {
		
	}
	
	public Unterkategorie(int id, String bezeichnung, String standort) {
		this.id = id;
		this.bezeichnung = bezeichnung;
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

	public Hauptkategorie getHauptkategorie() {
		return hauptkategorie;
	}

	public void setHauptkategorie(Hauptkategorie hauptkategorie) {
		this.hauptkategorie = hauptkategorie;
	}

	public Set<Produktkategorie> getProduktkategorien() {
		return produktkategorien;
	}

	public void setProduktkategorien(Set<Produktkategorie> produktkategorien) {
		this.produktkategorien = produktkategorien;
	}
	
	public void addProduktkateogien(Produktkategorie p) {
		this.produktkategorien.add(p);
	}

	@Override
	public String toString() {
		return bezeichnung;
	}
}
