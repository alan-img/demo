package com.dahuatech.grpc.generate;

import static io.grpc.stub.ClientCalls.asyncUnaryCall;
import static io.grpc.stub.ClientCalls.asyncServerStreamingCall;
import static io.grpc.stub.ClientCalls.asyncClientStreamingCall;
import static io.grpc.stub.ClientCalls.asyncBidiStreamingCall;
import static io.grpc.stub.ClientCalls.blockingUnaryCall;
import static io.grpc.stub.ClientCalls.blockingServerStreamingCall;
import static io.grpc.stub.ClientCalls.futureUnaryCall;
import static io.grpc.MethodDescriptor.generateFullMethodName;
import static io.grpc.stub.ServerCalls.asyncUnaryCall;
import static io.grpc.stub.ServerCalls.asyncServerStreamingCall;
import static io.grpc.stub.ServerCalls.asyncClientStreamingCall;
import static io.grpc.stub.ServerCalls.asyncBidiStreamingCall;
import static io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall;
import static io.grpc.stub.ServerCalls.asyncUnimplementedStreamingCall;

/**
 */
@javax.annotation.Generated(
    value = "by gRPC proto compiler (version 1.2.0)",
    comments = "Source: interface.proto")
public final class InterfaceServiceGrpc {

  private InterfaceServiceGrpc() {}

  public static final String SERVICE_NAME = "com.dahuatech.grpc.InterfaceService";

  // Static method descriptors that strictly reflect the proto.
  @io.grpc.ExperimentalApi("https://github.com/grpc/grpc-java/issues/1901")
  public static final io.grpc.MethodDescriptor<com.dahuatech.grpc.generate.Request,
      com.dahuatech.grpc.generate.Response> METHOD_GET =
      io.grpc.MethodDescriptor.create(
          io.grpc.MethodDescriptor.MethodType.UNARY,
          generateFullMethodName(
              "com.dahuatech.grpc.InterfaceService", "get"),
          io.grpc.protobuf.ProtoUtils.marshaller(com.dahuatech.grpc.generate.Request.getDefaultInstance()),
          io.grpc.protobuf.ProtoUtils.marshaller(com.dahuatech.grpc.generate.Response.getDefaultInstance()));

  /**
   * Creates a new async stub that supports all call types for the service
   */
  public static InterfaceServiceStub newStub(io.grpc.Channel channel) {
    return new InterfaceServiceStub(channel);
  }

  /**
   * Creates a new blocking-style stub that supports unary and streaming output calls on the service
   */
  public static InterfaceServiceBlockingStub newBlockingStub(
      io.grpc.Channel channel) {
    return new InterfaceServiceBlockingStub(channel);
  }

  /**
   * Creates a new ListenableFuture-style stub that supports unary and streaming output calls on the service
   */
  public static InterfaceServiceFutureStub newFutureStub(
      io.grpc.Channel channel) {
    return new InterfaceServiceFutureStub(channel);
  }

  /**
   */
  public static abstract class InterfaceServiceImplBase implements io.grpc.BindableService {

    /**
     */
    public void get(com.dahuatech.grpc.generate.Request request,
        io.grpc.stub.StreamObserver<com.dahuatech.grpc.generate.Response> responseObserver) {
      asyncUnimplementedUnaryCall(METHOD_GET, responseObserver);
    }

    @java.lang.Override public final io.grpc.ServerServiceDefinition bindService() {
      return io.grpc.ServerServiceDefinition.builder(getServiceDescriptor())
          .addMethod(
            METHOD_GET,
            asyncUnaryCall(
              new MethodHandlers<
                com.dahuatech.grpc.generate.Request,
                com.dahuatech.grpc.generate.Response>(
                  this, METHODID_GET)))
          .build();
    }
  }

  /**
   */
  public static final class InterfaceServiceStub extends io.grpc.stub.AbstractStub<InterfaceServiceStub> {
    private InterfaceServiceStub(io.grpc.Channel channel) {
      super(channel);
    }

    private InterfaceServiceStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected InterfaceServiceStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new InterfaceServiceStub(channel, callOptions);
    }

    /**
     */
    public void get(com.dahuatech.grpc.generate.Request request,
        io.grpc.stub.StreamObserver<com.dahuatech.grpc.generate.Response> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(METHOD_GET, getCallOptions()), request, responseObserver);
    }
  }

  /**
   */
  public static final class InterfaceServiceBlockingStub extends io.grpc.stub.AbstractStub<InterfaceServiceBlockingStub> {
    private InterfaceServiceBlockingStub(io.grpc.Channel channel) {
      super(channel);
    }

    private InterfaceServiceBlockingStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected InterfaceServiceBlockingStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new InterfaceServiceBlockingStub(channel, callOptions);
    }

    /**
     */
    public com.dahuatech.grpc.generate.Response get(com.dahuatech.grpc.generate.Request request) {
      return blockingUnaryCall(
          getChannel(), METHOD_GET, getCallOptions(), request);
    }
  }

  /**
   */
  public static final class InterfaceServiceFutureStub extends io.grpc.stub.AbstractStub<InterfaceServiceFutureStub> {
    private InterfaceServiceFutureStub(io.grpc.Channel channel) {
      super(channel);
    }

    private InterfaceServiceFutureStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected InterfaceServiceFutureStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new InterfaceServiceFutureStub(channel, callOptions);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<com.dahuatech.grpc.generate.Response> get(
        com.dahuatech.grpc.generate.Request request) {
      return futureUnaryCall(
          getChannel().newCall(METHOD_GET, getCallOptions()), request);
    }
  }

  private static final int METHODID_GET = 0;

  private static final class MethodHandlers<Req, Resp> implements
      io.grpc.stub.ServerCalls.UnaryMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ServerStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ClientStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.BidiStreamingMethod<Req, Resp> {
    private final InterfaceServiceImplBase serviceImpl;
    private final int methodId;

    MethodHandlers(InterfaceServiceImplBase serviceImpl, int methodId) {
      this.serviceImpl = serviceImpl;
      this.methodId = methodId;
    }

    @java.lang.Override
    @java.lang.SuppressWarnings("unchecked")
    public void invoke(Req request, io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        case METHODID_GET:
          serviceImpl.get((com.dahuatech.grpc.generate.Request) request,
              (io.grpc.stub.StreamObserver<com.dahuatech.grpc.generate.Response>) responseObserver);
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

  private static final class InterfaceServiceDescriptorSupplier implements io.grpc.protobuf.ProtoFileDescriptorSupplier {
    @java.lang.Override
    public com.google.protobuf.Descriptors.FileDescriptor getFileDescriptor() {
      return com.dahuatech.grpc.generate.Interface.getDescriptor();
    }
  }

  private static volatile io.grpc.ServiceDescriptor serviceDescriptor;

  public static io.grpc.ServiceDescriptor getServiceDescriptor() {
    io.grpc.ServiceDescriptor result = serviceDescriptor;
    if (result == null) {
      synchronized (InterfaceServiceGrpc.class) {
        result = serviceDescriptor;
        if (result == null) {
          serviceDescriptor = result = io.grpc.ServiceDescriptor.newBuilder(SERVICE_NAME)
              .setSchemaDescriptor(new InterfaceServiceDescriptorSupplier())
              .addMethod(METHOD_GET)
              .build();
        }
      }
    }
    return result;
  }
}
