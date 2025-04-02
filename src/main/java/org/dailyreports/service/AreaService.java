package org.dailyreports.service;

import lombok.RequiredArgsConstructor;
import org.dailyreports.dto.area.AreaDto;
import org.dailyreports.dto.area.AreaUpdateDto;
import org.dailyreports.exception.ResourceNotFoundException;
import org.dailyreports.mapper.AreaMapper;
import org.dailyreports.model.Area;
import org.dailyreports.repository.AreaRepository;
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

    public AreaDto update(long id, AreaUpdateDto data) {
        Area area = findByIdAsEntity(id);

        if(data.getName() != null)
            area.setName(data.getName());

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