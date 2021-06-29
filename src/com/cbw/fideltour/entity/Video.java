package com.cbw.fideltour.entity;

import java.io.Serializable;

public class Video implements Serializable {

	private static final long serialVersionUID = 1L;

	private String nom_video;
	private String image_video;
	private String prix_video;
	private String path;

	public Video(String nom_video, String image_video, String prix_video,
			String path) {
		super();
		this.nom_video = nom_video;
		this.image_video = image_video;
		this.prix_video = prix_video;
		this.path = path;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public String getNom_video() {
		return nom_video;
	}

	public void setNom_video(String nom_video) {
		this.nom_video = nom_video;
	}

	public String getImage_video() {
		return image_video;
	}

	public void setImage_video(String image_video) {
		this.image_video = image_video;
	}

	public String getPrix_video() {
		return prix_video;
	}

	public void setPrix_video(String prix_video) {
		this.prix_video = prix_video;
	}

}