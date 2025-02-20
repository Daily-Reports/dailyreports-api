package com.vasconcellos.dailyreport.model;

import com.vasconcellos.dailyreport.model.employee.Employee;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Entity
public class Report {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "employee_id")
    private Employee employee;

    @ManyToOne
    @JoinColumn(name = "order_id")
    private Order order;

    @ManyToOne
    @JoinColumn(name = "daily_report_id")
    private DailyReport dailyReport;

    private LocalDateTime entryTime;
    private LocalDateTime exitTime;

}