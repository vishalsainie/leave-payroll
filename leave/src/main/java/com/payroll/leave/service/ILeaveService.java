package com.payroll.leave.service;

import com.payroll.leave.dto.LeaveDto;

import java.util.*;

public interface ILeaveService {
    void createAccount(LeaveDto leaveDto);

    LeaveDto fetchDetails(Long employeeId, String date);

    boolean updateDetails(LeaveDto leaveDto);

    boolean deleteAccounts(Long employeeId, String date);

    List<LeaveDto> fetchAllDetails(Long employeeId);
}
