package com.pm.patientservice.grpc;

import com.pm.common.grpc.billing.BillingRequest;
import com.pm.common.grpc.billing.BillingResponse;
import com.pm.common.grpc.billing.BillingServiceGrpc;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import com.pm.common.grpc.billing.BillingServiceGrpc.BillingServiceBlockingStub;
@Service
public class BillingServiceGrpcClient {
    private static final Logger log = LoggerFactory
            .getLogger(BillingServiceGrpcClient.class);
    private final BillingServiceBlockingStub blockingStub;

    public BillingServiceGrpcClient(
            @Value("${billing.service.address:localhost}") String serverAddress,
            @Value("${billing.service.grpc.port:9001}") int serverPort) {

        log.info("Connecting to Billing Service GRPC service at {}:{}",
                serverAddress, serverPort);

        ManagedChannel managedChannel = ManagedChannelBuilder
                .forAddress(serverAddress, serverPort)
                .usePlaintext()
                .build();

        blockingStub = BillingServiceGrpc.newBlockingStub(managedChannel);
    }

    public BillingResponse createBillingAccount(
            String patientId,
            String name,
            String email) {

        BillingRequest request = BillingRequest
                .newBuilder()
                .setPatientId(patientId)
                .setName(name)
                .setEmail(email)
                .build();

        BillingResponse response = blockingStub
                .createBillingAccount(request);

        log.info("Received Response from billing service via GRPC: {}", response);

        return response;

    }
}
