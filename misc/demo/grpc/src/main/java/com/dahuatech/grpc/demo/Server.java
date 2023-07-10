package com.dahuatech.grpc.demo;

import com.dahuatech.grpc.generate.InterfaceServiceGrpc;
import com.dahuatech.grpc.generate.Request;
import com.dahuatech.grpc.generate.Response;
import com.dahuatech.grpc.generate.Student;
import io.grpc.ServerBuilder;
import io.grpc.stub.StreamObserver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * <p>projectName: rpc</p>
 * <p>packageName: com.dahuatech.test</p>
 * <p>className: TestService</p>
 * <p>date: 2023/3/10</p>
 *
 * @author qinjiawei(336105)
 * @version 1.0.0
 * @since JDK8.0
 */
public class Server extends InterfaceServiceGrpc.InterfaceServiceImplBase {

    private Logger logger = LoggerFactory.getLogger(getClass());

    public void get(Request request, StreamObserver<Response> responseObserver) {
        Response response = null;
        try {
            response = Response.newBuilder().setStudent(Student.newBuilder().setName(request.getName()).setAge(request.getAge()).setAddress("ahut").build()).build();
        } catch (Exception e) {
            response = Response.newBuilder().setStudent(Student.newBuilder().setName(request.getName()).setAge(request.getAge()).setAddress("ahut").build()).build();
        } finally {
            responseObserver.onNext(response);
            responseObserver.onCompleted();
        }
    }

    public static void main(String[] args) throws Exception {

        ServerBuilder.forPort(6401).addService(new Server()).build().start().awaitTermination();
    }
}