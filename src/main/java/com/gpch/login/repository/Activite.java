package com.gpch.login.repository;

import java.io.ByteArrayOutputStream;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;

import org.springframework.web.multipart.MultipartFile;

import com.gpch.login.model.UserFiles;

@Entity
public class Activite implements Serializable{
	@Id
	@GeneratedValue
	@Column(name="id_activite")
	private Long id;	
	private String titre;
	private String theme;




	/*
	@Lob 
	public byte[] BytArrayFromFile;
*/
	@javax.persistence.Transient
	private List<MultipartFile> files = new ArrayList<MultipartFile>();
	
    public List<MultipartFile> getFiles() {
    	
		return files;
	}
	public void setFiles(List<MultipartFile> files) {
		this.files = files;
	}
	public Activite() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getTitre() {
		return titre;
	}
	public void setTitre(String titre) {
		this.titre = titre;
	}
	public String getTheme() {
		return theme;
	}
	public void setTheme(String theme) {
		this.theme = theme;
	}

	
	
	public byte[] getByteArrayFromFile(final File handledDocument) throws IOException {
		final ByteArrayOutputStream baos = new ByteArrayOutputStream();
		final InputStream in = new FileInputStream(handledDocument);
		final byte[] buffer = new byte[500];
		int read = -1;
		while ((read = in.read(buffer)) > 0) {
		    baos.write(buffer, 0, read);
		}
		in.close();
		return baos.toByteArray();
		}

}


