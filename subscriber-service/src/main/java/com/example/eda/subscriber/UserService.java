package com.example.eda.subscriber;

import com.example.eda.contracts.CartActionEvent;
import com.example.eda.contracts.ProductViewEvent;
import java.util.function.Consumer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/** User-domain projections fed by the event bus. */
@Configuration
public class UserService {
    private static final Logger log = LoggerFactory.getLogger(UserService.class);

    @Bean
    Consumer<ProductViewEvent> userProductViews() {
        return event -> log.info("User service received view: userId={}, productId={}", event.userId(), event.productId());
    }

    @Bean
    Consumer<CartActionEvent> userCartActions() {
        return event -> log.info("User service received cart action: userId={}, cartId={}, action={}", event.userId(), event.cartId(), event.action());
    }
}
