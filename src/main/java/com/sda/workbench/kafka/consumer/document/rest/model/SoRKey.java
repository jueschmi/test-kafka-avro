package com.sda.workbench.kafka.consumer.document.rest.model;

import java.util.List;

import io.swagger.annotations.ApiModelProperty;

public class SoRKey {
	@ApiModelProperty(value = "List of SoRKey elements.", example = "...")
	private List<SorKeyElement> sorKeyElements;

	@ApiModelProperty(value = "List of SoRKey resource key elements.", example = "...")
	private List<String> sorResourceKeys;

	@ApiModelProperty(value = "List of SoRKey other identifier elements.", example = "...")
	private List<String> otherIdentifiers;

	public SoRKey(List<SorKeyElement> sorKeyElements) {
		this.sorKeyElements = sorKeyElements;
	}

	public List<SorKeyElement> getSorKeyElements() {
		return sorKeyElements;
	}

	public void setSorKeyElements(List<SorKeyElement> sorKeyElements) {
		this.sorKeyElements = sorKeyElements;
	}

	public List<String> getSorResourceKeys() {
		return sorResourceKeys;
	}

	public void setSorResourceKeys(List<String> sorResourceKeys) {
		this.sorResourceKeys = sorResourceKeys;
	}

	public List<String> getOtherIdentifiers() {
		return otherIdentifiers;
	}

	public void setOtherIdentifiers(List<String> otherIdentifiers) {
		this.otherIdentifiers = otherIdentifiers;
	}
}
