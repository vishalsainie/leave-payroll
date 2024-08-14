package com.payroll.leave.mapper;

import com.payroll.leave.dto.LeaveDto;
import com.payroll.leave.entity.Leave;

public class LeaveMapper {

    public static Leave mapToLeave(LeaveDto leaveDto, Leave leave){

        leave.setEmployeeId(leaveDto.getEmployeeId());
        leave.setCategory(leaveDto.getCategory());
        leave.setReason(leaveDto.getReason());
        leave.setDate(leaveDto.getDate());
        return leave;
    }

    public static LeaveDto mapToLeaveDto(Leave leave, LeaveDto leaveDto){

        leaveDto.setEmployeeId(leave.getEmployeeId());
        leaveDto.setCategory(leave.getCategory());
        leaveDto.setReason(leave.getReason());
        leaveDto.setDate(leave.getDate());
        return leaveDto;
    }



}


