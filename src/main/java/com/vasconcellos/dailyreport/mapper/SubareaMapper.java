package com.vasconcellos.dailyreport.mapper;

import com.vasconcellos.dailyreport.dto.SubareaDto;
import com.vasconcellos.dailyreport.model.Subarea;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface SubareaMapper {

    SubareaDto toDto(Subarea area);

    @Mapping(target = "orders", ignore = true)
    Subarea toEntity(SubareaDto data);

}