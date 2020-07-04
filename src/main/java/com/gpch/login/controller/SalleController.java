package com.gpch.login.controller;

import java.io.ByteArrayOutputStream;




import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.DateFormat;
import java.text.FieldPosition;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.jboss.logging.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.gpch.login.model.User;
import com.gpch.login.model.UserFiles;
import com.gpch.login.repository.*;
import com.gpch.login.service.ActiviteService;
import com.gpch.login.service.ReservationService;


import javassist.expr.NewArray;

@Controller
public class SalleController {
	@Autowired
	private MyUserDetailsService userService;
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private ReservationService reservationService;
	@Autowired
	private ServletContext context;
	
	//Cherche moi une classe qui implemente cet interface et tu va l'injecter
	@Autowired
	private SalleRepository salleRepository;
	@Autowired
	private EquipementRepository equipementRepository;
	
	@Autowired
	private ReservationRepository reservationRepository;
	
	@Autowired
	private ActiviteRepository activiteRepository;

	@Autowired
	private ActiviteService activiteService;
	@Autowired
	private UserFilesRepository userFilesRepository;

	
	@RequestMapping(value= "/salles")
	public String salles(Model model,@RequestParam(name="page",defaultValue ="0")int p,@RequestParam(name="size",defaultValue = "3")int s,@RequestParam(name="motCle", defaultValue="")String motCle)
	{
		Page<Salle> listeSalles = salleRepository.chercher("%"+motCle+"%",new PageRequest(p,s));
		List<Equipements> equipements = equipementRepository.findAll();
		model.addAttribute("listeSalles", listeSalles.getContent());
		//recuperation de nombre des pages
		int[] nombrePages = new int[ listeSalles.getTotalPages()];
		// le stoquer dans le modele pour le recuperer dans la page

    	
		model.addAttribute("nbrePage",nombrePages);
		model.addAttribute("pageCourante", p);
		model.addAttribute("motCle", motCle);
		model.addAttribute("equipements",equipements);
		//retourne la vue
		return "salles";
	}
	

	@GetMapping(value="/creafPdf",params= {"idRes"})
	public void creatPdf(Long idRes,HttpServletRequest request,HttpServletResponse response)
	{
		System.out.println("Hello bebgim");
		 Optional<Reservation> OptReservation = reservationRepository.findById(idRes);
		   	
		 Reservation reservation = OptReservation.get();
		 System.out.println("je suis a l'interieur de creaf pdf"+reservation.getDebutReservation());
		 System.out.println(reservation.getDebutReservation());
		 boolean isFlag = reservationService.creatPdf(reservation,context,request,response);
		 if(isFlag) {
			 
			 String fullPath = request.getServletContext().getRealPath("/resources/reports/"+"reservation"+".pdf");
			 System.out.println("Wa houha full path"+fullPath);
			 fileDownload(fullPath,response,"reservation.pdf");
			 
		 }
	}


	private void fileDownload(String fullPath, HttpServletResponse response, String fileName) {
		// TODO Auto-generated method stub
		File file = new File(fullPath);
		final int BUFFER_SIZE = 4096;
		if(file.exists())
		{
			try
			{
				FileInputStream inputStram = new FileInputStream(file);
				String mimeType = context.getMimeType(fullPath);
				response.setContentType(mimeType);
				response.setHeader("content-disposition", "attachment ; filename = "+fileName);
				OutputStream outputStram = response.getOutputStream();
				byte[] buffer = new byte[BUFFER_SIZE];
				int bytesRead = -1;
				while((bytesRead = inputStram.read(buffer))!=-1)
				{
					outputStram.write(buffer,0,bytesRead);
				}
				inputStram.close();
				outputStram.close();
				file.delete();
				
			}
			catch (Exception e) {
				// TODO: handle exception
			}
		}
		
	}

	
	
	@RequestMapping(value="/sallesAth")
	public ModelAndView sallesAth(@RequestParam(name="page",defaultValue ="0")int p,@RequestParam(name="size",defaultValue = "3")int s,@RequestParam(name="motCle", defaultValue="")String motCle)
	
