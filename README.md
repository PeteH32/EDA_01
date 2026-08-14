# Event-driven architecture example

This repository implements the supplied architecture with two Spring Boot applications, Gradle, and Apache Kafka.

- `api-service` exposes `POST /product/view`, `POST /cart/add`, and `POST /cart/remove`, and publishes events through Spring Cloud Stream's Kafka binder.
- `subscriber-service` contains independently grouped Product and User consumers for both event types.
- `event-contracts` holds the Java event schema shared by the two applications.

## Human-designed architecture

The [_Human-designed_Architecture](_Human-designed_Architecture) directory contains the source architecture materials: `EDA_01_Arch-diagram.png` is the visual diagram, and `EDA_01__Architecture_v01.md` describes the components and event flows. Both specify `cart_action` as the cart-event Kafka topic.

## Run locally

Start Kafka, then run each application in its own terminal. The included Gradle wrapper downloads Gradle 8.10.2 automatically; Java 21 is required.

```sh
docker compose up -d
./gradlew :api-service:bootRun
./gradlew :subscriber-service:bootRun
```

Compile and run the test suite with:

```sh
./gradlew test
```

Publish example events:

```sh
curl -X POST 'http://localhost:8080/product/view?userId=u1&productId=p1'
curl -X POST 'http://localhost:8080/cart/add?userId=u1&cartId=c1&productId=p1'
curl -X POST 'http://localhost:8080/cart/remove?userId=u1&cartId=c1&productId=p1'
```

Each endpoint returns `202 Accepted` once its event is handed to the Spring Cloud Stream Kafka binder. The subscribers log received events; replace those handlers with persistence or domain logic as needed.
