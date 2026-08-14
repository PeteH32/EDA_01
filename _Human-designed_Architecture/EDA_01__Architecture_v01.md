
## Architecture Diagram
The basic architecture diagram is in file "EDA_01_Arch-diagram.png". Below describes the components in this diagram, grouped into 3 sections of the diagram - left, middle, and right.

## REST API services (left side)
On the left are REST API Services. 

 - User View Service
   - REST Endpoint: `POST /product/view`
     - Publishes Kafka event on topic "product_view"
 - Cart Service
   - REST Endpoint: `POST /cart/add`
     - Publishes Kafka event on topic "cart_action"
   - REST Endpoint: `POST /cart/remove`
     - Publishes Kafka event on topic "cart_action"

All the above REST Services are implemented within a single application, implemented using Spring `RestController` and Spring Boot. 

This application publishes Kafka events using Spring Cloud Stream and the Apache Kafka binder.

## Event Bus (middle)
In the middle is the event bus, implemented using Kafka running in docker containers.

## Event Subscribers (right side)
On the right are some services, which are subscribed to some of the Kafka events.

- Product Service
  - Subscribed to Kafka events on topic "product_view"
  - Subscribed to Kafka events on topic "cart_action"
- User Service
  - Subscribed to Kafka events on topic "product_view"
  - Subscribed to Kafka events on topic "cart_action"

The above services are implemented using Spring and Spring Boot. This application consumes Kafka events using Spring Cloud Stream and the Apache Kafka binder.
