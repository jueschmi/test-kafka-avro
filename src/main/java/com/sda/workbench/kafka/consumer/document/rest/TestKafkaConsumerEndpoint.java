package com.sda.workbench.kafka.consumer.document.rest;

import com.sdase.avro.schema.document.Classification;
import java.time.Instant;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;

import javax.inject.Inject;
import javax.ws.rs.core.Response;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.sda.workbench.kafka.consumer.document.rest.model.DocumentRest;
import com.sda.workbench.kafka.consumer.events.DocumentEventRepository;
import com.sda.workbench.kafka.consumer.mapping.DocumentODSMapper;
import com.sdase.avro.schema.document.DocumentODSCreate;
import com.sdase.avro.schema.document.DocumentODSDelete;
import com.sdase.avro.schema.document.DocumentODSEvent;
import com.sdase.avro.schema.document.DocumentODSEventType;
import com.sdase.avro.schema.document.InOutboundType;
import com.sdase.avro.schema.document.PartnerRoleType;
import com.sdase.avro.schema.document.RelevantPartner;
import com.sdase.avro.schema.document.SoRKey;
import com.sdase.framework.kafka.bundle.producer.MessageProducer;

public class TestKafkaConsumerEndpoint implements TestKafkaConsumerService {
	private static final Logger LOGGER = LoggerFactory.getLogger(TestKafkaConsumerEndpoint.class);

	private final MessageProducer<String, DocumentODSEvent> producer;
	private final DocumentEventRepository eventRepository;

	@Inject
	private DocumentODSMapper odsMapper;

	@Inject
	public TestKafkaConsumerEndpoint(final MessageProducer<String, DocumentODSEvent> producer,
			final DocumentEventRepository eventRepository) {
		this.producer = producer;
		this.eventRepository = eventRepository;
	}

	@Override
	public List<DocumentRest> checkKafkaMessages() {
		List<DocumentODSEvent> result = eventRepository.findAll();

		if (!result.isEmpty()) {

			List<DocumentRest> restDocs = result.stream().map(d -> odsMapper.mapToRestModel(d))
					.collect(Collectors.toList());

			return restDocs;
		}

		return new ArrayList<DocumentRest>();
		// List<String> collect = result.stream().map(s ->
		// s.toString()).collect(Collectors.toList());
		//
		// return collect;
	}

	@Override
	public Response generateKafkaMessage(String messagetype) {

		if (producer != null) {
			final String uuid = UUID.randomUUID().toString();
			final Map<String, String> sorKeyElements = Collections.singletonMap("DOCID", uuid);
			final List<String> sorResourceKeys = Arrays.asList(UUID.randomUUID().toString());

			final SoRKey sorKey = SoRKey.newBuilder().setSorKeyElements(sorKeyElements).setSorResourceKeys(sorResourceKeys).build();

			DocumentODSEvent event = null;
			switch (messagetype) {
				case "create" :
					final ArrayList<String> classificationIds = new ArrayList();
					classificationIds.add("1");
					classificationIds.add("2");

					final ZonedDateTime zdt = Instant.now().atZone(ZoneId.of("Europe/Paris"));
					final String sZDT = zdt.toString();
					final List<RelevantPartner> relevantPartners = new ArrayList<>();
					final RelevantPartner partner = RelevantPartner.newBuilder()
							.setPartnerId(UUID.randomUUID().toString()).setPartnerRole(PartnerRoleType.CUSTOMER)
							.build();
					relevantPartners.add(partner);

					final List<Classification> classifications = new ArrayList<>();
					final Classification classification = Classification.newBuilder()
							.setId("Vertrag")
							.setType("12345678")
							.build();
					classifications.add(classification);

					event = DocumentODSEvent.newBuilder().setType(DocumentODSEventType.DOCUMENT_CREATE)
							.setPayload(DocumentODSCreate.newBuilder().setId(uuid).setExternalId(sorKey)
									.setTitle("Test Document Title")
									.setCategory("Haftpflicht")
									.setType("Schadenmeldung")
									.setDate(sZDT)
									.setClassifications(classifications)
									.setInOutbound(InOutboundType.INBOUND)
									.setRelevantPartners(relevantPartners)
									.setBusinessTransactionId("123").build())
							.build();

					break;
				case "delete" :
					event = DocumentODSEvent.newBuilder().setType(DocumentODSEventType.DOCUMENT_DELETE)
							.setPayload(DocumentODSDelete.newBuilder().setId(uuid).setExternalId(sorKey).build())
							.build();
					break;

			}

			producer.send(uuid, event);
		}

		return Response.ok().build();
	}

	@Override
	public Response resetReceivedMessages() {
		eventRepository.deleteAll();

		return Response.ok().build();
	}
}
