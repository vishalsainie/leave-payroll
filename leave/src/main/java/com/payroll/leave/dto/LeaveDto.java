package com.payroll.leave.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class LeaveDto  {

    private Long employeeId;
    private String category;
    private String reason;
    private String date;
}