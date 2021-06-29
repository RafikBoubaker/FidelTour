package com.cbw.fideltour.entity;

public class Client {
String e_mail;
String id_passager;

public Client(String e_mail, String id_passager) {
	super();
	this.e_mail = e_mail;
	this.id_passager = id_passager;
}

public Client() {
	super();
}

public String getId_passager() {
	return id_passager;
}

public void setId_passager(String id_passager) {
	this.id_passager = id_passager;
}

public String getE_mail() {
	return e_mail;
}

public void setE_mail(String e_mail) {
	this.e_mail = e_mail;
}

}
