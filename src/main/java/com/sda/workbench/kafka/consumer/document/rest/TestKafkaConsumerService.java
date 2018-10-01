package com.sda.workbench.kafka.consumer.document.rest;

import com.sda.workbench.kafka.consumer.TestKafkaConsumerFacade;
import com.sda.workbench.kafka.consumer.document.rest.model.DocumentRest;
import com.sdase.framework.mapping.jaxrs.fields.FieldFiltered;
import io.swagger.annotations.*;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("kafka")
@Api
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public interface TestKafkaConsumerService extends TestKafkaConsumerFacade {

   @GET
   @Path("/checkmessages")
   @Produces(MediaType.APPLICATION_JSON)
   List<String> checkKafkaMessages();


   @POST
   @Path("/testmessage")
   Response generateKafkaMessage(@ApiParam(type = "tpye of the generated message") @DefaultValue("create") @QueryParam("type") String messagetype);

   @POST
   @Path("/resetmessages")
   Response resetReceivedMessages();
}
