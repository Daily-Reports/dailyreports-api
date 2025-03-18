package org.dailyreports.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

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

    @ElementCollection
    @CollectionTable(name="images", joinColumns = @JoinColumn(name="note_id"))
    @Column(name="image_url")
    private List<String> imagesUrl = new ArrayList<>();

    public boolean isOverlapping(Note newNote) {
        return newNote.getEntryTime().isBefore(exitTime) &&
                newNote.getExitTime().isAfter(entryTime);
    }
}