package com.payroll.leave.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LeaveMessageDto {

    private Long employeeId;
    private String firstName;
    private String lastName;
    private String category;
    private String reason;
    private String date;
}
