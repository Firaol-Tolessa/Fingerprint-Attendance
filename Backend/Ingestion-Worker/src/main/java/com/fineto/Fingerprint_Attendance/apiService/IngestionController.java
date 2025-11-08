package com.fineto.Fingerprint_Attendance.apiService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fineto.Fingerprint_Attendance.AttendanceEventEntity;
import com.fineto.Fingerprint_Attendance.gRpcClient.IngestionService;

@RestController
@RequestMapping("/api") // Listens for /api
public class IngestionController {

    private final IngestionService ingestionService;

    @Autowired
    public IngestionController(IngestionService ingestionService) {
        this.ingestionService = ingestionService;
    }


    @PostMapping("/scan")
    public void receiveScan(@RequestBody AttendanceEventEntity scan) {
        
        System.out.println("Worker: Received scan from device: " + scan.getEventHash());
        ingestionService.forwardScan(scan);
       
    }
}