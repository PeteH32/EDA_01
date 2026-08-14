package com.example.eda.api;

import com.example.eda.contracts.CartAction;
import com.example.eda.contracts.CartActionEvent;
import java.time.Instant;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CartController {
    private final EventPublisher eventPublisher;

    public CartController(EventPublisher eventPublisher) { this.eventPublisher = eventPublisher; }

    @PostMapping("/cart/add")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public void add(@RequestParam String userId, @RequestParam String cartId, @RequestParam String productId) {
        publish(userId, cartId, productId, CartAction.ADD);
    }

    @PostMapping("/cart/remove")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public void remove(@RequestParam String userId, @RequestParam String cartId, @RequestParam String productId) {
        publish(userId, cartId, productId, CartAction.REMOVE);
    }

    private void publish(String userId, String cartId, String productId, CartAction action) {
        eventPublisher.publishCartAction(new CartActionEvent(userId, cartId, productId, action, Instant.now()));
    }
}
