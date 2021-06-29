package com.cbw.fideltour.entity;

public class Article {
public 	String nom_article;	
public 	String description_article;
public 	String image_article;
	public Article(String nom_article, String description_article,
			String image_article) {
		super();
		this.nom_article = nom_article;
		this.description_article = description_article;
		this.image_article = image_article;
	}
	public Article(String nom_article, String description_article) {
		super();
		this.nom_article = nom_article;
		this.description_article = description_article;
	}
	public String getNom_article() {
		return nom_article;
	}
	public void setNom_article(String nom_article) {
		this.nom_article = nom_article;
	}
	public String getDescription_article() {
		return description_article;
	}
	public void setDescription_article(String description_article) {
		this.description_article = description_article;
	}
	public String getImage_article() {
		return image_article;
	}
	public void setImage_article(String image_article) {
		this.image_article = image_article;
	}
	@Override
	public String toString() {
		return "Article [nom_article=" + nom_article + ", description_article="
				+ description_article + "]";
	}
	
}
