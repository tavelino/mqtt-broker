package org.acme.mqtt;

import org.eclipse.paho.client.mqttv3.IMqttClient;
import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.jboss.logging.Logger;

import io.quarkus.runtime.Startup;
import jakarta.annotation.PreDestroy;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class MqttConsumerService {

    private static final Logger LOGGER = Logger.getLogger(MqttConsumerService.class);

    private IMqttClient client;

    @Startup
    public void init() {
        try {
            client = new MqttClient("tcp://localhost:1883", MqttClient.generateClientId());
            MqttConnectOptions options = new MqttConnectOptions();
            options.setCleanSession(true);
            client.connect(options);
            LOGGER.info("Connected to MQTT broker for consuming");

            client.subscribe("test/topic", (topic, message) -> {
                String payload = new String(message.getPayload());
                LOGGER.info("Received message: " + payload);
                processMessage(payload);
            });
        } catch (MqttException e) {
            LOGGER.error("Failed to connect to MQTT broker for consuming", e);
        }
    }

    private void processMessage(String message) {
        // Add logic to process the received message
        LOGGER.info("Processing message: " + message);
    }

    @PreDestroy
    public void cleanup() {
        try {
            if (client != null) {
                client.disconnect();
                client.close();
                LOGGER.info("Disconnected from MQTT broker");
            }
        } catch (MqttException e) {
            LOGGER.error("Failed to disconnect from MQTT broker", e);
        }
    }
}