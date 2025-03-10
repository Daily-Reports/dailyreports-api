package com.vasconcellos.dailyreport.service;

import com.vasconcellos.dailyreport.dto.AreaDto;
import com.vasconcellos.dailyreport.exception.ResourceNotFoundException;
import com.vasconcellos.dailyreport.mapper.AreaMapper;
import com.vasconcellos.dailyreport.model.Area;
import com.vasconcellos.dailyreport.repository.AreaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RequiredArgsConstructor
@Service
@Transactional
public class AreaService {

    private final AreaRepository areaRepository;
    private final AreaMapper areaMapper;

    public AreaDto save(AreaDto data) {
        Area area = areaMapper.toEntity(data);

        return areaMapper.toDto(areaRepository.save(area));
    }

    public AreaDto findById(Long id) {
        return areaMapper.toDto(findByIdAsEntity(id));
    }

    public Area findByIdAsEntity(Long id) {
        return areaRepository.findById(id).orElseThrow(() ->
                new ResourceNotFoundException("Area cannot be found."));
    }

    public List<AreaDto> findAll() {
        return areaRepository.findAll().stream().map(areaMapper::toDto).toList();
    }

    public void deleteById(Long id) {
        areaRepository.deleteById(id);
    }
}