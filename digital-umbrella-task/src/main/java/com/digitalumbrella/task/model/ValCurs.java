package com.digitalumbrella.task.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import lombok.Data;

import java.util.List;

@Data
public class ValCurs {

    @JsonProperty(value = "ValType")
    @JacksonXmlElementWrapper(useWrapping = false)
    private List<ValType> valType;

}
