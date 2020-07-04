package com.gpch.login.repository;

import org.springframework.data.domain.Page;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

public interface SalleRepository extends JpaRepository<Salle,Long> {

	@Query("select s from Salle as s where s.nom like :x")
	public Page<Salle> chercher(@Param("x")String motCle,Pageable pageable);

	@Query("select s from Salle as s where s.id= :x")
	public Salle chercher(@Param("x")Long id);
	
	@Query("from Salle as salle where salle.id=1")
	public Salle cherchertest();
}
