package com.example.eda.contracts;

import java.time.Instant;

/** Event emitted whenever a user views a product. */
public record ProductViewEvent(String userId, String productId, Instant occurredAt) { }
