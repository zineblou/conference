package com.gpch.login.repository;

import javax.persistence.*;

import org.springframework.context.annotation.Primary;

import java.io.Serializable;
import java.util.Set;
@Entity
public class Salle implements Serializable{
	@Id
	@GeneratedValue
	private Long id;
	private String nom;
	private int capacite;
	private String image;
	private TypeSalle typeSalle;
	
    @OneToMany(mappedBy="salleReservee",cascade=CascadeType.ALL)
    private Set<Reservation> reservations;
	
	
	public Set<Reservation> getReservations() {
		return reservations;
	}
	public void setReservations(Set<Reservation> reservations) {
		this.reservations = reservations;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getNom() {
		return nom;
	}
	public void setNom(String nom) {
		this.nom = nom;
	}
	public int getCapacite() {
		return capacite;
	}
	public void setCapacite(int capacite) {
		this.capacite = capacite;
	}
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}
	public Salle() {
		super();
		// TODO Auto-generated constructor stub
	}
	public TypeSalle getTypeSalle() {
		return typeSalle;
	}
	public void setTypeSalle(TypeSalle typeSalle) {
		this.typeSalle = typeSalle;
	}
	public Salle(String nom, int capacite, String image, TypeSalle typeSalle) {
		super();
		this.nom = nom;
		this.capacite = capacite;
		this.image = image;
		this.typeSalle = typeSalle;
	}

}
