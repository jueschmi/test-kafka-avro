package com.sda.workbench.kafka.consumer.document.rest.model;

import java.time.ZonedDateTime;
import java.util.List;

import org.hibernate.validator.constraints.NotEmpty;

import com.fasterxml.jackson.annotation.JsonFormat;

import io.swagger.annotations.ApiModelProperty;

public class DocumentRestCreate extends DocumentRest {
	@NotEmpty
	@ApiModelProperty(value = "The document title.", example = "AVB ", required = true)
	private String title;

	@NotEmpty
	@ApiModelProperty(value = "The category of document.", example = "Haftpflichtversicherung", required = true)
	private String category;

	@ApiModelProperty(value = "The type of document.", example = "Schadenmeldung, Police , Rechnung")
	private String type;

	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss.SSSZ")
	@ApiModelProperty(value = "Date of the document printed on the document.", dataType = "java.time.ZonedDateTime", example = "2017-01-01T07:54:30.604Z[UTC]")
	private ZonedDateTime date;

	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss.SSSZ")
	@ApiModelProperty(value = "Date when document was receipted at VU.", dataType = "java.time.ZonedDateTime", example = "2017-01-01T07:54:30.604Z[UTC]")
	private ZonedDateTime dateIn;

	@ApiModelProperty(value = "Attribute defining the direction, either the document is inbound, outbound or undefined", example = "INBOUD")
	private InOutboundType inOutBound;

	@ApiModelProperty(value = "The Sender according to document", example = "First name Last name or company, if necessary city")
	private String originalSender;

	@ApiModelProperty(value = "The Recipient according to document.", example = "First name Last name or company, if necessary city")
	private String originalReceiver;

	@NotEmpty
	@ApiModelProperty(value = "A list of classifications.", example = "...", required = true)
	private List<ClassificationResource> classifications;

	@ApiModelProperty(value = "The protection class of the document.", example = "...")
	private int protectionClass;

	@NotEmpty
	@ApiModelProperty(value = "A list relevant partner and their role.", example = "...", required = true)
	private List<RelevantPartner> relevantPartners;

	@ApiModelProperty(value = "The classification type of the document.", example = "...")
	private String businessTransactionId;

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public ZonedDateTime getDate() {
		return date;
	}

	public void setDate(ZonedDateTime date) {
		this.date = date;
	}

	public ZonedDateTime getDateIn() {
		return dateIn;
	}

	public void setDateIn(ZonedDateTime dateIn) {
		this.dateIn = dateIn;
	}

	public InOutboundType getInOutBound() {
		return inOutBound;
	}

	public void setInOutBound(InOutboundType inOutBound) {
		this.inOutBound = inOutBound;
	}

	public String getOriginalSender() {
		return originalSender;
	}

	public void setOriginalSender(String originalSender) {
		this.originalSender = originalSender;
	}

	public String getOriginalReceiver() {
		return originalReceiver;
	}

	public void setOriginalReceiver(String originalReceiver) {
		this.originalReceiver = originalReceiver;
	}

	public List<ClassificationResource> getClassifications() {
		return classifications;
	}

	public void setClassifications(
			List<ClassificationResource> classifications) {
		this.classifications = classifications;
	}

	public int getProtectionClass() {
		return protectionClass;
	}

	public void setProtectionClass(int protectionClass) {
		this.protectionClass = protectionClass;
	}

	public List<RelevantPartner> getRelevantPartners() {
		return relevantPartners;
	}

	public void setRelevantPartners(List<RelevantPartner> relevantPartners) {
		this.relevantPartners = relevantPartners;
	}

	public String getBusinessTransactionId() {
		return businessTransactionId;
	}

	public void setBusinessTransactionId(String businessTransactionId) {
		this.businessTransactionId = businessTransactionId;
	}

}
