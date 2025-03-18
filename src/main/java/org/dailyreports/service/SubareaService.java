package org.dailyreports.service;

import org.dailyreports.dto.SubareaDto;
import org.dailyreports.exception.ResourceNotFoundException;
import org.dailyreports.mapper.SubareaMapper;
import org.dailyreports.model.Subarea;
import org.dailyreports.repository.SubareaRepository;
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