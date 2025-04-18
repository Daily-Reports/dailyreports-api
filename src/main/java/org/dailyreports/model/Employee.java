package org.dailyreports.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
@Entity
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    private String name;

    @NotNull
    @Enumerated(EnumType.STRING)
    private EmployeeType type;

    @JsonIgnore
    @OneToMany(mappedBy = "employee", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Note> notes = new ArrayList<>();

    @JsonIgnore
    @OneToMany(mappedBy = "foreman", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Report> reportsAsForeman = new ArrayList<>();

    @JsonIgnore
    @OneToMany(mappedBy = "supervisor", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Report> reportsAsSupervisor = new ArrayList<>();

}