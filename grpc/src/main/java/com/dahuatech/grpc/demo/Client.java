package com.dahuatech.grpc.demo;

import com.dahuatech.grpc.generate.rpcGrpc;
import com.dahuatech.grpc.generate.Request;
import com.dahuatech.grpc.generate.Response;
import io.grpc.ManagedChannel;
import io.grpc.netty.NettyChannelBuilder;
import io.grpc.stub.StreamObserver;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.CountedCompleter;
import java.util.concurrent.TimeUnit;

public class Client {
    private static ManagedChannel channel = NettyChannelBuilder.forAddress("localhost", 50051)
            .idleTimeout(3, TimeUnit.MINUTES)
            .maxRetryAttempts(3)
            .keepAliveTime(10, TimeUnit.SECONDS)
            .keepAliveTimeout(30, TimeUnit.SECONDS)
            .keepAliveWithoutCalls(true)
            .maxInboundMetadataSize(6 * 1024 * 1024)
            .maxInboundMessageSize(6 * 1024 * 1024)
            .usePlaintext()
            .build();
    private static rpcGrpc.rpcBlockingStub rpcBlockingStub = rpcGrpc.newBlockingStub(channel);
    private static rpcGrpc.rpcStub rpcStub = rpcGrpc.newStub(channel);

    public static void main(String[] args) throws Exception {

        while (true) {
            TimeUnit.SECONDS.sleep(1);
            once(rpcBlockingStub);
        }
        // stream(rpcStub);

    }

    /**
     * 一次请求
     * @param rpcBlockingStub
     * @throws InterruptedException
     */
    public static void once(rpcGrpc.rpcBlockingStub rpcBlockingStub) throws InterruptedException {
        // 封装数据
        Request request = Request.newBuilder().setName("alan").setAge(10).build();

        // 调用接口
        Response res = rpcBlockingStub.once(request);

        // 返回数据
        System.out.println(res);
    }

    /**
     * 流式请求
     * @param rpcStub
     * @throws InterruptedException
     */
    public static void stream(rpcGrpc.rpcStub rpcStub) throws InterruptedException {
        CountDownLatch countDownLatch = new CountDownLatch(10);

        // 封装请求
        Request request = Request.newBuilder().setName("alan").setAge(10).build();

        // 定义返送落
        StreamObserver<Request> streamObserver = rpcStub.stream(new StreamObserver<Response>() {
            @Override
            public void onNext(Response value) {
                System.out.println(value.getStudentList());
            }

            @Override
            public void onError(Throwable t) {
                countDownLatch.countDown();
                System.out.println("onError" + t);
            }

            @Override
            public void onCompleted() {
                countDownLatch.countDown();
                System.out.println("onCompleted");
            }
        });

        for (int i = 0; i < 10; i++) {
            // 返送数据
            streamObserver.onNext(request);
        }

        // 返回ack
        streamObserver.onCompleted();

        countDownLatch.await();
    }
}

