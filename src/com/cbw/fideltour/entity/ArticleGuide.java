package com.cbw.fideltour.entity;

public class ArticleGuide {
public String nom_article_guide;
public String description_article_guide;
public String image_article_guide;
public ArticleGuide(String nom_article_guide, String description_article_guide,
		String image_article_guide) {
	super();
	this.nom_article_guide = nom_article_guide;
	this.description_article_guide = description_article_guide;
	this.image_article_guide = image_article_guide;
}
public ArticleGuide() {
	super();
}
public String getNom_article_guide() {
	return nom_article_guide;
}
public void setNom_article_guide(String nom_article_guide) {
	this.nom_article_guide = nom_article_guide;
}
public String getDescription_article_guide() {
	return description_article_guide;
}
public void setDescription_article_guide(String description_article_guide) {
	this.description_article_guide = description_article_guide;
}
public String getImage_article_guide() {
	return image_article_guide;
}
public void setImage_article_guide(String image_article_guide) {
	this.image_article_guide = image_article_guide;
}
@Override
public String toString() {
	return "ArticleGuide [nom_article_guide=" + nom_article_guide
			+ ", description_article_guide=" + description_article_guide + "]";
}

}
