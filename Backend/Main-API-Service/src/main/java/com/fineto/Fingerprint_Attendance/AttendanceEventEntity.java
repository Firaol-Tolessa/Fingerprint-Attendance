package com.fineto.Fingerprint_Attendance;

import java.time.LocalDateTime;

import jakarta.persistence.*;

@Entity
@Table(name = "employee_attendance")
public class AttendanceEventEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String employeeId;
    @Column(nullable = false)
    private String deviceId;
    @Column(nullable = false)
    private LocalDateTime timestamp;
    @Column(unique = true, nullable = false)
    private String eventHash;

    public AttendanceEventEntity() {
    }

    public AttendanceEventEntity(String employeeId, String deviceId, LocalDateTime timestamp, String eventHash) {
        this.employeeId = employeeId;
        this.deviceId = deviceId;
        this.timestamp = timestamp;
        this.eventHash = eventHash;
    }

    public String getEmployeeId() {
        return employeeId;
    }

    public String getDeviceId() {
        return deviceId;
    }

    public void setEmployeeId(String EmployeeId) {
        this.employeeId = EmployeeId;
    }

    public void setDeviceId(String deviceId) {
        this.deviceId = deviceId;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }

    public void setEventHash(String eventHash) {
        this.eventHash = eventHash;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public String getEventHash() {
        return eventHash;
    }

}
