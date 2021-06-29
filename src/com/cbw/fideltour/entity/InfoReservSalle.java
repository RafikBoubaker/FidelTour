package com.cbw.fideltour.entity;

public class InfoReservSalle {
	
	String heure_debut1;
	String heure_fin1;
	String heure_debut2;
	String heure_fin2;
	String heure_debut3;
	String heure_fin3;
	String nom_passager;
	String prenom_passager;
	String e_mail;
	String type_salle1;
	String type_salle2;
	String type_salle3;
	String id_passager;

	public InfoReservSalle() {
		super();
	}

	public String getHeure_debut1() {
		return heure_debut1;
	}

	public void setHeure_debut1(String heure_debut1) {
		this.heure_debut1 = heure_debut1;
	}

	public String getHeure_fin1() {
		return heure_fin1;
	}

	public void setHeure_fin1(String heure_fin1) {
		this.heure_fin1 = heure_fin1;
	}

	public String getHeure_debut2() {
		return heure_debut2;
	}

	public void setHeure_debut2(String heure_debut2) {
		this.heure_debut2 = heure_debut2;
	}

	public String getHeure_fin2() {
		return heure_fin2;
	}

	public void setHeure_fin2(String heure_fin2) {
		this.heure_fin2 = heure_fin2;
	}

	public String getHeure_debut3() {
		return heure_debut3;
	}

	public void setHeure_debut3(String heure_debut3) {
		this.heure_debut3 = heure_debut3;
	}

	public String getHeure_fin3() {
		return heure_fin3;
	}

	public void setHeure_fin3(String heure_fin3) {
		this.heure_fin3 = heure_fin3;
	}

	public String getNom_passager() {
		return nom_passager;
	}

	public void setNom_passager(String nom_passager) {
		this.nom_passager = nom_passager;
	}

	public String getPrenom_passager() {
		return prenom_passager;
	}

	public void setPrenom_passager(String prenom_passager) {
		this.prenom_passager = prenom_passager;
	}

	public String getE_mail() {
		return e_mail;
	}

	public void setE_mail(String e_mail) {
		this.e_mail = e_mail;
	}

	public String getType_salle1() {
		return type_salle1;
	}

	public void setType_salle1(String type_salle1) {
		this.type_salle1 = type_salle1;
	}

	public String getType_salle2() {
		return type_salle2;
	}

	public void setType_salle2(String type_salle2) {
		this.type_salle2 = type_salle2;
	}

	public String getType_salle3() {
		return type_salle3;
	}

	public void setType_salle3(String type_salle3) {
		this.type_salle3 = type_salle3;
	}

	public String getId_passager() {
		return id_passager;
	}

	public void setId_passager(String id_passager) {
		this.id_passager = id_passager;
	}

	@Override
	public String toString() {
		return "InfoReservSalle [heure_debut1=" + heure_debut1
				+ ", heure_fin1=" + heure_fin1 + ", heure_debut2="
				+ heure_debut2 + ", heure_fin2=" + heure_fin2
				+ ", heure_debut3=" + heure_debut3 + ", heure_fin3="
				+ heure_fin3 + ", nom_passager=" + nom_passager
				+ ", prenom_passager=" + prenom_passager + ", e_mail=" + e_mail
				+ ", type_salle1=" + type_salle1 + ", type_salle2="
				+ type_salle2 + ", type_salle3=" + type_salle3
				+ ", id_passager=" + id_passager + "]";
	}

	
	

}
