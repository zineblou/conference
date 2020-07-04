package com.gpch.login.model;

import lombok.AllArgsConstructor;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import org.hibernate.validator.constraints.Length;
import org.springframework.beans.factory.annotation.Value;

import com.gpch.login.repository.Reservation;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import java.util.Set;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "user")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "user_id")
    private int id;
    
    @OneToMany(mappedBy="reservateur",cascade=CascadeType.ALL)
    private Set<Reservation> reservations;
    
    public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public boolean getActive() {
		return active;
	}
	public void setActive(boolean active) {
		this.active = active;
	}
	public String getRoles() {
		return roles;
	}
	public void setRoles(String roles) {
		this.roles = roles;
	}
	@Value("normal")
	private String role="normal";
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
	@Column(name = "email")
    @Email(message = "*S'il vous plait entrer un email")
    @NotEmpty(message = "*S'il vous plait entrer un email")
    private String email;
    @Column(name = "password")
    @Length(min = 5, message = "*Votre mot de passe doit contenir au moin 5 caracteres")
    @NotEmpty(message = "*s'il vous plait entrer un mot de passe")
    private String password;
    @Column(name = "name")
    @NotEmpty(message = "*s'il vous entrez votre nom")
    private String name;
    @Column(name = "last_name")
    @NotEmpty(message = "*s'il vous entrez votre prenom")
    private String lastName;
    @Column(name = "active")
    private boolean active;
    private String roles;


	public Set<Reservation> getReservations() {
		return reservations;
	}
	public void setReservations(Set<Reservation> reservations) {
		this.reservations = reservations;
	}

}
