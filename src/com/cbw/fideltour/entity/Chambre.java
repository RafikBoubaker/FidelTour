package com.cbw.fideltour.entity;

public class Chambre {
	public String type_chambre;
	public String prix_chambre;
	public String tva;
	public String prixchambreTva;
	public String image_article;

	public String getImage_article() {
		return image_article;
	}

	public void setImage_article(String image_article) {
		this.image_article = image_article;
	}

	public Chambre(String type_chambre, String description_chambre,
			String prix_chambre, String tva, String prixchambreTva) {
		super();
		this.type_chambre = type_chambre;

		this.prix_chambre = prix_chambre;
		this.tva = tva;
		this.prixchambreTva = prixchambreTva;
	}

	public Chambre(String type_chambre, String description_chambre,
			String image_chambre, String prix_chambre, String tva,
			String prixchambreTva) {
		super();
		this.type_chambre = type_chambre;

		this.prix_chambre = prix_chambre;
		this.tva = tva;
		this.prixchambreTva = prixchambreTva;
	}

	public String getPrix_chambre() {
		return prix_chambre;
	}

	public void setPrix_chambre(String prix_chambre) {
		this.prix_chambre = prix_chambre;
	}

	public String getTva() {
		return tva;
	}

	public void setTva(String tva) {
		this.tva = tva;
	}

	public String getPrixchambreTva() {
		return prixchambreTva;
	}

	public void setPrixchambreTva(String prixchambreTva) {
		this.prixchambreTva = prixchambreTva;
	}

	public String getType_chambre() {
		return type_chambre;
	}

	public void setType_chambre(String type_chambre) {
		this.type_chambre = type_chambre;
	}

	@Override
	public String toString() {
		return "Chambre [type_chambre=" + type_chambre

		+ ", prix_chambre=" + prix_chambre + ", tva=" + tva
				+ ", prixchambreTva=" + prixchambreTva + "]";
	}

}