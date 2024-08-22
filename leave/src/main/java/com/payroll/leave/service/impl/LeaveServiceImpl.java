package com.payroll.leave.service.impl;


import com.payroll.leave.dto.EmployeesDto;
import com.payroll.leave.dto.LeaveDto;
import com.payroll.leave.dto.LeaveMessageDto;
import com.payroll.leave.entity.Leave;
import com.payroll.leave.exceptions.LeaveAlreadyExistsException;
import com.payroll.leave.exceptions.ResourceNotFoundException;
import com.payroll.leave.mapper.LeaveMapper;
import com.payroll.leave.repository.LeaveRepository;
import com.payroll.leave.service.ILeaveService;
import lombok.AllArgsConstructor;
import org.springframework.cloud.stream.function.StreamBridge;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class LeaveServiceImpl implements ILeaveService {

    private LeaveRepository leaveRepository;
    private StreamBridge streamBridge;

    @Override
    public void createAccount(LeaveDto leaveDto) {

        EmployeesDto employeesDto = new EmployeesDto();

        Long employeeId = leaveDto.getEmployeeId();
        String date = leaveDto.getDate();
        Optional<Leave> foundLeave = leaveRepository.findByEmployeeIdAndDate(employeeId, date);

        if (foundLeave.isPresent()) {
            throw new LeaveAlreadyExistsException("Leave already exists for given employee ID " + employeeId);
        }

        Leave leave = LeaveMapper.mapToLeave(leaveDto, new Leave());
        leaveRepository.save(leave);

        sendCommunication(leave, employeesDto);
    }

    private void sendCommunication(Leave leave, EmployeesDto employeesDto){
        LeaveMessageDto leaveMessageDto = new LeaveMessageDto(
                leave.getEmployeeId(),
                employeesDto.getFirstName(),
                employeesDto.getLastName(),
                leave.getCategory(),
                leave.getReason(),
                leave.getDate()
        );

        boolean isSend = streamBridge.send("sendCommunication-out-0", leaveMessageDto);

        System.out.println("Is communication send >" + isSend);
    }

    @Override
    public LeaveDto fetchDetails(Long employeeId, String date) {
        Leave leave = leaveRepository.findByEmployeeIdAndDate(employeeId, date).orElseThrow(
                () -> new ResourceNotFoundException("Leave", "Employee ID", employeeId)
        );

        LeaveDto leaveDto = LeaveMapper.mapToLeaveDto(leave, new LeaveDto());
        return leaveDto;
    }

    @Override
    public boolean updateDetails(LeaveDto leaveDto) {

        boolean isUpdated = false;

        Long employeeId = leaveDto.getEmployeeId();
        String date = leaveDto.getDate();

        Leave leave = leaveRepository.findByEmployeeIdAndDate(employeeId, date).orElseThrow(
                () -> new ResourceNotFoundException("Leave", "employee ID", leaveDto.getEmployeeId())
        );

        if (leave != null) {
            LeaveMapper.mapToLeave(leaveDto, leave);

            leaveRepository.save(leave);

            isUpdated = true;
        }

        return isUpdated;
    }

    @Override
    public boolean deleteAccounts(Long employeeId, String date) {

        boolean isDeleted = false;

        Leave leave = leaveRepository.findByEmployeeIdAndDate(employeeId, date).orElseThrow(
                () -> new ResourceNotFoundException("Leave", "employee ID", employeeId)
        );

        if (leave != null) {
            leaveRepository.delete(leave);
            isDeleted = true;
        }
        return isDeleted;
    }

    @Override
    public List<LeaveDto> fetchAllDetails(Long employeeId) {
        List<Leave> leaveList = leaveRepository.findByEmployeeId(employeeId);

        if(leaveList.isEmpty()){
            throw new ResourceNotFoundException("Leave", "Employee ID", employeeId);
        }

        List<LeaveDto> leaveDtoList = new ArrayList<>();
        for (Leave leave : leaveList) {
            LeaveDto leaveDto = LeaveMapper.mapToLeaveDto(leave, new LeaveDto());
            leaveDtoList.add(leaveDto);
        }
        return leaveDtoList;
    }

}