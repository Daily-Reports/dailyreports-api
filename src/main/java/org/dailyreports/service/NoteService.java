package org.dailyreports.service;

import lombok.RequiredArgsConstructor;
import org.dailyreports.dto.employee.EmployeeDto;
import org.dailyreports.dto.note.NoteDto;
import org.dailyreports.dto.note.NoteUpdateDto;
import org.dailyreports.exception.ImageException;
import org.dailyreports.exception.InvalidDateException;
import org.dailyreports.exception.ResourceNotFoundException;
import org.dailyreports.mapper.NoteMapper;
import org.dailyreports.model.Employee;
import org.dailyreports.model.Note;
import org.dailyreports.model.Order;
import org.dailyreports.model.Report;
import org.dailyreports.repository.NoteRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RequiredArgsConstructor
@Service
@Transactional
public class NoteService {

    private final NoteRepository noteRepository;
    private final NoteMapper noteMapper;

    private final ReportService reportService;
    private final EmployeeService employeeService;
    private final OrderService orderService;

    private final S3Service s3Service;

    public NoteDto save(NoteDto data) {
        Report report = reportService.findByIdAsEntity(data.getReportId());
        Employee employee = employeeService.findByIdAsEntity(data.getEmployeeId());
        Order order = orderService.findByIdAsEntity(data.getOrderId());

        Note note = noteMapper.toEntity(data, report, employee, order);

        for(Note existingNote : noteRepository.findByEmployeeId(data.getEmployeeId()))
            if (existingNote.isOverlapping(note))
                throw new InvalidDateException("The exit time cannot overlap with an existing entry time.");

        if(note.getExitTime().isBefore(note.getEntryTime()))
            throw new InvalidDateException("Exit date cannot be before entry date.");

        return noteMapper.toDto(noteRepository.save(note));
    }

    public NoteDto update(long id, NoteUpdateDto data) {
        Note note = findByIdAsEntity(id);

        if(data.getComment() != null)
            note.setComment(data.getComment());

        return noteMapper.toDto(noteRepository.save(note));
    }

    public NoteDto addImage(long id, MultipartFile image) {
        Note note = findByIdAsEntity(id);

        try {
            note.getImagesUrl().add(s3Service.uploadFile(image));
        } catch (IOException e) {
            throw new ImageException("Image cannot be added.");
        }

        return noteMapper.toDto(noteRepository.save(note));
    }

    public NoteDto removeImage(long id, String imageUrl) {
        Note note = findByIdAsEntity(id);

        if (!note.getImagesUrl().remove(imageUrl))
            throw new ResourceNotFoundException("Image cannot be found");

        String imageName = imageUrl.substring(imageUrl.lastIndexOf("/") + 1);
        s3Service.deleteFile(imageName);

        return noteMapper.toDto(noteRepository.save(note));
    }

    public NoteDto findById(Long id) {
        return noteMapper.toDto(findByIdAsEntity(id));
    }

    private Note findByIdAsEntity(Long id) {
        return noteRepository.findById(id).orElseThrow(() ->
                new ResourceNotFoundException("Note cannot be found."));
    }

    public List<NoteDto> findAll() {
        return noteRepository.findAll().stream().map(noteMapper::toDto).toList();
    }

    public List<NoteDto> findByEmployee(EmployeeDto employeeDto) {
        return noteRepository.findByEmployeeId(employeeDto.getId()).stream().map(noteMapper::toDto).toList();
    }

    public void deleteById(Long id) {
        noteRepository.deleteById(id);
    }
}