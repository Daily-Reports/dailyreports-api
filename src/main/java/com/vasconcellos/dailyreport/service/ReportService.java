package com.vasconcellos.dailyreport.service;

import com.vasconcellos.dailyreport.dto.ReportDto;
import com.vasconcellos.dailyreport.mapper.ReportMapper;
import com.vasconcellos.dailyreport.model.Report;
import com.vasconcellos.dailyreport.repository.ReportRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
@Transactional
public class ReportService {

    private final ReportRepository reportRepository;
    private final ReportMapper reportMapper;

    public Report save(ReportDto data) {
        Report toSave = reportMapper.toEntity(data);

        return reportRepository.save(toSave);
    }

    public Optional<Report> findById(Long id) {
        return reportRepository.findById(id);
    }

    public List<Report> findAll() {
        return reportRepository.findAll();
    }

    public void deleteById(Long id) {
        reportRepository.deleteById(id);
    }
}