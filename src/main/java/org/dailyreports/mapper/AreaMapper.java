package org.dailyreports.mapper;

import org.dailyreports.dto.AreaDto;
import org.dailyreports.model.Area;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface AreaMapper {

    AreaDto toDto(Area area);

    @Mapping(target = "orders", ignore = true)
    Area toEntity(AreaDto data);

}