# mqtt-broker

There are two projects here, mqtt-producer and mqtt-server. To run this application you need to run first mqq-server, and after that mqtt-producer.
For run mqtt-server:

```
cd mqtt-server
quarkus dev
```

For run mqtt-producer.

```
cd mqtt-producer
quarkus dev
```

For deploying thouse applications in openshift is necessary to config the tag quarkus.openshift.deploy=true in application.properties and select your project and after that just build the application.

```
oc project <<my-project>>
cd mqtt-server
quarkus build
```

For producer application:

```
oc project <<my-project>>
cd mqtt-producer
quarkus build
```

To test the application is necessary to send a post to the endpoint mqtt/send. After that you can follow the logs of the consumer application.

```
curl -X POST -d "Hello from Quarkus client" http://localhost:8080/mqtt/send
```
