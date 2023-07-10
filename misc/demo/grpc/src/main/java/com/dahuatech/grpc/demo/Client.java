package com.dahuatech.grpc.demo;

import com.dahuatech.grpc.generate.InterfaceServiceGrpc;
import com.dahuatech.grpc.generate.Request;
import com.dahuatech.grpc.generate.Response;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;

/**
 * <p>projectName: rpc</p>
 * <p>packageName: com.dahuatech.demo</p>
 * <p>className: Client</p>
 * <p>date: 2023/3/11</p>
 *
 * @author qinjiawei(alan)
 * @version 1.0.0
 * @since JDK8.0
 */
public class Client {

    public static void main(String[] args) {

        ManagedChannel managedChannel = ManagedChannelBuilder.forAddress("localhost", 6401)
                .usePlaintext()
                .build();
        InterfaceServiceGrpc.InterfaceServiceBlockingStub sub = InterfaceServiceGrpc.newBlockingStub(managedChannel);
        Response res = sub.get(Request.newBuilder().setName("alan").setAge(23).build());
        System.out.println(res);
    }
}
