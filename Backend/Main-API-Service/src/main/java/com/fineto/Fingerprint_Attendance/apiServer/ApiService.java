package com.fineto.Fingerprint_Attendance.apiServer;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api")
public class ApiService {
    @PostMapping("/data")
    public String postMethodName(@RequestBody String entity) {
         System.out.println("Received POST request with body: " + entity);
            return "Data received successfully!";
    }
    
}
