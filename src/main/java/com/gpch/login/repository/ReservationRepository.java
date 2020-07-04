package com.gpch.login.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.*;

public interface ReservationRepository extends JpaRepository<Reservation, Long>{

	@Query("from Reservation as res where "
			+ "(res.debutReservation > :dateDebut AND res.debutReservation < :dateFin) OR"
			+ "(res.debutReservation < :dateDebut AND res.finReservation > :dateFin) OR"
			+ "(res.finReservation > :dateDebut AND res.finReservation < :dateFin) OR"
			+ "(res.debutReservation > :dateDebut AND res.finReservation < :dateFin)"
		  )
	public List<Reservation> intersectionAvecAutresRes(@Param("dateDebut")String dateDebut,@Param("dateFin")String dateFin);

	@Modifying
	@Transactional
	@Query("update Reservation set status= :s where idReservation= :id")
	public void SetStatus(@Param("s")String status,@Param("id")Long id);
	
	@Query("from Reservation as res where res.status='En cours de confirmation'")
	public List<Reservation> ResNotConfirmed();
	
	@Query("select count(*) from Reservation as res where res.status='En cours de confirmation'")
	public int countResNotConfirmed();



}

