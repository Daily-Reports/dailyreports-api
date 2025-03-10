package com.vasconcellos.dailyreport.mapper;

import com.vasconcellos.dailyreport.dto.AreaDto;
import com.vasconcellos.dailyreport.model.Area;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface AreaMapper {

    AreaDto toDto(Area area);

    @Mapping(target = "orders", ignore = true)
    Area toEntity(AreaDto data);

}