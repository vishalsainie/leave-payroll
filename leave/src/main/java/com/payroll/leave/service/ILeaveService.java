package com.payroll.leave.service;

import com.payroll.leave.dto.LeaveDto;

import java.util.List;

public interface ILeaveService {
    void createAccount(LeaveDto leaveDto);

    LeaveDto fetchDetails(Long employeeId);

    boolean updateDetails(LeaveDto leaveDto);

    boolean deleteAccounts(Long employeeId);
}
