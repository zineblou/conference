package com.gpch.login.controller;

import java.util.Iterator;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.gpch.login.repository.Reservation;
import com.gpch.login.repository.ReservationRepository;
@Controller
public class AdminPageController {
	@Autowired
	private ReservationRepository reservationRepository;
	
	@RequestMapping("/pageAdmin")
	public String pageAdmin(Model model)
	{
		List<Reservation> listRes = reservationRepository.ResNotConfirmed();
    	Iterator iterator = listRes.iterator();
    	int a = reservationRepository.countResNotConfirmed();
    	System.out.println("Le nombre des reservations non confirmees "+a);
    	model.addAttribute("nbreRes", a);
    	model.addAttribute("listRes", listRes);
    	while(iterator.hasNext())
    	{
    		Reservation rese = (Reservation) iterator.next();
    		System.out.println("Hahouwa"+rese.getStatus());
    	}
		
		return "/pageAdmin";
	}
	
	@RequestMapping(value="/adminConfirmation",params={"idRes"})
	public String adminConfirmation(Model model,@RequestParam(name="idRes")Long idRes)
	{
		Optional<Reservation> resOp = reservationRepository.findById(idRes);
		Reservation res = resOp.get();
		model.addAttribute("reservation", res);
		return "/adminConfirmation";
		
	}
	
	@RequestMapping(value="/refuser",params={"idRes"})
	public String refuse(Model model,@RequestParam(name="idRes")Long idRes)
	{
		Optional<Reservation> resOp = reservationRepository.findById(idRes);
		Reservation res = resOp.get();
		res.setStatus("refuser");
		reservationRepository.save(res);
		return "redirect:/pageAdmin";
	}
	@RequestMapping(value="/accepter",params={"idRes"})
	public String accepter(Model model,@RequestParam(name="idRes")Long idRes)
	{
		Optional<Reservation> resOp = reservationRepository.findById(idRes);
		Reservation res = resOp.get();
		res.setA(1);
		res.setStatus("accepter");
		reservationRepository.save(res);
		return "redirect:/pageAdmin";
	}
	
	
}
