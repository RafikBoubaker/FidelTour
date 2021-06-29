package com.cbw.fideltour.entity;

public class Hotel {

	int id_hotel;
	public String nom_hotel;
	public String description_hotel;
	public String video_hotel;
	public String politique_de_confidentialite;
	public String conditionReservationChambre;
	public String conditionReservationSalle;
	public String telephone_hotel;
	public String e_mail_hotel;
	public String fax_hotel;
	public String qualite;

	public static String adresse_ip = "http://192.168.1.3/Projet_BD_Fidel_Tour/";

	public Hotel(String nom_hotel, String description_hotel, String video_hotel) {
		super();
		this.nom_hotel = nom_hotel;
		this.description_hotel = description_hotel;
		this.video_hotel = video_hotel;
	}

	public String getConditionReservationChambre() {
		return conditionReservationChambre;
	}

	public void setConditionReservationChambre(
			String conditionReservationChambre) {
		this.conditionReservationChambre = conditionReservationChambre;
	}

	public String getConditionReservationSalle() {
		return conditionReservationSalle;
	}

	public void setConditionReservationSalle(String conditionReservationSalle) {
		this.conditionReservationSalle = conditionReservationSalle;
	}

	public String getTelephone_hotel() {
		return telephone_hotel;
	}

	public void setTelephone_hotel(String telephone_hotel) {
		this.telephone_hotel = telephone_hotel;
	}

	public String getE_mail_hotel() {
		return e_mail_hotel;
	}

	public void setE_mail_hotel(String e_mail_hotel) {
		this.e_mail_hotel = e_mail_hotel;
	}

	public String getFax_hotel() {
		return fax_hotel;
	}

	public void setFax_hotel(String fax_hotel) {
		this.fax_hotel = fax_hotel;
	}

	public String getQualite() {
		return qualite;
	}

	public void setQualite(String qualite) {
		this.qualite = qualite;
	}

	public void setPolitique_de_confidentialite(
			String politique_de_confidentialite) {
		this.politique_de_confidentialite = politique_de_confidentialite;
	}

	public String getPolitique_de_confidentialite() {
		return politique_de_confidentialite;
	}

	public void setPolitique_de_confidentialité(
			String politique_de_confidentialité) {
		this.politique_de_confidentialite = politique_de_confidentialité;
	}

	public String getVideo_hotel() {
		return video_hotel;
	}

	public void setVideo_hotel(String video_hotel) {
		this.video_hotel = video_hotel;
	}

	public Hotel() {

	}

	public String getNom_hotel() {
		return nom_hotel;
	}

	public void setNom_hotel(String nom_hotel) {
		this.nom_hotel = nom_hotel;
	}

	public int getId_hotel() {
		return id_hotel;
	}

	public void setId_hotel(int id_hotel) {
		this.id_hotel = id_hotel;
	}

	public String getDescription_hotel() {
		return description_hotel;
	}

	public void setDescription_hotel(String description_hotel) {
		this.description_hotel = description_hotel;
	}

}
