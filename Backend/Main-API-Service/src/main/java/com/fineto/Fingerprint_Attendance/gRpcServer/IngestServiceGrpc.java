package com.fineto.Fingerprint_Attendance.gRpcServer;
import static io.grpc.MethodDescriptor.generateFullMethodName;

/**
 */
@io.grpc.stub.annotations.GrpcGenerated
public final class IngestServiceGrpc {

  private IngestServiceGrpc() {}

  public static final java.lang.String SERVICE_NAME = "IngestService";

  // Static method descriptors that strictly reflect the proto.
  private static volatile io.grpc.MethodDescriptor<Ingestor.ScanEvent,
      Ingestor.IngestResponse> getIngestEventMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "IngestEvent",
      requestType = Ingestor.ScanEvent.class,
      responseType = Ingestor.IngestResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<Ingestor.ScanEvent,
      Ingestor.IngestResponse> getIngestEventMethod() {
    io.grpc.MethodDescriptor<Ingestor.ScanEvent, Ingestor.IngestResponse> getIngestEventMethod;
    if ((getIngestEventMethod = IngestServiceGrpc.getIngestEventMethod) == null) {
      synchronized (IngestServiceGrpc.class) {
        if ((getIngestEventMethod = IngestServiceGrpc.getIngestEventMethod) == null) {
          IngestServiceGrpc.getIngestEventMethod = getIngestEventMethod =
              io.grpc.MethodDescriptor.<Ingestor.ScanEvent, Ingestor.IngestResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "IngestEvent"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  Ingestor.ScanEvent.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  Ingestor.IngestResponse.getDefaultInstance()))
              .setSchemaDescriptor(new IngestServiceMethodDescriptorSupplier("IngestEvent"))
              .build();
        }
      }
    }
    return getIngestEventMethod;
  }

  /**
   * Creates a new async stub that supports all call types for the service
   */
  public static IngestServiceStub newStub(io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<IngestServiceStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<IngestServiceStub>() {
        @java.lang.Override
        public IngestServiceStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new IngestServiceStub(channel, callOptions);
        }
      };
    return IngestServiceStub.newStub(factory, channel);
  }

  /**
   * Creates a new blocking-style stub that supports all types of calls on the service
   */
  public static IngestServiceBlockingV2Stub newBlockingV2Stub(
      io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<IngestServiceBlockingV2Stub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<IngestServiceBlockingV2Stub>() {
        @java.lang.Override
        public IngestServiceBlockingV2Stub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new IngestServiceBlockingV2Stub(channel, callOptions);
        }
      };
    return IngestServiceBlockingV2Stub.newStub(factory, channel);
  }

  /**
   * Creates a new blocking-style stub that supports unary and streaming output calls on the service
   */
  public static IngestServiceBlockingStub newBlockingStub(
      io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<IngestServiceBlockingStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<IngestServiceBlockingStub>() {
        @java.lang.Override
        public IngestServiceBlockingStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new IngestServiceBlockingStub(channel, callOptions);
        }
      };
    return IngestServiceBlockingStub.newStub(factory, channel);
  }

  /**
   * Creates a new ListenableFuture-style stub that supports unary calls on the service
   */
  public static IngestServiceFutureStub newFutureStub(
      io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<IngestServiceFutureStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<IngestServiceFutureStub>() {
        @java.lang.Override
        public IngestServiceFutureStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new IngestServiceFutureStub(channel, callOptions);
        }
      };
    return IngestServiceFutureStub.newStub(factory, channel);
  }

  /**
   */
  public interface AsyncService {

    /**
     */
    default void ingestEvent(Ingestor.ScanEvent request,
        io.grpc.stub.StreamObserver<Ingestor.IngestResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getIngestEventMethod(), responseObserver);
    }
  }

  /**
   * Base class for the server implementation of the service IngestService.
   */
  public static abstract class IngestServiceImplBase
      implements io.grpc.BindableService, AsyncService {

    @java.lang.Override public final io.grpc.ServerServiceDefinition bindService() {
      return IngestServiceGrpc.bindService(this);
    }
  }

  /**
   * A stub to allow clients to do asynchronous rpc calls to service IngestService.
   */
  public static final class IngestServiceStub
      extends io.grpc.stub.AbstractAsyncStub<IngestServiceStub> {
    private IngestServiceStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected IngestServiceStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new IngestServiceStub(channel, callOptions);
    }

    /**
     */
    public void ingestEvent(Ingestor.ScanEvent request,
        io.grpc.stub.StreamObserver<Ingestor.IngestResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getIngestEventMethod(), getCallOptions()), request, responseObserver);
    }
  }

  /**
   * A stub to allow clients to do synchronous rpc calls to service IngestService.
   */
  public static final class IngestServiceBlockingV2Stub
      extends io.grpc.stub.AbstractBlockingStub<IngestServiceBlockingV2Stub> {
    private IngestServiceBlockingV2Stub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected IngestServiceBlockingV2Stub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new IngestServiceBlockingV2Stub(channel, callOptions);
    }

    /**
     */
    public Ingestor.IngestResponse ingestEvent(Ingestor.ScanEvent request) throws io.grpc.StatusException {
      return io.grpc.stub.ClientCalls.blockingV2UnaryCall(
          getChannel(), getIngestEventMethod(), getCallOptions(), request);
    }
  }

  /**
   * A stub to allow clients to do limited synchronous rpc calls to service IngestService.
   */
  public static final class IngestServiceBlockingStub
      extends io.grpc.stub.AbstractBlockingStub<IngestServiceBlockingStub> {
    private IngestServiceBlockingStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected IngestServiceBlockingStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new IngestServiceBlockingStub(channel, callOptions);
    }

    /**
     */
    public Ingestor.IngestResponse ingestEvent(Ingestor.ScanEvent request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getIngestEventMethod(), getCallOptions(), request);
    }
  }

  /**
   * A stub to allow clients to do ListenableFuture-style rpc calls to service IngestService.
   */
  public static final class IngestServiceFutureStub
      extends io.grpc.stub.AbstractFutureStub<IngestServiceFutureStub> {
    private IngestServiceFutureStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected IngestServiceFutureStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new IngestServiceFutureStub(channel, callOptions);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<Ingestor.IngestResponse> ingestEvent(
        Ingestor.ScanEvent request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getIngestEventMethod(), getCallOptions()), request);
    }
  }

  private static final int METHODID_INGEST_EVENT = 0;

  private static final class MethodHandlers<Req, Resp> implements
      io.grpc.stub.ServerCalls.UnaryMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ServerStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ClientStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.BidiStreamingMethod<Req, Resp> {
    private final AsyncService serviceImpl;
    private final int methodId;

    MethodHandlers(AsyncService serviceImpl, int methodId) {
      this.serviceImpl = serviceImpl;
      this.methodId = methodId;
    }

    @java.lang.Override
    @java.lang.SuppressWarnings("unchecked")
    public void invoke(Req request, io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        case METHODID_INGEST_EVENT:
          serviceImpl.ingestEvent((Ingestor.ScanEvent) request,
              (io.grpc.stub.StreamObserver<Ingestor.IngestResponse>) responseObserver);
          break;
        default:
          throw new AssertionError();
      }
    }

    @java.lang.Override
    @java.lang.SuppressWarnings("unchecked")
    public io.grpc.stub.StreamObserver<Req> invoke(
        io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        default:
          throw new AssertionError();
      }
    }
  }

  public static final io.grpc.ServerServiceDefinition bindService(AsyncService service) {
    return io.grpc.ServerServiceDefinition.builder(getServiceDescriptor())
        .addMethod(
          getIngestEventMethod(),
          io.grpc.stub.ServerCalls.asyncUnaryCall(
            new MethodHandlers<
              Ingestor.ScanEvent,
              Ingestor.IngestResponse>(
                service, METHODID_INGEST_EVENT)))
        .build();
  }

  private static abstract class IngestServiceBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoFileDescriptorSupplier, io.grpc.protobuf.ProtoServiceDescriptorSupplier {
    IngestServiceBaseDescriptorSupplier() {}

    @java.lang.Override
    public com.google.protobuf.Descriptors.FileDescriptor getFileDescriptor() {
      return Ingestor.getDescriptor();
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.ServiceDescriptor getServiceDescriptor() {
      return getFileDescriptor().findServiceByName("IngestService");
    }
  }

  private static final class IngestServiceFileDescriptorSupplier
      extends IngestServiceBaseDescriptorSupplier {
    IngestServiceFileDescriptorSupplier() {}
  }

  private static final class IngestServiceMethodDescriptorSupplier
      extends IngestServiceBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoMethodDescriptorSupplier {
    private final java.lang.String methodName;

    IngestServiceMethodDescriptorSupplier(java.lang.String methodName) {
      this.methodName = methodName;
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.MethodDescriptor getMethodDescriptor() {
      return getServiceDescriptor().findMethodByName(methodName);
    }
  }

  private static volatile io.grpc.ServiceDescriptor serviceDescriptor;

  public static io.grpc.ServiceDescriptor getServiceDescriptor() {
    io.grpc.ServiceDescriptor result = serviceDescriptor;
    if (result == null) {
      synchronized (IngestServiceGrpc.class) {
        result = serviceDescriptor;
        if (result == null) {
          serviceDescriptor = result = io.grpc.ServiceDescriptor.newBuilder(SERVICE_NAME)
              .setSchemaDescriptor(new IngestServiceFileDescriptorSupplier())
              .addMethod(getIngestEventMethod())
              .build();
        }
      }
    }
    return result;
  }
}
