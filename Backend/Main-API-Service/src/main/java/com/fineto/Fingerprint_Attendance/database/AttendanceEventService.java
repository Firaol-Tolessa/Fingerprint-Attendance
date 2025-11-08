package com.fineto.Fingerprint_Attendance.database;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.fineto.Fingerprint_Attendance.AttendanceEventEntity;

public interface AttendanceEventService {
    Page<AttendanceEventEntity> getAllRecords(Pageable pageable);
    Page<AttendanceEventEntity> findRecordsByEmployeeId(String employeeId, Pageable pageable);
}
