package org.dailyreports.mapper;

import org.dailyreports.dto.EventDto;
import org.dailyreports.model.Event;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface EventMapper {

    EventDto toDto(Event area);

    @Mapping(target = "orders", ignore = true)
    Event toEntity(EventDto data);

}