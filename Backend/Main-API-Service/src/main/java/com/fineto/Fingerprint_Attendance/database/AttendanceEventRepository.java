package com.fineto.Fingerprint_Attendance.database;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.fineto.Fingerprint_Attendance.AttendanceEventEntity;
import java.util.Optional;

@Repository
public interface AttendanceEventRepository extends JpaRepository<AttendanceEventEntity, Long> {
    Optional<AttendanceEventEntity> findByEventHash(String eventHash);
    Page<AttendanceEventEntity> findByEmployeeId(String employeeId, Pageable pageable);
}