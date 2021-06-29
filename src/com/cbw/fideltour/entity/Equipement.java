package com.cbw.fideltour.entity;

public class Equipement {
	public 	String type_equipement;	
	public 	String description_equipement;
	public 	String logo_equipement;
	public Equipement(String type_equipement, String ddescription_equipement,
			String logo_equipement) {
		super();
		this.type_equipement = type_equipement;
		this.description_equipement = ddescription_equipement;
		this.logo_equipement = logo_equipement;
	}
	public String getType_equipement() {
		return type_equipement;
	}
	public void setType_equipement(String type_equipement) {
		this.type_equipement = type_equipement;
	}
	public String getDdescription_equipement() {
		return description_equipement;
	}
	public void setDdescription_equipement(String ddescription_equipement) {
		this.description_equipement = ddescription_equipement;
	}
	public String getLogo_equipement() {
		return logo_equipement;
	}
	public void setLogo_equipement(String logo_equipement) {
		this.logo_equipement = logo_equipement;
	}
	
}