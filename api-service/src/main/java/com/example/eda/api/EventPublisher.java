package com.example.eda.api;

import com.example.eda.contracts.CartActionEvent;
import com.example.eda.contracts.ProductViewEvent;
import org.springframework.cloud.stream.function.StreamBridge;
import org.springframework.stereotype.Service;

@Service
public class EventPublisher {
    private final StreamBridge streamBridge;

    public EventPublisher(StreamBridge streamBridge) {
        this.streamBridge = streamBridge;
    }

    public void publishProductView(ProductViewEvent event) {
        send("productView-out-0", event);
    }

    public void publishCartAction(CartActionEvent event) {
        send("cartAction-out-0", event);
    }

    private void send(String bindingName, Object event) {
        if (!streamBridge.send(bindingName, event)) {
            throw new IllegalStateException("Kafka event could not be published to " + bindingName);
        }
    }
}
