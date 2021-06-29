package com.cbw.fideltour.entity;

public class OffreM {
	private String nom_offre;
	private String delai_offre;

	public String getNom_offre() {
		return nom_offre;
	}

	public void setNom_offre(String nom_offre) {
		this.nom_offre = nom_offre;
	}



	public String getDelai_offre() {
		return delai_offre;
	}

	public void setDelai_offre(String delai_offre) {
		this.delai_offre = delai_offre;
	}

	public OffreM(String nom_offre, String delai_offre) {
		super();
		this.nom_offre = nom_offre;
		this.delai_offre = delai_offre;
	}

	public OffreM() {
		super();
	}

}
