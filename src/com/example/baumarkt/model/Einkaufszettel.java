package com.example.baumarkt.model;

import java.util.LinkedList;
import java.util.List;

public class Einkaufszettel {
	
	private List<Position> artikelliste = new LinkedList<Position>();
	
	public void addArtikel(Artikel a) {
		Position p = new Position(a);
		if (artikelliste.contains(p)) {
			Position pExist = artikelliste.get(artikelliste.indexOf(p));
			pExist.increaseMenge();
		}
		else {
			p.setMenge(1);
			artikelliste.add(p);
		}
		System.out.println(toString());
	}
	
	public void remove(Artikel a) {
		artikelliste.remove(new Position(a));
	}
	
	public List<Position> getBestellliste() {
		return artikelliste;
	}
	
	public String toString() {
		StringBuilder sb = new StringBuilder();
		int count = 0;
		for (Position p : artikelliste) {
			count++;
			sb.append(count);
			sb.append(": ");
			sb.append(p.getArtikel().getBezeichnung());
			sb.append("; Menge: ");
			sb.append(p.getMenge());
			sb.append("\n");
		}
		return sb.toString();
	}
}
