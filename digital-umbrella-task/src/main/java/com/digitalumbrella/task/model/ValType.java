package com.digitalumbrella.task.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import lombok.Data;

import java.util.List;

@Data
public class ValType {

    @JsonProperty(value = "Type")
    private String type;

    @JsonProperty(value = "Valute")
    @JacksonXmlElementWrapper(useWrapping = false)
    private List<Valute> valutes;
}
