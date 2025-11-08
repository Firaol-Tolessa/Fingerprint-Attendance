package com.fineto.Fingerprint_Attendance.gRpcServer;

import io.grpc.stub.StreamObserver;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.grpc.server.service.GrpcService;
import org.springframework.stereotype.Service;

import com.fineto.Fingerprint_Attendance.AttendanceEventEntity;
import com.fineto.Fingerprint_Attendance.database.AttendanceEventRepository;
import com.fineto.Fingerprint_Attendance.gRpcServer.Ingestor.IngestResponse;

import java.util.Optional;

@GrpcService
public class IngestorGrpcService extends IngestServiceGrpc.IngestServiceImplBase {

    final private AttendanceEventRepository repository;

    @Autowired
    public IngestorGrpcService(AttendanceEventRepository repository) {
        this.repository = repository;
    }

    @Override
    public void ingestEvent(Ingestor.ScanEvent request, StreamObserver<Ingestor.IngestResponse> responseObserver) {
        Optional<AttendanceEventEntity> existing = repository.findByEventHash(request.getEventHash());
        try {
            if (existing.isPresent()) {
                System.out.println("Duplicate event detected: ");
                Ingestor.IngestResponse response = Ingestor.IngestResponse.newBuilder()
                        .setSuccess("DUPLICATE")
                        .setMessage("Cannot input value, Employee is duplicate")
                        .build();
                responseObserver.onNext(response);
                responseObserver.onCompleted();
                return;
            }

            LocalDateTime localTimestamp = LocalDateTime.parse(request.getTimestamp());
            // long timestampMillis = Long.parseLong(request.getTimestamp());
            // Instant instant = Instant.ofEpochMilli(timestampMillis);
            // LocalDateTime localTimestamp = LocalDateTime.ofInstant(instant, ZoneId.systemDefault());

            AttendanceEventEntity newEvent = new AttendanceEventEntity();
            newEvent.setEmployeeId(request.getEmployeeId());
            newEvent.setTimestamp(localTimestamp);
            newEvent.setDeviceId(request.getDeviceId());
            newEvent.setEventHash(request.getEventHash());

            // Save it to the database
            AttendanceEventEntity savedEvent = repository.save(newEvent);
            System.out.println("Processed new event: " + savedEvent.getEventHash());

            IngestResponse response = IngestResponse.newBuilder()
                    .setSuccess("PROCESSED")
                    .setMessage("Employee recorded successfully")
                    .build();

            responseObserver.onNext(response);
            responseObserver.onCompleted();
        } catch (NumberFormatException e) {
            // 6. Gracefully handle bad timestamps
            System.err.println("Failed to parse timestamp: " + request.getTimestamp());
            responseObserver.onError(e);
        } catch (Exception e) {
            responseObserver.onError(e);
        }

        // super.ingestEvent(request, responseObserver);
    }

}
