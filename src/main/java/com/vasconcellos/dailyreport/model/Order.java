package com.vasconcellos.dailyreport.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.vasconcellos.dailyreport.validation.NumberLength;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@Table(name = "orders")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @NumberLength(length = 10)
    private Long number;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "event_id")
    private Event event;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "area_id")
    private Area area;

    @ManyToOne
    @JoinColumn(name = "subarea_id")
    private Subarea subarea;

    @NotNull
    @Enumerated(EnumType.STRING)
    private OrderSpeciality speciality;

    @NotNull
    @Enumerated(EnumType.STRING)
    private OrderStatus status;

    private LocalDateTime endDate;

    @NotBlank
    private String description;

    @NotBlank
    private String technical;

    @JsonIgnore
    @OneToMany(mappedBy = "order", cascade = CascadeType.REMOVE, orphanRemoval = true)
    private List<Note> notes = new ArrayList<>();

}