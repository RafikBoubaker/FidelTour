package com.cbw.fideltour.entity;

import java.util.ArrayList;

public class Categorie {

	public String nom_categ_cmd;
	public String logo_categ;
	public ArrayList<SousCategorie> souscat = new ArrayList<SousCategorie>();

	public Categorie(String nom_categorie, String logo,
			ArrayList<SousCategorie> souscat) {
		super();
		this.nom_categ_cmd = nom_categorie;
		this.logo_categ = logo;
		this.souscat = souscat;
	}

	public ArrayList<SousCategorie> getSouscat() {
		return souscat;
	}

	public void setSouscat(ArrayList<SousCategorie> souscat) {
		this.souscat = souscat;
	}

	public String getNom_categ_cmd() {
		return nom_categ_cmd;
	}

	public void setNom_categ_cmd(String nom_categ_cmd) {
		this.nom_categ_cmd = nom_categ_cmd;
	}

	public String getLogo_categ() {
		return logo_categ;
	}

	public void setLogo_categ(String logo_categ) {
		this.logo_categ = logo_categ;
	}

}
