package com.example.eda.contracts;

import java.time.Instant;

/** Event emitted whenever an item is added to or removed from a cart. */
public record CartActionEvent(String userId, String cartId, String productId, CartAction action, Instant occurredAt) { }
