/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nku.hospitalreporting.hospitalreportingservice.repository;

import com.nku.hospitalreporting.hospitalreportingservice.model.Report;
import java.util.List;

/**
 *
 * @author serdar
 */
public interface ReportRepositoryCustom {

    public List<Report> searchReports(String searchText);
    public List<Report> findByFileId(String fileId);
}
