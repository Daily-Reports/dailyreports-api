package org.dailyreports.mapper;

import org.dailyreports.dto.SubareaDto;
import org.dailyreports.model.Subarea;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface SubareaMapper {

    SubareaDto toDto(Subarea area);

    @Mapping(target = "orders", ignore = true)
    Subarea toEntity(SubareaDto data);

}