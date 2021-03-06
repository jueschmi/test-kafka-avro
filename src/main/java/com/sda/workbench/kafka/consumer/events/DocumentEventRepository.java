package com.sda.workbench.kafka.consumer.events;

import java.util.List;

import com.sdase.avro.schema.document.DocumentODSEvent;

public interface DocumentEventRepository {

	List<DocumentODSEvent> findAll();

	DocumentODSEvent save(DocumentODSEvent event);

	void deleteAll();
}
