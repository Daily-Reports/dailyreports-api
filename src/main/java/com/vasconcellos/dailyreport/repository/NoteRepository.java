package com.vasconcellos.dailyreport.repository;

import com.vasconcellos.dailyreport.model.Employee;
import com.vasconcellos.dailyreport.model.Note;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface NoteRepository extends JpaRepository<Note, Long> {

    List<Note> findByEmployee(Employee employee);

}