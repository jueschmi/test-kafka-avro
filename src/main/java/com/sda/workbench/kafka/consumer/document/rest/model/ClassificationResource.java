package com.sda.workbench.kafka.consumer.document.rest.model;

import io.swagger.annotations.ApiModelProperty;

public class ClassificationResource {
  @ApiModelProperty(value = "Defining argument of the business object the document belongs to", example = "...")
  private String id;

  @ApiModelProperty(value = "The type of the business object the document belongs to.", example = "...")
  private String type;

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public String getType() {
    return type;
  }

  public void setType(String type) {
    this.type = type;
  }
}
