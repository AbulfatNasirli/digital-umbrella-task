package com.digitalumbrella.task.rest.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ExchangeInfoDTO {

    private String name;
    private String currValue;
    private String nominal;
    private String valType;
    private String valCode;
    private String date;
}
