package com.vasconcellos.dailyreport.mapper;

import com.vasconcellos.dailyreport.dto.OrderDto;
import com.vasconcellos.dailyreport.model.Area;
import com.vasconcellos.dailyreport.model.Event;
import com.vasconcellos.dailyreport.model.Order;
import com.vasconcellos.dailyreport.model.Subarea;
import com.vasconcellos.dailyreport.service.AreaService;
import com.vasconcellos.dailyreport.service.EventService;
import com.vasconcellos.dailyreport.service.SubareaService;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.springframework.beans.factory.annotation.Autowired;

@Mapper(componentModel = "spring")
public abstract class OrderMapper {

    @Autowired
    private EventService eventService;
    @Autowired
    private AreaService areaService;
    @Autowired
    private SubareaService subareaService;

    @Mapping(source = "eventId", target = "event", qualifiedByName = "mapEventById")
    @Mapping(source = "areaId", target = "area", qualifiedByName = "mapAreaById")
    @Mapping(source = "subareaId", target = "subarea", qualifiedByName = "mapSubareaById")
    @Mapping(target = "notes", ignore = true)
    public abstract Order toEntity(OrderDto orderDto);

    @Mapping(source = "event.id", target = "eventId")
    @Mapping(source = "area.id", target = "areaId")
    @Mapping(source = "subarea.id", target = "subareaId")
    public abstract OrderDto toDto(Order order);

    @Named("mapEventById")
    public Event mapEventById(Long id) {
        return eventService.findById(id).orElse(null);
    }

    @Named("mapAreaById")
    public Area mapAreaById(Long id) {
        return areaService.findById(id).orElse(null);
    }

    @Named("mapSubareaById")
    public Subarea mapSubareaById(Long id) {
        return id != null ? subareaService.findById(id).orElse(null) : null;
    }
}