package com.sda.workbench.kafka.consumer.streaming;

import javax.enterprise.inject.Default;
import javax.enterprise.inject.Instance;
import javax.enterprise.inject.spi.CDI;
import javax.enterprise.util.AnnotationLiteral;
import javax.inject.Inject;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.sda.workbench.kafka.consumer.events.DocumentEventRepository;
import com.sda.workbench.kafka.consumer.mapping.DocumentODSMapper;
import com.sdase.avro.schema.document.DocumentODSEvent;
import com.sdase.framework.kafka.bundle.consumer.MessageHandler;

public class DocumentMessageHandler implements MessageHandler<String, DocumentODSEvent> {
	private static final Logger LOGGER = LoggerFactory.getLogger(DocumentMessageHandler.class);

	@Inject
	private DocumentODSMapper odsMapper;

	private DocumentEventRepository eventRepository = null;

	public DocumentMessageHandler() {
	}

	@Override
	public void handle(ConsumerRecord<String, DocumentODSEvent> record) {
		if (eventRepository == null) {
			getDocumentEventRepositoryFromCDI();
		}

		final DocumentODSEvent value = record.value();
		eventRepository.save(value);
		if (LOGGER.isDebugEnabled()) {
			LOGGER.debug("Incomming message , topic={} ,  key={} , value={}", record.topic(), record.key(), value);
		}
	}

	private void getDocumentEventRepositoryFromCDI() {
		Instance<DocumentEventRepository> sessionInstance = CDI.current().select(DocumentEventRepository.class,
				new AnnotationLiteral<Default>() {
				});
		this.eventRepository = sessionInstance.get();
	}
}
