package com.cbw.fideltour.entity;

public class ServiceGratuit {
	public 	String nom_service;	
	public 	String description_service;
	public 	String logo_service;
	
	public ServiceGratuit(String nom_service, String description_service,
			String logo_service) {
		super();
		this.nom_service = nom_service;
		this.description_service = description_service;
		this.logo_service = logo_service;
	}
	public ServiceGratuit() {
		super();
	}
	public String getNom_service() {
		return nom_service;
	}
	public void setNom_service(String nom_service) {
		this.nom_service = nom_service;
	}
	public String getDescription_service() {
		return description_service;
	}
	public void setDescription_service(String description_service) {
		this.description_service = description_service;
	}
	public String getLogo_service() {
		return logo_service;
	}
	public void setLogo_service(String logo_service) {
		this.logo_service = logo_service;
	}
	@Override
	public String toString() {
		return "Service [nom_service=" + nom_service + ", description_service="
				+ description_service + "]";
	}


}