	{
    	ModelAndView modelAndView = new ModelAndView();
    	Authentication auth = SecurityContextHolder.getContext().getAuthentication();
    	modelAndView.setViewName("sallesAth");
    	UserDetails user = userService.loadUserByUsername(auth.getName());    	
		Page<Salle> listeSalles = salleRepository.chercher("%"+motCle+"%",new PageRequest(p,s));
		List<Equipements> equipements = equipementRepository.findAll();
		modelAndView.addObject("listeSalles", listeSalles.getContent());
		//recuperation de nombre des pages
		int[] nombrePages = new int[ listeSalles.getTotalPages()];		
		modelAndView.addObject("nbrePage",nombrePages);
		modelAndView.addObject("pageCourante", p);
		modelAndView.addObject("motCle", motCle);
		modelAndView.addObject("equipements",equipements);
		modelAndView.addObject("user", user);
    	List<Reservation> listRes = reservationRepository.ResNotConfirmed();
    	Iterator iterator = listRes.iterator();
    	int a = reservationRepository.countResNotConfirmed();
    	System.out.println("Le nombre des reservations non confirmees: "+a);
    	while(iterator.hasNext())
    	{
    		Reservation rese = (Reservation) iterator.next();
    		System.out.println("Hahouwa"+rese.getStatus());
    	}
    	return modelAndView;
	}
	@GetMapping(value="/reservation")
	public String reservation(Model model,Long id)
	{

		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
    	UserDetails user = userService.loadUserByUsername(auth.getName());    	
		System.out.println("Id"+id);
		model.addAttribute("id",id);
		Optional<Salle> salleOp = salleRepository.findById(id);
		Salle salle = salleOp.get();
		//Date
        LocalDateTime l = LocalDateTime.now();
        String date2 = l.toString().substring(0, 16);
		System.out.println(date2);
		model.addAttribute("id", id);
		model.addAttribute("salle", salle);
		model.addAttribute("user",user);
		model.addAttribute("debut",date2);
		model.addAttribute("fin",date2);
		//Pour passer de la reservation formulaire au formulaire
		boolean bool=false;
		model.addAttribute("bool", bool);
		return "/reservation";
	}
	
