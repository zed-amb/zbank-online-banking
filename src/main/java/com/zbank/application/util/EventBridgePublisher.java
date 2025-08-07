package com.zbank.application.util;

import com.fasterxml.jackson.databind.ObjectMapper;
import software.amazon.awssdk.services.eventbridge.EventBridgeClient;
import software.amazon.awssdk.services.eventbridge.model.*;

public class EventBridgePublisher {
    private static final EventBridgeClient client = EventBridgeClient.create();

    public static void publish(String source, String detailType, Object detailObj) throws Exception {
        String detail = new ObjectMapper().writeValueAsString(detailObj);

        PutEventsRequestEntry entry = PutEventsRequestEntry.builder()
                .source(source)
                .detailType(detailType)
                .detail(detail)
                .eventBusName("default") // or custom if created
                .build();

        PutEventsRequest request = PutEventsRequest.builder()
                .entries(entry)
                .build();

        client.putEvents(request);
    }
}
