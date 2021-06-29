package com.cbw.fideltour.entity;

import java.io.Serializable;

public class Offre implements Serializable{

	private static final long serialVersionUID = -6915815894906179691L;
	
	private String nom_offre;
	private String description_offre;
	private String	prix_offre;
	private String image_offre;
	private String delai_offre;
	private String path;
	
	public Offre() {
		super();
	}
	

	public Offre(String nom_offre, String description_offre, String prix_offre,
			String image_offre, String delai_offre, String path) {
		super();
		this.nom_offre = nom_offre;
		this.description_offre = description_offre;
		this.prix_offre = prix_offre;
		this.image_offre = image_offre;
		this.delai_offre = delai_offre;
		this.path = path;
	}


	public String getPath() {
		return path;
	}
	public void setPath(String path) {
		this.path = path;
	}

	public String getDelai_offre() {
		return delai_offre;
	}
	public void setDelai_offre(String delai_offre) {
		this.delai_offre = delai_offre;
	}
	public String getNom_offre() {
		return nom_offre;
	}
	public void setNom_offre(String nom_offre) {
		this.nom_offre = nom_offre;
	}
	public String getDescription_offre() {
		return description_offre;
	}
	public void setDescription_offre(String description_offre) {
		this.description_offre = description_offre;
	}
	public String getPrix_offre() {
		return prix_offre;
	}
	public void setPrix_offre(String prix_offre) {
		this.prix_offre = prix_offre;
	}
	public String getImage_offre() {
		return image_offre;
	}
	public void setImage_offre(String image_offre) {
		this.image_offre = image_offre;
	}
	
	
	
}
