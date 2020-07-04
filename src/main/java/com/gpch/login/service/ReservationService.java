package com.gpch.login.service;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.gpch.login.repository.Reservation;

public interface ReservationService {

	boolean creatPdf(Reservation reservation, ServletContext context, HttpServletRequest request,
			HttpServletResponse response);

}
