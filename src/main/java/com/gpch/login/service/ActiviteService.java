package com.gpch.login.service;

import java.util.List;
import java.util.Map;

import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.gpch.login.model.UserFiles;
import com.gpch.login.repository.*;


public interface ActiviteService {

	public Activite save(Activite activite);
    public List<UserFiles> ReservationFile(Long id);
	public Activite find(Long id);
	public List<Map<String,Object>> report();
	public Iterable<Activite> findAll();
	
}
