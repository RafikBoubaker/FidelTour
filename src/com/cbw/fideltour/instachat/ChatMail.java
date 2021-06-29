package com.cbw.fideltour.instachat;

public class ChatMail {
	public String e_mail;
public String prenom_passager;
	public String getE_mail() {
		return e_mail;
	}

	
	public ChatMail(String e_mail, String prenom_passager) {
		super();
		this.e_mail = e_mail;
		this.prenom_passager = prenom_passager;
	}


	public void setE_mail(String e_mail) {
		this.e_mail = e_mail;
	}

	public ChatMail(String e_mail) {
		super();
		this.e_mail = e_mail;
	}

	public ChatMail() {
		super();
		// TODO Auto-generated constructor stub
	}

	public String getPrenom_passager() {
		return prenom_passager;
	}

	public void setPrenom_passager(String prenom_passager) {
		this.prenom_passager = prenom_passager;
	}

}
