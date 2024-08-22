package com.payroll.leave.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
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
    @Column(name = "LEAVE_ID")
    private Long leaveId;

    @NotNull(message = "EmployeeId is must")
    @Column(name = "EMPLOYEE_ID")
    private Long employeeId;

    @Size(min = 3, max = 100, message = "size should be between 3 to 100")
    @Column(name = "CATEGORY")
    private String category;

    @Size(min = 2, max = 100, message = "size should be between 2 to 100")
    @Column(name = "REASON")
    private String reason;

    @Column(name = "DATE")
    private String date;

//    private Boolean communicationSw;
}
