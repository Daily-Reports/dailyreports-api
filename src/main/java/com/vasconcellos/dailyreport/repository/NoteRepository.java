package com.vasconcellos.dailyreport.repository;

import com.vasconcellos.dailyreport.model.Note;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NoteRepository extends JpaRepository<Note, Long> {

}