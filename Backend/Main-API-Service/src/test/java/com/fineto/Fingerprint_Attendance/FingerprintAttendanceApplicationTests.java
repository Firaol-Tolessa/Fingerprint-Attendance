package com.fineto.Fingerprint_Attendance;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Optional;

import com.fineto.Fingerprint_Attendance.database.AttendanceEventRepository;
import com.fineto.Fingerprint_Attendance.gRpcServer.Ingestor.IngestResponse;
import com.fineto.Fingerprint_Attendance.gRpcServer.Ingestor.ScanEvent;
import com.fineto.Fingerprint_Attendance.gRpcServer.IngestorGrpcService;

import io.grpc.stub.StreamObserver;

@SpringBootTest
class FingerprintAttendanceApplicationTests {
	@MockBean
	private AttendanceEventRepository repository;
	@Mock
	private StreamObserver<IngestResponse> responseObserver;
	@Captor
	private ArgumentCaptor<IngestResponse> responseCaptor;
	@Autowired
	private IngestorGrpcService ingestorService;

	private ScanEvent newEventRequest;
	private AttendanceEventEntity existingEventEntity;

	@BeforeEach
	void setUp() {
		// Long time = System.currentTimeMillis();
		String validTimestampString = LocalDateTime.now().toString();
		newEventRequest = ScanEvent.newBuilder()
				.setEmployeeId("e-123")
				.setDeviceId("d-001")
				.setEventHash("hash-123")
				.setTimestamp(validTimestampString) // Use the same format as the service
				.build();

		// Creating a duplicate data
		existingEventEntity = new AttendanceEventEntity();
		existingEventEntity.setEmployeeId("e-123");
		existingEventEntity.setDeviceId("d-001");
		existingEventEntity.setEventHash("hash-123");

	}

	@Test
	void testIngestEvent_whenEventIsDuplicate_itIsNotSaved() {
		when(repository.findByEventHash("hash-123")).thenReturn(Optional.of(existingEventEntity));
		ingestorService.ingestEvent(newEventRequest, responseObserver);

		verify(repository, times(0)).save(any(AttendanceEventEntity.class));
		verify(responseObserver).onNext(responseCaptor.capture());
		IngestResponse response = responseCaptor.getValue();

		assertEquals("DUPLICATE", response.getSuccess());
		assertEquals("Cannot input value, Employee is duplicate", response.getMessage());

		verify(responseObserver).onCompleted();

	}

	@Test
	void testIngestEvent_whenEventIsNew_itIsSaved() {
		when(repository.findByEventHash("hash-123")).thenReturn(Optional.empty());
		when(repository.save(any(AttendanceEventEntity.class))).thenReturn(existingEventEntity);
		ingestorService.ingestEvent(newEventRequest, responseObserver);

		verify(repository, times(1)).save(any(AttendanceEventEntity.class));
		verify(responseObserver).onNext(responseCaptor.capture());
		IngestResponse response = responseCaptor.getValue();

		assertEquals("PROCESSED", response.getSuccess());
		assertEquals("Employee recorded successfully", response.getMessage());

		verify(responseObserver).onCompleted();

	}

}
