package com.vasconcellos.dailyreport.service;

import com.vasconcellos.dailyreport.dto.NoteDto;
import com.vasconcellos.dailyreport.mapper.NoteMapper;
import com.vasconcellos.dailyreport.model.Note;
import com.vasconcellos.dailyreport.repository.NoteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
@Transactional
public class NoteService {

    private final NoteRepository noteRepository;
    private final NoteMapper noteMapper;

    public Note save(NoteDto data) {
        Note toSave = noteMapper.toEntity(data);

        return noteRepository.save(toSave);
    }

    public Optional<Note> findById(Long id) {
        return noteRepository.findById(id);
    }

    public List<Note> findAll() {
        return noteRepository.findAll();
    }

    public void deleteById(Long id) {
        noteRepository.deleteById(id);
    }
}