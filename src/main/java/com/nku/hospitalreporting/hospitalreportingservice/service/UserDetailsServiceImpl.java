package com.nku.hospitalreporting.hospitalreportingservice.service;

import com.nku.hospitalreporting.hospitalreportingservice.model.Laborant;
import com.nku.hospitalreporting.hospitalreportingservice.repository.LaborantRepository;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import static java.util.Collections.emptyList;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    private LaborantRepository laborantRepository;

    public UserDetailsServiceImpl(LaborantRepository laborantRepository) {
        this.laborantRepository = laborantRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Laborant applicationUser = laborantRepository.findByUsername(username);
        if (applicationUser == null) {
            throw new UsernameNotFoundException(username);
        }
        return new User(applicationUser.getUsername(), applicationUser.getPassword(), emptyList());
    }
}
