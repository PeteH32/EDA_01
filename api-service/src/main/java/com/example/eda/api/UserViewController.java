package com.example.eda.api;

import com.example.eda.contracts.ProductViewEvent;
import java.time.Instant;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserViewController {
    private final EventPublisher eventPublisher;

    public UserViewController(EventPublisher eventPublisher) { this.eventPublisher = eventPublisher; }

    @PostMapping("/product/view")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public void viewProduct(@RequestParam String userId, @RequestParam String productId) {
        eventPublisher.publishProductView(new ProductViewEvent(userId, productId, Instant.now()));
    }
}
