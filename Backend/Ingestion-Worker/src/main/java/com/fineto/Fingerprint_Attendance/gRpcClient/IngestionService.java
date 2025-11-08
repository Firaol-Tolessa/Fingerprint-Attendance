package com.fineto.Fingerprint_Attendance.gRpcClient;

import java.time.LocalDate;

import org.springframework.stereotype.Service;

import com.fineto.Fingerprint_Attendance.AttendanceEventEntity;
import com.fineto.Fingerprint_Attendance.HashUtil;
import com.fineto.Fingerprint_Attendance.gRpcClient.Ingestor.IngestResponse;
import com.fineto.Fingerprint_Attendance.gRpcClient.Ingestor.ScanEvent;

import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import jakarta.annotation.PostConstruct;

@Service
public class IngestionService {
    private IngestServiceGrpc.IngestServiceBlockingStub blockingStub;

    @PostConstruct
    public void init() {
        ManagedChannel channel = ManagedChannelBuilder.forAddress("localhost", 8080)
                .usePlaintext()
                .build();
        blockingStub = IngestServiceGrpc.newBlockingStub(channel);
    }

    public void forwardScan(AttendanceEventEntity event) {
        String eventHash = HashUtil.generateHash(
            event.getEmployeeId(), 
            String.valueOf(event.getTimestamp()),
            event.getDeviceId());

        ScanEvent grpcRequest = ScanEvent.newBuilder()
                .setEmployeeId(event.getEmployeeId())
                .setTimestamp(String.valueOf(event.getTimestamp()))
                .setDeviceId(event.getDeviceId())
                .setEventHash(eventHash)
                .build();
        try {
            System.out.println("Worker: Forwarding scan to main service: " + eventHash);
            IngestResponse grpcResponse = blockingStub.ingestEvent(grpcRequest);
            System.out.println(grpcResponse);
        } catch (Exception e) {
            System.err.println("Worker: Failed to forward scan! " + e);
        }
    }
}
