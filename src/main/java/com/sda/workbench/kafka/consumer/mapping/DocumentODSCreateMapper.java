package com.sda.workbench.kafka.consumer.mapping;

import java.time.ZonedDateTime;
import java.util.List;
import java.util.stream.Collectors;

import com.sda.workbench.kafka.consumer.document.rest.model.DocumentRestCreate;
import com.sda.workbench.kafka.consumer.document.rest.model.InOutBoundType;
import com.sdase.avro.schema.document.DocumentODSCreate;
import com.sdase.avro.schema.document.RelevantPartner;

import ma.glasnost.orika.CustomMapper;
import ma.glasnost.orika.MappingContext;

public class DocumentODSCreateMapper extends CustomMapper<DocumentODSCreate, DocumentRestCreate> {

	@Override
	public void mapAtoB(DocumentODSCreate a, DocumentRestCreate b, MappingContext mappingContext) {
		b.setEventType("document_create");

		b.setUuid(a.getId());
		b.setTitle(a.getTitle());
		b.setCategory(a.getCategory());
		b.setDate((a.getDate() != null) ? ZonedDateTime.parse(a.getDate()) : null);
		b.setDateIn((a.getDateIn() != null) ? ZonedDateTime.parse(a.getDateIn()) : null);
		b.setOriginalSender(a.getOriginalSender());
		b.setOriginalReceiver(a.getOriginalReceiver());
		b.setInOutBound(InOutBoundType.INBOUND);
		b.setClassificationType(a.getClassificationType());
		b.setClassificationIds(a.getClassificationIds());
		b.setProtectionClass(a.getProtectionClass() != null ? a.getProtectionClass().intValue() : 1);

		b.setExternalId(mapperFacade.map(a.getExternalId(), com.sda.workbench.kafka.consumer.document.rest.model.SoRKey.class));

		List<com.sda.workbench.kafka.consumer.document.rest.model.RelevantPartner> relevantPartners = a
				.getRelevantPartners().stream().map(p -> convertDODSRelevantPartner(p)).collect(Collectors.toList());

		b.setRelevantPartners(relevantPartners);
	}

	private com.sda.workbench.kafka.consumer.document.rest.model.RelevantPartner convertDODSRelevantPartner(
			RelevantPartner relevantPartner) {

		return mapperFacade.map(relevantPartner, com.sda.workbench.kafka.consumer.document.rest.model.RelevantPartner.class);
	}
}