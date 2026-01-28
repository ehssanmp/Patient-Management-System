package com.pm.billingservice.grpc;

import com.pm.common.grpc.billing.BillingServiceGrpc.BillingServiceImplBase;
import com.pm.common.grpc.billing.BillingRequest;
import com.pm.common.grpc.billing.BillingResponse;
import io.grpc.stub.StreamObserver;
import net.devh.boot.grpc.server.service.GrpcService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@GrpcService
public class BillingGrpcService extends BillingServiceImplBase {

    private static final Logger log = LoggerFactory.getLogger(BillingGrpcService.class);

    @Override
    public void createBillingAccount(BillingRequest billingRequest,
                                     StreamObserver<BillingResponse> responseObserver) {
        log.info("createBillingAccount request received {}", billingRequest.toString());

        // business logic - e.g save to database, perform calculates etc

        BillingResponse response = BillingResponse.newBuilder()
                .setAccountId("12345")
                .setStatus("ACTIVE")
                .build();
        // 2. SEND the response (Missing in your code)
        responseObserver.onNext(response);

        // 3. CLOSE the connection (Missing in your code)
        responseObserver.onCompleted();
    }

}
