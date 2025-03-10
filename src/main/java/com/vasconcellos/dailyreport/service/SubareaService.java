package com.vasconcellos.dailyreport.service;

import com.vasconcellos.dailyreport.dto.SubareaDto;
import com.vasconcellos.dailyreport.exception.ResourceNotFoundException;
import com.vasconcellos.dailyreport.mapper.SubareaMapper;
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
    private final SubareaMapper subareaMapper;

    public SubareaDto save(SubareaDto data) {
        Subarea subarea = subareaMapper.toEntity(data);

        return subareaMapper.toDto(subareaRepository.save(subarea));
    }

    public SubareaDto findById(Long id) {
        return subareaMapper.toDto(findByIdAsEntity(id));
    }

    public Subarea findByIdAsEntity(Long id) {
        return subareaRepository.findById(id).orElseThrow(() ->
                new ResourceNotFoundException("Subarea cannot be found"));
    }

    public List<SubareaDto> findAll() {
        return subareaRepository.findAll().stream().map(subareaMapper::toDto).toList();
    }

    public void deleteById(Long id) {
        subareaRepository.deleteById(id);
    }
}