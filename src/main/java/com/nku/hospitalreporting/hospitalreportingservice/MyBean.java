package com.nku.hospitalreporting.hospitalreportingservice;

/**
 *
 * @author serdar
 */
import com.nku.hospitalreporting.hospitalreportingservice.controller.LaborantController;
import com.nku.hospitalreporting.hospitalreportingservice.model.Laborant;
import com.nku.hospitalreporting.hospitalreportingservice.repository.LaborantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.*;
import org.springframework.stereotype.*;

@Component
public class MyBean implements CommandLineRunner {
    
        @Autowired
        LaborantController cont;
        
        @Autowired
        LaborantRepository repository;

        @Override
	public void run(String... args) {
            
            Laborant lab = new Laborant();
            lab.setUsername("admin");
            lab.setPassword("12345");
            
            if(repository.findByUsername("admin") == null) {
               cont.signUp(lab);
            } else {
                System.out.println("Bu Kullanıcı Daha Önce Oluşturuldu.");
            }
            
	}

}
