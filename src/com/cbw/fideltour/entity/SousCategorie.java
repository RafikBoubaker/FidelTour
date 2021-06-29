package com.cbw.fideltour.entity;

public class SousCategorie {
	public String nom_sous_categ_cmd;
	public String prix_unitaire;
	public String nb;

	public SousCategorie() {
		super();

	}

	public SousCategorie(String nom_sous_categ_cmd, String prix_unitaire) {
		super();
		this.nom_sous_categ_cmd = nom_sous_categ_cmd;
		this.prix_unitaire = prix_unitaire;
	}

	public String getNb() {
		return nb;
	}

	public void setNb(String nb) {
		this.nb = nb;
	}

	public String getNom_sous_categ_cmd() {
		return nom_sous_categ_cmd;
	}

	public void setNom_sous_categ_cmd(String nom_sous_categ_cmd) {
		this.nom_sous_categ_cmd = nom_sous_categ_cmd;
	}

	public String getPrix_unitaire() {
		return prix_unitaire;
	}

	public void setPrix_unitaire(String prix_unitaire) {
		this.prix_unitaire = prix_unitaire;
	}

}
