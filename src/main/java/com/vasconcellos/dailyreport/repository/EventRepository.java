package com.vasconcellos.dailyreport.repository;

import com.vasconcellos.dailyreport.model.Event;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EventRepository extends JpaRepository<Event, Long> {

}
