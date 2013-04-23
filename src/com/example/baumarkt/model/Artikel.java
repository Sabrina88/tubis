package com.example.baumarkt.model;

public class Artikel {
	
	private int id;
	private String standort;
	private String bezeichnung;
	private float preis;
	private String bildname;
	private Produktkategorie produktkategorie;
	
	public Artikel() {
	}
	
	public Artikel(int id, String bezeichnung, String standort, float preis, String bildname) {
		this.id = id; 
		this.bezeichnung = bezeichnung;
		this.standort = standort;
		this.preis = preis;
		this.bildname = bildname;
	}
	
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getStandort() {
		return standort;
	}
	public void setStandort(String standort) {
		this.standort = standort;
	}
	public String getBezeichnung() {
		return bezeichnung;
	}
	public void setBezeichnung(String bezeichnung) {
		this.bezeichnung = bezeichnung;
	}
	public float getPreis() {
		return preis;
	}
	public void setPreis(float preis) {
		this.preis = preis;
	}
	public String getBildname() {
		return bildname;
	}
	public void setBildname(String bildname) {
		this.bildname = bildname;
	}
	public Produktkategorie getProduktkategorie() {
		return produktkategorie;
	}
	public void setProduktkategorie(Produktkategorie produktkategorie) {
		this.produktkategorie = produktkategorie;
	}
	
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append(id);
		sb.append(" / ");
		sb.append(bezeichnung);
		sb.append(" / ");
		sb.append(standort);
		sb.append(" / ");
		sb.append(preis);
		return sb.toString();
	}
}
