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
    comments = "Source: rpc.proto")
public final class rpcGrpc {

  private rpcGrpc() {}

  public static final String SERVICE_NAME = "com.dahuatech.grpc.rpc";

  // Static method descriptors that strictly reflect the proto.
  @io.grpc.ExperimentalApi("https://github.com/grpc/grpc-java/issues/1901")
  public static final io.grpc.MethodDescriptor<com.dahuatech.grpc.generate.Request,
      com.dahuatech.grpc.generate.Response> METHOD_ONCE =
      io.grpc.MethodDescriptor.create(
          io.grpc.MethodDescriptor.MethodType.UNARY,
          generateFullMethodName(
              "com.dahuatech.grpc.rpc", "once"),
          io.grpc.protobuf.ProtoUtils.marshaller(com.dahuatech.grpc.generate.Request.getDefaultInstance()),
          io.grpc.protobuf.ProtoUtils.marshaller(com.dahuatech.grpc.generate.Response.getDefaultInstance()));
  @io.grpc.ExperimentalApi("https://github.com/grpc/grpc-java/issues/1901")
  public static final io.grpc.MethodDescriptor<com.dahuatech.grpc.generate.Request,
      com.dahuatech.grpc.generate.Response> METHOD_STREAM =
      io.grpc.MethodDescriptor.create(
          io.grpc.MethodDescriptor.MethodType.BIDI_STREAMING,
          generateFullMethodName(
              "com.dahuatech.grpc.rpc", "stream"),
          io.grpc.protobuf.ProtoUtils.marshaller(com.dahuatech.grpc.generate.Request.getDefaultInstance()),
          io.grpc.protobuf.ProtoUtils.marshaller(com.dahuatech.grpc.generate.Response.getDefaultInstance()));

  /**
   * Creates a new async stub that supports all call types for the service
   */
  public static rpcStub newStub(io.grpc.Channel channel) {
    return new rpcStub(channel);
  }

  /**
   * Creates a new blocking-style stub that supports unary and streaming output calls on the service
   */
  public static rpcBlockingStub newBlockingStub(
      io.grpc.Channel channel) {
    return new rpcBlockingStub(channel);
  }

  /**
   * Creates a new ListenableFuture-style stub that supports unary and streaming output calls on the service
   */
  public static rpcFutureStub newFutureStub(
      io.grpc.Channel channel) {
    return new rpcFutureStub(channel);
  }

  /**
   */
  public static abstract class rpcImplBase implements io.grpc.BindableService {

    /**
     */
    public void once(com.dahuatech.grpc.generate.Request request,
        io.grpc.stub.StreamObserver<com.dahuatech.grpc.generate.Response> responseObserver) {
      asyncUnimplementedUnaryCall(METHOD_ONCE, responseObserver);
    }

    /**
     */
    public io.grpc.stub.StreamObserver<com.dahuatech.grpc.generate.Request> stream(
        io.grpc.stub.StreamObserver<com.dahuatech.grpc.generate.Response> responseObserver) {
      return asyncUnimplementedStreamingCall(METHOD_STREAM, responseObserver);
    }

    @java.lang.Override public final io.grpc.ServerServiceDefinition bindService() {
      return io.grpc.ServerServiceDefinition.builder(getServiceDescriptor())
          .addMethod(
            METHOD_ONCE,
            asyncUnaryCall(
              new MethodHandlers<
                com.dahuatech.grpc.generate.Request,
                com.dahuatech.grpc.generate.Response>(
                  this, METHODID_ONCE)))
          .addMethod(
            METHOD_STREAM,
            asyncBidiStreamingCall(
              new MethodHandlers<
                com.dahuatech.grpc.generate.Request,
                com.dahuatech.grpc.generate.Response>(
                  this, METHODID_STREAM)))
          .build();
    }
  }

  /**
   */
  public static final class rpcStub extends io.grpc.stub.AbstractStub<rpcStub> {
    private rpcStub(io.grpc.Channel channel) {
      super(channel);
    }

