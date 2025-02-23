package com.vasconcellos.dailyreport.model;

import com.vasconcellos.dailyreport.model.employee.Employee;
import jakarta.persistence.*;
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
    private Employee employee;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "order_id", nullable = false)
    private Order order;

    @ManyToOne
    @JoinColumn(name = "report_id", nullable = false)
    private Report report;

    private LocalDateTime entryTime;
    private LocalDateTime exitTime;

}