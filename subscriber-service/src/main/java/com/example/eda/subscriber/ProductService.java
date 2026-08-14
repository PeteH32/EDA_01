package com.example.eda.subscriber;

import com.example.eda.contracts.CartActionEvent;
import com.example.eda.contracts.ProductViewEvent;
import java.util.function.Consumer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/** Product-domain projections fed by the event bus. */
@Configuration
public class ProductService {
    private static final Logger log = LoggerFactory.getLogger(ProductService.class);

    @Bean
    Consumer<ProductViewEvent> productProductViews() {
        return event -> log.info("Product service received view: productId={}, userId={}", event.productId(), event.userId());
    }

    @Bean
    Consumer<CartActionEvent> productCartActions() {
        return event -> log.info("Product service received cart action: productId={}, action={}", event.productId(), event.action());
    }
}
