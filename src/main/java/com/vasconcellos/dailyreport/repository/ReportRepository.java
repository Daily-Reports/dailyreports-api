package com.vasconcellos.dailyreport.repository;

import com.vasconcellos.dailyreport.model.Report;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReportRepository extends JpaRepository<Report, Long> {

}