    private rpcStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected rpcStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new rpcStub(channel, callOptions);
    }

    /**
     */
    public void once(com.dahuatech.grpc.generate.Request request,
        io.grpc.stub.StreamObserver<com.dahuatech.grpc.generate.Response> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(METHOD_ONCE, getCallOptions()), request, responseObserver);
    }

    /**
     */
    public io.grpc.stub.StreamObserver<com.dahuatech.grpc.generate.Request> stream(
        io.grpc.stub.StreamObserver<com.dahuatech.grpc.generate.Response> responseObserver) {
      return asyncBidiStreamingCall(
          getChannel().newCall(METHOD_STREAM, getCallOptions()), responseObserver);
    }
  }

  /**
   */
  public static final class rpcBlockingStub extends io.grpc.stub.AbstractStub<rpcBlockingStub> {
    private rpcBlockingStub(io.grpc.Channel channel) {
      super(channel);
    }

    private rpcBlockingStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected rpcBlockingStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new rpcBlockingStub(channel, callOptions);
    }

    /**
     */
    public com.dahuatech.grpc.generate.Response once(com.dahuatech.grpc.generate.Request request) {
      return blockingUnaryCall(
          getChannel(), METHOD_ONCE, getCallOptions(), request);
    }
  }

  /**
   */
  public static final class rpcFutureStub extends io.grpc.stub.AbstractStub<rpcFutureStub> {
    private rpcFutureStub(io.grpc.Channel channel) {
      super(channel);
    }

    private rpcFutureStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected rpcFutureStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new rpcFutureStub(channel, callOptions);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<com.dahuatech.grpc.generate.Response> once(
        com.dahuatech.grpc.generate.Request request) {
      return futureUnaryCall(
          getChannel().newCall(METHOD_ONCE, getCallOptions()), request);
    }
  }

  private static final int METHODID_ONCE = 0;
  private static final int METHODID_STREAM = 1;

  private static final class MethodHandlers<Req, Resp> implements
      io.grpc.stub.ServerCalls.UnaryMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ServerStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ClientStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.BidiStreamingMethod<Req, Resp> {
    private final rpcImplBase serviceImpl;
    private final int methodId;

    MethodHandlers(rpcImplBase serviceImpl, int methodId) {
      this.serviceImpl = serviceImpl;
      this.methodId = methodId;
    }

    @java.lang.Override
    @java.lang.SuppressWarnings("unchecked")
    public void invoke(Req request, io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        case METHODID_ONCE:
          serviceImpl.once((com.dahuatech.grpc.generate.Request) request,
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
        case METHODID_STREAM:
          return (io.grpc.stub.StreamObserver<Req>) serviceImpl.stream(
              (io.grpc.stub.StreamObserver<com.dahuatech.grpc.generate.Response>) responseObserver);
        default:
          throw new AssertionError();
      }
    }
  }

  private static final class rpcDescriptorSupplier implements io.grpc.protobuf.ProtoFileDescriptorSupplier {
    @java.lang.Override
    public com.google.protobuf.Descriptors.FileDescriptor getFileDescriptor() {
      return com.dahuatech.grpc.generate.Interface.getDescriptor();
    }
  }

  private static volatile io.grpc.ServiceDescriptor serviceDescriptor;

  public static io.grpc.ServiceDescriptor getServiceDescriptor() {
    io.grpc.ServiceDescriptor result = serviceDescriptor;
    if (result == null) {
      synchronized (rpcGrpc.class) {
        result = serviceDescriptor;
        if (result == null) {
          serviceDescriptor = result = io.grpc.ServiceDescriptor.newBuilder(SERVICE_NAME)
              .setSchemaDescriptor(new rpcDescriptorSupplier())
              .addMethod(METHOD_ONCE)
              .addMethod(METHOD_STREAM)
              .build();
        }
      }
    }
    return result;
  }
}
