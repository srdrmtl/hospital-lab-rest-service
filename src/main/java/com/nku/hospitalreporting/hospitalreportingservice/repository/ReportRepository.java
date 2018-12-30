package com.nku.hospitalreporting.hospitalreportingservice.repository;

import com.nku.hospitalreporting.hospitalreportingservice.model.Report;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author serdar
 */
@Repository
public interface ReportRepository extends JpaRepository<Report, Long>, ReportRepositoryCustom {
    
}
