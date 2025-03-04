package com.vasconcellos.dailyreport.service;

import com.vasconcellos.dailyreport.dto.AreaDto;
import com.vasconcellos.dailyreport.exception.ResourceNotFoundException;
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

    public Area save(AreaDto data) {
        Area area = new Area();
        area.setName(data.getName());

        return areaRepository.save(area);
    }

    public Area findById(Long id) {
        return areaRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Area cannot be found."));
    }

    public List<Area> findAll() {
        return areaRepository.findAll();
    }

    public void deleteById(Long id) {
        areaRepository.deleteById(id);
    }
}
