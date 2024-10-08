package com.payroll.leave.repository;

import com.payroll.leave.entity.Leave;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.*;

@Repository
public interface LeaveRepository extends JpaRepository<Leave, Long> {
    Optional<Leave> findByEmployeeIdAndDate(Long employeeId, String date);
    List<Leave> findByEmployeeId(Long employeeId);
}