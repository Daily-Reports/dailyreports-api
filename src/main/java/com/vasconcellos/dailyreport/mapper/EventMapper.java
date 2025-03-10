package com.vasconcellos.dailyreport.mapper;

import com.vasconcellos.dailyreport.dto.EventDto;
import com.vasconcellos.dailyreport.model.Event;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface EventMapper {

    EventDto toDto(Event area);

    @Mapping(target = "orders", ignore = true)
    Event toEntity(EventDto data);

}