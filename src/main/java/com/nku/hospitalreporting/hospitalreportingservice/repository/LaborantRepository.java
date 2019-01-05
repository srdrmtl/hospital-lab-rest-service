package com.nku.hospitalreporting.hospitalreportingservice.repository;

import com.nku.hospitalreporting.hospitalreportingservice.model.Laborant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author serdar
 */
@Repository
public interface LaborantRepository extends JpaRepository<Laborant, Long> {

    Laborant findByUsername(String username);
}
