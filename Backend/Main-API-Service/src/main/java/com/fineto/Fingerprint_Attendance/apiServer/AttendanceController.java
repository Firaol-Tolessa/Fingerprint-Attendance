package com.fineto.Fingerprint_Attendance.apiServer;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import com.fineto.Fingerprint_Attendance.AttendanceEventEntity;
import com.fineto.Fingerprint_Attendance.HashUtil;
import com.fineto.Fingerprint_Attendance.database.AttendanceEventService;

@RestController
@RequestMapping("/api/attendance")
public class AttendanceController {
    private final AttendanceEventService repoService;

    @Autowired
    public AttendanceController(AttendanceEventService repoService) {
        this.repoService = repoService;
    }

    @GetMapping
    public Page<AttendanceEventEntity> getAllEvents(Pageable pageable,
            @RequestParam(required = false) String employeeId) {
        if (employeeId != null && !employeeId.isEmpty()) {
            return repoService.findRecordsByEmployeeId(employeeId, pageable);
        } else {
            return repoService.getAllRecords(pageable);
        }

    }
}
