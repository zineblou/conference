package com.gpch.login;

import java.io.File;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.sql.Blob;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.Iterator;


import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.util.concurrent.ListenableFuture;

import com.gpch.login.model.User;
import com.gpch.login.repository.*;


@SpringBootApplication
@EnableJpaRepositories()
public class LoginApplication {

    public static void main(String[] args) throws ParseException, IOException {
       

    	BeanFactory ctx=SpringApplication.run(LoginApplication.class, args);
	
    	ActiviteRepository activiteRepository = ctx.getBean(ActiviteRepository.class);
    

		SalleRepository salleRepository = ctx.getBean(SalleRepository.class);
		 EquipementRepository  equipementRepository = ctx.getBean(EquipementRepository.class);
		 //saving some instances of salles
		 /*
		 salleRepository.save(new Salle("Idéal pour vos réunions, vos événements comme des défilés, showroom, pop-up store et vos productions. Ce lieu peut accueillir jusqu'à 500 personnes.", 100, "https://www.le55.ca/wp-content/uploads/2018/11/salle-conference-complexe-hotelier-le-55.jpg",TypeSalle.reunion));
		 salleRepository.save(new Salle("Ce lieu haut-de-gamme est très lumineux grâce à un puit de lumière, plein de charme, il vous envoutera grâce à son atmosphère créative", 200, "http://www.mobilier.fr/wp-content/uploads/2010/11/salle-conference-pleine.jpg",TypeSalle.conference));
		 salleRepository.save(new Salle("Ce lieu haut-de-gamme est très lumineux grâce à un puit de lumière, plein de charme, il vous envoutera grâce à son atmosphère créative", 300, "https://addislighting.com/wp-content/uploads/2015/06/eclairage-led-salle-de-conference-addis-lighting.jpg",TypeSalle.reunion));
		 salleRepository.save(new Salle("Ce lieu haut-de-gamme est très lumineux grâce à un puit de lumière, plein de charme, il vous envoutera grâce à son atmosphère créative", 400, "https://cdn.kactus.com/pictures/000/084/239/large/mercure_rouen_champ_de_mars_salle_de_seminaire.jpg?1507425979",TypeSalle.conference));

		 salleRepository.save(new Salle("Ce lieu haut-de-gamme est très lumineux grâce à un puit de lumière, plein de charme, il vous envoutera grâce à son atmosphère créative", 500, "https://cdn.kactus.com/pictures/000/097/175/large/now_coworking_rouen_1.jpg?1507448142",TypeSalle.reunion));
		 salleRepository.save(new Salle("Salle royal6", 600, "https://cdn.kactus.com/pictures/000/049/104/large/novotel_rouen_sud_24.jpg?1507424770",TypeSalle.conference));
		 salleRepository.save(new Salle("Salle royal7", 700, "https://cdn.kactus.com/pictures/000/108/780/large/work_s_day_salle_de_reception.jpg?1509462036",TypeSalle.reunion));
		 salleRepository.save(new Salle("Salle royal8", 800, "https://cdn.kactus.com/pictures/000/119/441/large/l_usine_cafe_coworking_4.jpg?1527258809",TypeSalle.conference));
		 salleRepository.save(new Salle("Salle royal9", 900, "https://cdn.kactus.com/pictures/000/136/677/large/le_village_by_ca_rouen_vallee_de_seine_terrasse.jpg?1549285911",TypeSalle.reunion));
		 salleRepository.save(new Salle("Salle royal10", 1000, "https://cdn.kactus.com/pictures/000/121/509/large/blue_shaker_penthouse.jpg?1530605986",TypeSalle.conference));
		 salleRepository.save(new Salle("Salle royal11", 1100, "https://cdn.kactus.com/pictures/000/115/171/large/keeze_trocadero_atelier_keeze_trocadero.jpg?1521728783",TypeSalle.reunion));
		 salleRepository.save(new Salle("Salle royal12", 1200, "https://cdn.kactus.com/pictures/000/096/278/large/keeze_17.jpg?1507447235",TypeSalle.conference));
		 salleRepository.save(new Salle("Salle royal13", 1300, "https://cdn.kactus.com/pictures/000/131/630/large/espace_cinko_27.jpg?1542877038",TypeSalle.reunion));
		 salleRepository.save(new Salle("Salle royal14", 1400, "https://cdn.kactus.com/pictures/000/124/956/large/hub_lab_paris_30.jpg?1535473175",TypeSalle.conference));
		 salleRepository.findAll().forEach(p->System.out.println(p.getCapacite()));
		 for(int i=0; i<300;i++) {
			 salleRepository.
		 }

		 Equipements equi1= new Equipements("Micro", "<i class=\"fas fa-microphone\"></i>");
		 Equipements equi2= new Equipements("WIFI", "<i class=\"fas fa-wifi\"></i>");
		 Equipements equi3= new Equipements("Video-projecteur", "<i class=\"fas fa-video\"></i>");
		 Equipements equi4= new Equipements("Laptop", " <i class=\"fas fa-laptop\"></i>");
		 Equipements equi5= new Equipements("Podium", "<i class=\"fas fa-chair\"></i>");
		 Equipements equi6= new Equipements("Climatisation","<i class=\"fas fa-wind\"></i>");
		 Equipements equi7= new Equipements("Restaurant","<i class=\"fas fa-utensils\"></i>");
		 
		 equipementRepository.save(equi1);
		 equipementRepository.save(equi2);
		 equipementRepository.save(equi3);
		 equipementRepository.save(equi4);
		 equipementRepository.save(equi5);
		 equipementRepository.save(equi6);
		 equipementRepository.save(equi7);
		 List<Equipements> equipements = equipementRepository.findAll();
		 Iterator it = equipements.iterator();
		 while(it.hasNext())
		 {
			 Equipements equi = (Equipements) it.next();
			 System.out.println(equi.getNomEqui());
			 
		 }

 */

    	
    	
    
    }
   
}

