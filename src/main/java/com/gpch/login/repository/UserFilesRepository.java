package com.gpch.login.repository;

import org.springframework.data.repository.query.Param;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.gpch.login.model.UserFiles;





public interface UserFilesRepository extends CrudRepository<UserFiles, Long>{
	

	@Query("from UserFiles u where u.activite = :id")
	public List<UserFiles> FileActivite(@Param("id")Activite id);
}
