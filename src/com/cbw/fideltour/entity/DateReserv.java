package com.cbw.fideltour.entity;

public class DateReserv {
	String dateArrivee;
	String dateDepart;
	String jour_reservation_salle;
	
	public DateReserv() {
		super();
			}
	public String getDateArrivee() {
		return dateArrivee;
	}
	public void setDateArrivee(String dateArrivee) {
		this.dateArrivee = dateArrivee;
	}
	public String getDateDepart() {
		return dateDepart;
	}
	public void setDateDepart(String dateDepart) {
		this.dateDepart = dateDepart;
	}
	
	public String getJour_reservation_salle() {
		return jour_reservation_salle;
	}
	public void setJour_reservation_salle(String jour_reservation_salle) {
		this.jour_reservation_salle = jour_reservation_salle;
	}
	@Override
	public String toString() {
		return "DateReservCh [dateArrivee=" + dateArrivee + ", dateDepart="
				+ dateDepart + "]";
	}


}
