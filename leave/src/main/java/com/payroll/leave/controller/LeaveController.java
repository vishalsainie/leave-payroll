package com.payroll.leave.controller;


import com.payroll.leave.dto.LeaveDto;
import com.payroll.leave.dto.ResponseDto;
import com.payroll.leave.service.ILeaveService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.*;

@RestController
@RequestMapping("/api")
@Tag(
        name = "Leave Controller",
        description = "Leave controller for Payroll"
)
@CrossOrigin(origins="http://localhost:4200")
public class LeaveController {

    @Value("${build.version}")
    private String buildVersion;

    @Autowired
    private ILeaveService iLeaveService;


    @PostMapping("/create")
    public ResponseEntity<ResponseDto> createAccounts(@RequestBody LeaveDto leaveDto) {
        iLeaveService.createAccount(leaveDto);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(new ResponseDto("Successfully Created", HttpStatus.CREATED));
    }

    @GetMapping("/fetch")
    public ResponseEntity<LeaveDto> fetchAccountsDetails(@RequestParam Long employeeId, String date) {
        LeaveDto leaveDto = iLeaveService.fetchDetails(employeeId, date);
        return ResponseEntity.status(HttpStatus.OK)
                .body(leaveDto);
    }


    @PutMapping("/update")
    public ResponseEntity<ResponseDto> updateDetails(@RequestBody LeaveDto leaveDto) {
        boolean isUpdated = iLeaveService.updateDetails(leaveDto);
        if (isUpdated) {
            return ResponseEntity.status(HttpStatus.OK)
                    .body(new ResponseDto("Updated successfully", HttpStatus.ACCEPTED));
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(new ResponseDto("Unable to update", HttpStatus.BAD_REQUEST));
        }
    }

    @DeleteMapping("/delete")
    public ResponseEntity<ResponseDto> deleteAccounts(@RequestParam Long employeeId, String date){
        boolean isDeleted = iLeaveService.deleteAccounts(employeeId, date);
        if (isDeleted) {
            return ResponseEntity.status(HttpStatus.OK)
                    .body(new ResponseDto("Deleted successfully", HttpStatus.OK));
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(new ResponseDto("Unable to delete", HttpStatus.BAD_REQUEST));
        }
    }

    @GetMapping("/build")
    public ResponseEntity<String> getBuildInfo(){
        return ResponseEntity.status(HttpStatus.OK)
                .body(buildVersion);
    }

    @GetMapping("/fetch-all-leave")
    public ResponseEntity<List<LeaveDto>> fetchAllLeave(@RequestParam Long employeeId) {
        List<LeaveDto> leaveDtoList = iLeaveService.fetchAllDetails(employeeId);
        return ResponseEntity.status(HttpStatus.OK)
                .body(leaveDtoList);
    }
}