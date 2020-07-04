package com.gpch.login.repository;

import javax.persistence.Entity;
import javax.persistence.*;

@Entity
public class Equipements {
	@Id @GeneratedValue
	private Long idEqui;
	private String nomEqui;
	private String codeHtml;
	public String getNomEqui() {
		return nomEqui;
	}
	public void setNomEqui(String nomEqui) {
		this.nomEqui = nomEqui;
	}
	public String getCodeHtml() {
		return codeHtml;
	}
	public void setCodeHtml(String codeHtml) {
		this.codeHtml = codeHtml;
	}
	public Equipements(String nomEqui, String codeHtml) {
		super();
		this.nomEqui = nomEqui;
		this.codeHtml = codeHtml;
	}
	public Equipements() {
		super();
		// TODO Auto-generated constructor stub
	}

	
}
