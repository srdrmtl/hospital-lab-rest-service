package com.nku.hospitalreporting.hospitalreportingservice.controller;

import com.nku.hospitalreporting.hospitalreportingservice.exception.ResourceNotFoundException;
import com.nku.hospitalreporting.hospitalreportingservice.model.Report;
import com.nku.hospitalreporting.hospitalreportingservice.repository.ReportRepository;
import java.util.List;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author serdar
 */
@RestController
public class ReportController {

    @Autowired
    ReportRepository repository;

    @GetMapping("/report/all")
    public List<Report> getAllReport() {
        return repository.findAll();
    }

    @GetMapping("report/{id}")
    public Report getReport(@Valid @PathVariable(value = "id") Long id) throws ResourceNotFoundException {
        Report report = repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Report not found for this id :: " + id));
        return report;
    }

    @PostMapping("report/add")
    public ResponseEntity addReport(@RequestBody Report report) {
        return ResponseEntity.ok(repository.save(report));
    }

    @PutMapping("report/update/{id}")
    public ResponseEntity<Report> updateReport(@PathVariable(value = "id") Long id, @Valid @RequestBody Report reportDetails) throws ResourceNotFoundException {
        Report report = repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Report not found for this id :: " + id));
        report.setBazofilikErit(reportDetails.getBazofilikErit());
        report.setBazofilikSeri(reportDetails.getBazofilikSeri());
        report.setComak(reportDetails.getComak());
        report.setDosyaNo(reportDetails.getDosyaNo());
        report.setEozinofilikSeri(reportDetails.getEozinofilikSeri());
        report.setLenfosit(reportDetails.getLenfosit());
        report.setMegakaryositler(reportDetails.getMegakaryositler());
        report.setMetamyelosit(reportDetails.getMetamyelosit());
        report.setMonosit(reportDetails.getMonosit());
        report.setMyelosit(reportDetails.getMyelosit());
        report.setMyloblast(reportDetails.getMyloblast());
        report.setOrtokromantofilikErit(reportDetails.getOrtokromantofilikErit());
        report.setParcali(reportDetails.getParcali());
        report.setPlazmaHucresi(reportDetails.getPlazmaHucresi());
        report.setPolikromalofilikErit(reportDetails.getPolikromalofilikErit());
        report.setProeritroblast(reportDetails.getProeritroblast());
        report.setPromonosit(reportDetails.getPromonosit());
        report.setPromyelosit(reportDetails.getPromyelosit());
        report.setRapor(reportDetails.getRapor());
        report.setRaporEden(reportDetails.getRaporEden());
        report.setRaporId(reportDetails.getRaporId());
        report.setTani(reportDetails.getTani());
        report.setTarih(reportDetails.getTarih());
        final Report updatedReport = repository.save(report);
        return ResponseEntity.ok(updatedReport);
    }

    @GetMapping("report/find/{searchText}")
    public List<Report> searchReports(@PathVariable(value = "searchText") String searchText) {
        return repository.searchReports(searchText);
    }
    
    @GetMapping("report/fileid/{fileId}")
    public ResponseEntity getReportWithFileId(@PathVariable(value = "fileId") String fileId) {
        try {
            Report report = repository.findByFileId(fileId).get(0);
            return ResponseEntity.ok(report);
        } catch (Exception e) {
            return ResponseEntity.ok(false);
        }
    }
}
