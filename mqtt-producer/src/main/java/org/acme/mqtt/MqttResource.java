package org.acme.mqtt;

import jakarta.inject.Inject;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.core.Response;

@Path("/mqtt")
public class MqttResource {

    @Inject
    MqttClientService mqttClientService;

    @POST
    @Path("/send")
    public Response sendMessage(String message) {
        System.out.println("Entrando no metodo de envio mqtt");
        mqttClientService.publishMessage("test/topic", message);
        return Response.ok().build();
    }
}