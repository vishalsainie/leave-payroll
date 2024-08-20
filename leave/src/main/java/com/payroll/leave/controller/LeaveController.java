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

@RestController
@RequestMapping("/api")
@Tag(
        name = "Leave Controller",
        description = "Leave controller for Payroll"
)
public class LeaveController {

    @Value("${build.version}")
    private String buildVersion;

    @Autowired
    private ILeaveService iLeaveService;

//    public LeaveController(ILeaveService iLeaveService) {
//        this.iLeaveService = iLeaveService;
//    }

    @PostMapping("/create")
    public ResponseEntity<ResponseDto> createAccounts(@RequestBody LeaveDto leaveDto) {
        iLeaveService.createAccount(leaveDto);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(new ResponseDto("Successfully Created", HttpStatus.CREATED));
    }

    @GetMapping("/fetch")
    public ResponseEntity<LeaveDto> fetchAccountsDetails(@RequestParam Long employeeId) {
        LeaveDto leaveDto = iLeaveService.fetchDetails(employeeId);
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
    public ResponseEntity<ResponseDto> deleteAccounts(@RequestParam Long employeeId){
        boolean isDeleted = iLeaveService.deleteAccounts(employeeId);
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
}