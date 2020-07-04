package com.gpch.login.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

import com.gpch.login.repository.Activite;

@Entity
public class UserFiles implements Serializable {
	
	@Id
	@GeneratedValue
	@Column(name="id_file")
	private Long id;
	private String fileName;
	private String modifiedFileName;
	private String fileExtension;
	@ManyToOne
	@JoinColumn(name="id_activite")
	private Activite activite;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getFileName() {
		return fileName;
	}
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	public String getModifiedFileName() {
		return modifiedFileName;
	}
	public void setModifiedFileName(String modifiedFileName) {
		this.modifiedFileName = modifiedFileName;
	}
	public String getFileExtension() {
		return fileExtension;
	}
	public void setFileExtension(String fileExtension) {
		this.fileExtension = fileExtension;
	}


	public Activite getActivite() {
		return activite;
	}
	public void setActivite(Activite activite) {
		this.activite = activite;
	}
	public String construireFile()
	{
		System.out.println("Voici le fichier construit " +this.getFileName()+this.getModifiedFileName()+"."+this.getFileExtension());
		return "pathimages/"+this.getModifiedFileName();
	}
	
	
	

}
