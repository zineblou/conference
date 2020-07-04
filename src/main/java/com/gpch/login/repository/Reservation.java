package com.gpch.login.repository;

import java.io.Serializable;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.*;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;
import org.springframework.web.multipart.MultipartFile;

import com.gpch.login.model.User;

@Entity
public class Reservation implements Serializable {
	@Id
	@GeneratedValue
	private Long idReservation;
	
	@OneToOne
	@JoinColumn(name="id_activite")
	private Activite activite;
	public int getA() {
		return a;
	}
	public void setA(int a) {
		this.a = a;
	}
	private String filePath;
	private int a = 0;

	public String getFilePath() {
		return filePath;
	}
	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}
	public Activite getActivite() {
		return activite;
	}
	private String status;
	

	public void setActivite(Activite activite) {
		this.activite = activite;
	}
	private String debutReservation;
	
    private String finReservation;

	private String titreActivite;
	private String descriptionActivite;
	@ManyToOne
	@JoinColumn
	private User reservateur;
	public Salle getSalleReservee() {
		return salleReservee;
	}
	public void setSalleReservee(Salle salleReservee) {
		this.salleReservee = salleReservee;
	}
	@ManyToOne
	@JoinColumn
	private Salle salleReservee;
	public Reservation(String debutReservation, String finReservation, String titreActivite, String descriptionActivite) {
		super();
		this.debutReservation = debutReservation;
		this.finReservation = finReservation;
		this.titreActivite = titreActivite;
		this.descriptionActivite = descriptionActivite;
	}
	public Reservation() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Long getIdReservation() {
		return idReservation;
	}
	public void setIdReservation(Long idReservation) {
		this.idReservation = idReservation;
	}
	public String getDebutReservation() {
		return debutReservation;
	}
	public void setDebutReservation(String debutReservation) {
		this.debutReservation = debutReservation;
	}
	public String getFinReservation() {
		return finReservation;
	}
	public void setFinReservation(String finReservation) {
		this.finReservation = finReservation;
	}
	public String getTitreActivite() {
		return titreActivite;
	}
	public void setTitreActivite(String titreActivite) {
		this.titreActivite = titreActivite;
	}
	public String getDescriptionActivite() {
		return descriptionActivite;
	}
	public void setDescriptionActivite(String descriptionActivite) {
		this.descriptionActivite = descriptionActivite;
	}
	public User getReservateur() {
		return reservateur;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public void setReservateur(User reservateur) {
		this.reservateur = reservateur;
	}
	

}
