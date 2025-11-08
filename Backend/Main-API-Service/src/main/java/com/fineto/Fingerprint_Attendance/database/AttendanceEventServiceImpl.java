package com.fineto.Fingerprint_Attendance.database;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.fineto.Fingerprint_Attendance.AttendanceEventEntity;

@Service
public class AttendanceEventServiceImpl implements AttendanceEventService {
    private final AttendanceEventRepository repository;

    public AttendanceEventServiceImpl(AttendanceEventRepository repository) {
        this.repository = repository;
    }

    @Override
    public Page<AttendanceEventEntity> getAllRecords(Pageable pageable) {
        return repository.findAll(pageable);
    }

    @Override
    public Page<AttendanceEventEntity> findRecordsByEmployeeId(String employeeId, Pageable pageable) {
       return repository.findByEmployeeId(employeeId, pageable);
    }



}
