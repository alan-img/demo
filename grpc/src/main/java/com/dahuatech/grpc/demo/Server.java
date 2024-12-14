package com.dahuatech.grpc.demo;

import com.dahuatech.grpc.generate.rpcGrpc;
import com.dahuatech.grpc.generate.Request;
import com.dahuatech.grpc.generate.Response;
import com.dahuatech.grpc.generate.Student;
import io.grpc.ServerBuilder;
import io.grpc.stub.StreamObserver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>projectName: rpc</p>
 * <p>packageName: com.dahuatech.streaming</p>
 * <p>className: TestService</p>
 * <p>date: 2023/3/10</p>
 *
 * @author qinjiawei(336105)
 * @version 1.0.0
 * @since JDK8.0
 */
public class Server extends rpcGrpc.rpcImplBase {

    private Logger logger = LoggerFactory.getLogger(getClass());

    @Override
    public void once(Request request, StreamObserver<Response> responseObserver) {
        // 获取数据
        String name = request.getName();
        int age = request.getAge();

        // 处理逻辑
        Student student = Student.newBuilder()
                .setName(name)
                .setAge(age)
                .setAddress("hangzhou")
                .build();

        // 返回数据
        Response response = Response.newBuilder().addStudent(student).build();
        responseObserver.onNext(response);

        // 返回ack
        responseObserver.onCompleted();

        // 如果发生错误则调用responseObserver.onError(throwable)
        // responseObserver.onError(throwable);
    }

    private List<Student> list = new ArrayList<>();

    @Override
    public StreamObserver<Request> stream(StreamObserver<Response> responseObserver) {
        return new StreamObserver<Request>() {
            @Override
            public void onNext(Request value) {
                // 接受数据
                String name = value.getName();
                int age = value.getAge();

                // 处理逻辑
                // Response response = Response.newBuilder().addStudent(Student.newBuilder().setName(name).setAge(age).build()).build();
                // 返回消息
                // responseObserver.onNext(response);

                list.add(Student.newBuilder().setName(name).setAge(age).build());

                System.out.println("onNext...");
            }

            @Override
            public void onError(Throwable t) {
                System.out.println("onError..." + t.getMessage());
                responseObserver.onError(t);
            }

            @Override
            public void onCompleted() {
                System.out.println("onCompleted...");
                // 处理完全部数据后一次性返回
                Response response = Response.newBuilder().addAllStudent(list).build();
                responseObserver.onNext(response);
                responseObserver.onCompleted();
            }
        };
    }

    public static void main(String[] args) throws Exception {

        ServerBuilder.forPort(50051).addService(new Server()).build().start().awaitTermination();

    }
}