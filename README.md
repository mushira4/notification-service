## Notification Service
This is a simple notification service that sends notifications to users considering a rate limit per type of message.

### Requirements to develop 
- Java 21

### Requirement to run locally
- Java 21
- Docker Compose 2.17 or Redis 7.2

#### Basic commands
- Run tests: 
```bash
./gradlew test`
```

- Generate jar:
```bash
## Currently it is not possible to generate a jar
```

- Run the application:
```bash
## Currently it cannot be run by a jar, so it is necessary to run the main class
```

## Architecture
The system is divided into 3 layers: input(console), core(domain, service, repository) and infra(cache, gateway).
The division was made considering the separation of concerns and the possibility of changing the external world interface without changing the core of the system, 
it was influenced by the concept of the hexagonal architecture. (Hexagonal Architecture Component: Primary Adapter)

### Input
This layer is responsible to receive the input from the external world and parse it to the core of the system. 

### Core
The core separates all the business logic in order to keep the system maintainable and testable.
Keeping this layer without influence of the input layer and infra layer makes it more stable as well. (Hexagonal Architecture Component: Input Port, Business Logic)

### Infra
The infra layer is responsible for the communication with systems there are not part of the core of the system. (Hexagonal Architecture Component: Output Port, Secondary Adapter)

### Designed Solution
The solution designed used Redis as a controller of the number of requests and timeframes, the main idea is to have a service
that can be scaled horizontally and keep the `rate limit` data accessible across the cluster, so Redis can be deployed and keep the data updated
between all nodes that receives the notifications.
