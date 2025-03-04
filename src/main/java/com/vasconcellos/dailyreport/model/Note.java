package com.vasconcellos.dailyreport.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Entity
public class Note {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "employee_id", nullable = false)
    @NotNull
    private Employee employee;

    @ManyToOne
    @JoinColumn(name = "order_id", nullable = false)
    @NotNull
    private Order order;

    @ManyToOne
    @JoinColumn(name = "report_id", nullable = false)
    @NotNull
    @JsonIgnore
    private Report report;

    @NotNull
    private LocalDateTime entryTime;
    @NotNull
    private LocalDateTime exitTime;

    private String comment;

    public boolean isOverlapping(Note newNote) {
        return newNote.getEntryTime().isBefore(exitTime) &&
                newNote.getExitTime().isAfter(entryTime);
    }
}