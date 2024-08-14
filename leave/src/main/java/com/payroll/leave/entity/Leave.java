package com.payroll.leave.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "leave")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

public class Leave extends BaseEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long leaveId;

    private Long employeeId;
    private String category;
    private String reason;
    private String date;
}
