package com.vasconcellos.dailyreport.service;

import com.vasconcellos.dailyreport.dto.NoteDto;
import com.vasconcellos.dailyreport.exception.InvalidDateException;
import com.vasconcellos.dailyreport.exception.ResourceNotFoundException;
import com.vasconcellos.dailyreport.mapper.NoteMapper;
import com.vasconcellos.dailyreport.model.Employee;
import com.vasconcellos.dailyreport.model.Note;
import com.vasconcellos.dailyreport.repository.NoteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RequiredArgsConstructor
@Service
@Transactional
public class NoteService {

    private final NoteRepository noteRepository;
    private final NoteMapper noteMapper;

    public Note save(NoteDto data) {
        Note toSave = noteMapper.toEntity(data);

        for(Note existingNote : findByEmployee(toSave.getEmployee()))
            if (existingNote.isOverlapping(toSave))
                throw new InvalidDateException("The exit time cannot overlap with an existing entry time.");

        if(data.getExitTime().isBefore(data.getEntryTime()))
            throw new InvalidDateException("Exit date cannot be before entry date.");

        return noteRepository.save(toSave);
    }

    public Note findById(Long id) {
        return noteRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Note cannot be found."));
    }

    public List<Note> findAll() {
        return noteRepository.findAll();
    }

    public List<Note> findByEmployee(Employee employee) {
        return noteRepository.findByEmployee(employee);
    }

    public void deleteById(Long id) {
        noteRepository.deleteById(id);
    }
}