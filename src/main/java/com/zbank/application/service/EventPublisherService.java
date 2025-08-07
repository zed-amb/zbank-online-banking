package com.zbank.application.service;

import com.zbank.application.util.EventBridgePublisher;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class EventPublisherService {

    private static final Logger logger = LoggerFactory.getLogger(EventPublisherService.class);

    public void publish(Object event, String source, String detailType) {
        try {
            EventBridgePublisher.publish(source, detailType, event);
            logger.info("Published event: {} with source: {}", detailType, source);
        } catch (Exception e) {
            logger.error("Failed to publish event: {} — {}", detailType, e.getMessage());
            throw new RuntimeException("Failed to publish event to EventBridge", e);
        }
    }
}
