package com.vasconcellos.dailyreport.mapper;

import com.vasconcellos.dailyreport.dto.order.OrderDto;
import com.vasconcellos.dailyreport.model.Area;
import com.vasconcellos.dailyreport.model.Event;
import com.vasconcellos.dailyreport.model.Order;
import com.vasconcellos.dailyreport.model.Subarea;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface OrderMapper {

    @Mapping(source = "data.id", target = "id")
    @Mapping(source = "event", target = "event")
    @Mapping(source = "area", target = "area")
    @Mapping(source = "subarea", target = "subarea")
    @Mapping(target = "notes", ignore = true)
    Order toEntity(OrderDto data, Event event, Area area, Subarea subarea);

    @Mapping(source = "event.id", target = "eventId")
    @Mapping(source = "area.id", target = "areaId")
    @Mapping(source = "subarea.id", target = "subareaId")
    OrderDto toDto(Order order);

}