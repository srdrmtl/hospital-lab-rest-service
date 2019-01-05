package com.nku.hospitalreporting.hospitalreportingservice.controller;

import com.nku.hospitalreporting.hospitalreportingservice.model.Laborant;
import com.nku.hospitalreporting.hospitalreportingservice.repository.LaborantRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author serdar
 */
@RestController
@RequestMapping("/laborant")
public class LaborantController {
    
    private LaborantRepository laborantRepository;
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    public LaborantController(LaborantRepository laborantRepository,
                               BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.laborantRepository = laborantRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    @RequestMapping(value = "/sign-up", method = RequestMethod.POST)
    public void signUp(@RequestBody Laborant laborant) {
        laborant.setPassword(bCryptPasswordEncoder.encode(laborant.getPassword()));
        laborantRepository.save(laborant);
}
    
}
