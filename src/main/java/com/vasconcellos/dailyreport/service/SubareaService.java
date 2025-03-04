package com.vasconcellos.dailyreport.service;

import com.vasconcellos.dailyreport.dto.SubareaDto;
import com.vasconcellos.dailyreport.exception.ResourceNotFoundException;
import com.vasconcellos.dailyreport.model.Subarea;
import com.vasconcellos.dailyreport.repository.SubareaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RequiredArgsConstructor
@Service
@Transactional
public class SubareaService {

    private final SubareaRepository subareaRepository;

    public Subarea save(SubareaDto data) {
        Subarea subarea = new Subarea();
        subarea.setName(data.getName());

        return subareaRepository.save(subarea);
    }

    public Subarea findById(Long id) {
        return subareaRepository.findById(id).orElseThrow(() ->
                new ResourceNotFoundException("Subarea cannot be found"));
    }

    public List<Subarea> findAll() {
        return subareaRepository.findAll();
    }

    public void deleteById(Long id) {
        subareaRepository.deleteById(id);
    }
}
