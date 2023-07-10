package com.dahuatech.grpc.demo;

import com.dahuatech.grpc.generate.InterfaceServiceGrpc;
import com.dahuatech.grpc.generate.Request;
import com.dahuatech.grpc.generate.Response;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;

public class Client {

    public static void main(String[] args) throws Exception {
        ManagedChannel managedChannel = ManagedChannelBuilder.forAddress("localhost", 50051)
                .usePlaintext()
                .build();
        InterfaceServiceGrpc.InterfaceServiceBlockingStub stub = InterfaceServiceGrpc.newBlockingStub(managedChannel);

        Response res = stub.get(Request.newBuilder().setName("alan").build());
        System.out.println(res);
    }
}

