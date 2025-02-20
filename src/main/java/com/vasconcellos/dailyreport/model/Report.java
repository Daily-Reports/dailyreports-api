package com.vasconcellos.dailyreport.model;

import com.vasconcellos.dailyreport.model.employee.Foreman;
import com.vasconcellos.dailyreport.model.employee.Supervisor;
import jakarta.persistence.*;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
@Entity
public class Report {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "foreman_id")
    private Foreman foreman;

    @ManyToOne
    @JoinColumn(name = "supervisor_id")
    private Supervisor supervisor;


    @OneToMany(mappedBy = "report", cascade = CascadeType.ALL)
    private List<Note> notes = new ArrayList<>();

}