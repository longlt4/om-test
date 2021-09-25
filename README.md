# om-test
om test


## Design
* Om Gateway
This is a service for handling, routing requests from internet, public servers and internal server. These requests should be checked authentication before calling to destination services.

For the detail, please refer to https://github.com/long-laithanh/om-test/blob/main/om-gateway-design.drawio; and open it on draw.io

* OmUserService
This is a service for executing CRUD operations on User database


- Model URIs
	/users
	/users/{id}


## Implementation
- Pre-require
	+ JDK 11
	+ Maven

- Source code
Clone from https://github.com/long-laithanh/om-test.git


- How to run
* for user-service project
./mvnw clean spring-boot:run

* accessing
Create new:
$ curl -X POST localhost:8081/userss -H 'Content-type:application/json' -d '{"username": "test-username", "email": "test-username@email.com"}'

Read all:
$ curl -v localhost:8081/users

Read one:
$ curl -v localhost:8081/users/1

Update:
$ curl -X PUT localhost:8081/users/1 -H 'Content-type:application/json' -d '{"username": "username1", "email": "username11@email.com"}'


Delete:
$ curl -X DELETE localhost:8081/users/1


for om-gateway project
./mvnw clean spring-boot:run
get user by id:
$ curl -v localhost:8089/public/api/mobile/v1/users/1

## Deployment
This is a deployment diagram for om-gateway and user-service on live server.
Please refer to : https://github.com/long-laithanh/om-test/blob/main/om-deployment-diagram.drawio






## More actions

### Other business/technical features
#### Business
- Multiple devices need to access:
For example: Car, television, smartphone, smarthome devices, ...

- Multiple applications and services need to access:
For example: Health care, retails, banks, ensurance, ...

- Thirdparty applications and services

- User experience, supporting and suggestion


#### Technial
- Security:
For example: logged devices, services, applications, locations

- Tracing activities log and analysis log to improve customer experience:


### More time
- Redesign om-gateway: it should be applied pattern back-end for front-end if it needs to support multiple devices, various kinds of applications
- Authentication and routing from internet, public servers or internal servers
- Adding actuator module for supporting management service after deployment
- Refine source code and handling exception cases


### Addtion
- Adding dynamic configuratons
- Building docker image for these services
- Adding Spring Boot Actuator module to services
- Adding ELK stack for logging