	@GetMapping(value="/decouvrir")
	public ModelAndView decouvrir(Model model,Long id)
	{
		ModelAndView modelAndView = new ModelAndView();
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
    	UserDetails user = userService.loadUserByUsername(auth.getName());  
    	modelAndView.addObject("user", user);
		System.out.println("Id"+id);
		model.addAttribute("id",id);
		Optional<Salle> salleOp = salleRepository.findById(id);
		Salle salle = salleOp.get();
		modelAndView.addObject("salle",salle);
		modelAndView.setViewName("singleConference");
		return modelAndView;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

	public String delete(Long id,String motCle,int page)
	{
		salleRepository.deleteById(id);
		return "redirect:/salles?page="+page+"&motCle="+motCle;
		
	}

	@RequestMapping(value="/reserve",params = {"id","debut","fin"})
  public String reservation(Model model,Long id,String debut,String fin)
  {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
    	UserDetails user = userService.loadUserByUsername(auth.getName());    	
    	Optional<Salle> salleOp = salleRepository.findById(id);
		Salle salle = salleOp.get();
    	model.addAttribute("user", user);
		model.addAttribute("salle", salle);
		model.addAttribute("debut", debut);
		model.addAttribute("fin", fin);
		model.addAttribute("id", id);

		//Dissocation de debut
		String yearD= debut.substring(0,4);
		String monthD = debut.substring(5,7);
		String dayD = debut.substring(8,10);
		String hoursD = debut.substring(11,13);
		String minD = debut.substring(14,16);
		
		//Pour passer de la reservation formulaire au formulaire
		boolean bool=false;
		

		//Dissocation de fin
		String yearF= fin.substring(0,4);
		String monthF = fin.substring(5,7);
		String dayF = fin.substring(8,10);
		String hoursF = fin.substring(11,13);
		String minF = fin.substring(14,16);
	
		
		
		List<Reservation> res = reservationRepository.intersectionAvecAutresRes(yearD+"-"+monthD+"-"+dayD+" "+hoursD+":"+minD+":00", yearF+"-"+monthF+"-"+dayF+" "+hoursF+":"+minF+":00");
		Iterator<Reservation> it = res.iterator();
		while(it.hasNext())
		{
			System.out.println("Activite "+it.next().getTitreActivite());
		}
		if(res.isEmpty()==true)
		{
			model.addAttribute("resultat","Tres bien vous pouvez la reserver" );
			Reservation newRes = new Reservation(yearD+"-"+monthD+"-"+dayD+" "+hoursD+":"+minD+":00", yearF+"-"+monthF+"-"+dayF+" "+hoursF+":"+minF+":00", "Activite inseree", "Description activite inseree");
			newRes.setSalleReservee(salle);
			newRes.setReservateur(userRepository.findByEmail(user.getUsername()).get());
			//Activite activite = new Activite("titre1", "cloud1", null);
			//newRes.setActivite(activite);
			reservationRepository.save(newRes);
			Long idRes = newRes.getIdReservation();
			model.addAttribute("idRes", idRes);
			bool=true;
			
		}
		model.addAttribute("bool", bool);
		return "/reservation";
  }
	
	@RequestMapping(value="/MesReservation")
	public String MesReservation(Model model) throws IOException
	{
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
    	UserDetails user = userService.loadUserByUsername(auth.getName());    	
		model.addAttribute("user", user);
		Set<Reservation> reservations = userRepository.findByEmail(user.getUsername()).get().getReservations();
		model.addAttribute("reservations", reservations);
		Iterator<Reservation> it = reservations.iterator();
		Reservation res = it.next();
		 List<UserFiles> files = activiteService.ReservationFile(res.getIdReservation());
		 Iterator<UserFiles> it3 = files.iterator();
		 UserFiles us = it3.next();
		 String filePath = us.construireFile();
		 model.addAttribute("filepath", filePath);
		 System.out.println("Construire file: "+res.getActivite().getFiles().size());
		/*
		FileOutputStream fos = new FileOutputStream("src\\main\\resources\\templates\\file2.docx");
		fos.write(res.getActivite().getBytArrayFromFile());
		fos.close();
		*/
		return "/MesReservation";
	}
	
	//formulaire de la reservation
	@RequestMapping(value="/index",params= {"idRes"})
	public String getFormulaireReservation(Model model,Long idRes)
	{
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
    	UserDetails user = userService.loadUserByUsername(auth.getName());    	   
    	model.addAttribute("idRes",idRes);
		model.addAttribute("user", user);
		model.addAttribute("activite", new Activite());
		model.addAttribute("userFiles", new UserFiles());
		return "/index";
	}
	
	
	
	@PostMapping(value="/save",params= {"idRes"})
	public String saveReservation(@ModelAttribute Activite activite, RedirectAttributes redirectAttributes,Model model,@RequestParam(name="idRes")Long idRes) throws IOException
	{
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();

    	UserDetails user = userService.loadUserByUsername(auth.getName());    	
   	    Optional<Reservation> OptReservation = reservationRepository.findById(idRes);
	   	
		 Reservation reservation = OptReservation.get();
		 Activite dbActivite = activiteService.save(activite);
		 List<UserFiles> list = userFilesRepository.FileActivite(dbActivite);
		 System.out.println(list.size());
		 String fichier = list.get(0).construireFile(); 
		 reservation.setFilePath(fichier);
		 
		 reservation.setStatus("En cours de confirmation");
	 	
		reservation.setActivite(activite);
		reservationRepository.save(reservation);
    	
		if(dbActivite != null)
		{
			redirectAttributes.addFlashAttribute("successmessage", "Reservation est ajoutee avec succes");
			return "redirect:/index?idRes="+idRes;
		}
		else
		{
			model.addAttribute("errormessage", "La Reservation n'est pas ajoutee");
			model.addAttribute("activite", new Activite());
			return "/index?idRes="+idRes;
		}
	
	}
	
	@RequestMapping(value="/acceuilAth")
	public String acceuilAuth(Model model)
	{
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();

    	UserDetails user = userService.loadUserByUsername(auth.getName());
    	System.out.println("acceuilAth user"+user.getUsername());
		model.addAttribute("user", user);
		return "acceuilAth";
	}
	/*
	//Verification du formulaire reservation
	@RequestMapping(value="/MesReservations",params= {"idRes","name","theme","file"})
	public String ReservationAccepter(Model model,Long idRes,String name,String theme,String file) throws IOException
	{
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
    	User user = userService.findUserByEmail(auth.getName());
		model.addAttribute("user", user);
		System.out.println("name:"+name);
		System.out.println("file location:"+file);
		System.out.println("theme"+theme);
		
		//Enrigistrement de l'activite 
		File fichier = new File("C:\\Users\\asus\\Desktop\\"+file);
		
		 Optional<Reservation> OptReservation = reservationRepository.findById(idRes);
		 Reservation reservation = OptReservation.get();
		 Activite newActivite = new Activite(name, theme,this.getByteArrayFromFile(fichier) );
		 activiteRepository.save(newActivite);
		 reservation.setStatus("En cours de confirmation");
	 	//reservationRepository.SetStatus("refuse",idRes);

		reservation.setActivite(newActivite);
		reservationRepository.save(reservation);
		 //enrigistrement activite 

		return "/MesReservation";
	}
	*/

	
	
	
}
