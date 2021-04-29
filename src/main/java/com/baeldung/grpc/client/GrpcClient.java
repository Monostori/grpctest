package com.baeldung.grpc.client;

import com.baeldung.grpc.HelloRequest;
import com.baeldung.grpc.HelloResponse;
import com.baeldung.grpc.HelloServiceGrpc;

import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;

public class GrpcClient {
    public static void main(String[] args) throws InterruptedException {
        ManagedChannel channel = ManagedChannelBuilder.forAddress("localhost", 8080)
            .usePlaintext()
            .build();

        for (int i=0;i<1000000;i++) {

            HelloServiceGrpc.HelloServiceBlockingStub stub
                    = HelloServiceGrpc.newBlockingStub(channel);

            HelloResponse helloResponse = stub.hello(HelloRequest.newBuilder()
                    .setFirstName("Baeldung")
                    .setLastName("gRPC")
                    .build());

            System.out.println("Response #"+i+" received from server:\n" + helloResponse);
        }

        channel.shutdown();
    }
}
