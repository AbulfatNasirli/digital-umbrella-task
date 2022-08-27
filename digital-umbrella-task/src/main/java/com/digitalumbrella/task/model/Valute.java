package com.digitalumbrella.task.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class Valute {
    @JsonProperty(value = "Nominal")
    private String nominal;
    @JsonProperty(value = "Name")
    private String name;
    @JsonProperty(value = "Value")
    private String value;

    @JsonProperty(value = "Code")
    private String code;


}
