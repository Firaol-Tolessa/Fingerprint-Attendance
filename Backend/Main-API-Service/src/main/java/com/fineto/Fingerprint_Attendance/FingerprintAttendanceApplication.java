package com.fineto.Fingerprint_Attendance;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class FingerprintAttendanceApplication {

	public static void main(String[] args) {
		SpringApplication.run(FingerprintAttendanceApplication.class, args);
		System.out.println("Api server started on http://localhost:8080");
	}
}
