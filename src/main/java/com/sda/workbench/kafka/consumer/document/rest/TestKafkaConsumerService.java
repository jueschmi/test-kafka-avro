package com.sda.workbench.kafka.consumer.document.rest;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DefaultValue;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.sda.workbench.kafka.consumer.TestKafkaConsumerFacade;
import com.sda.workbench.kafka.consumer.document.rest.model.DocumentRest;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiParam;

@Path("kafka")
@Api
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public interface TestKafkaConsumerService extends TestKafkaConsumerFacade {

	@GET
	@Path("/checkmessages")
	@Produces(MediaType.APPLICATION_JSON)
	List<DocumentRest> checkKafkaMessages();

	@POST
	@Path("/testmessage")
	Response generateKafkaMessage(
			@ApiParam(type = "tpye of the generated message") @DefaultValue("create") @QueryParam("type") String messagetype);

	@POST
	@Path("/resetmessages")
	Response resetReceivedMessages();
}
