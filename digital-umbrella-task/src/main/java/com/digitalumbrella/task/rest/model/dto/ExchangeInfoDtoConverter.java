package com.digitalumbrella.task.rest.model.dto;

import com.digitalumbrella.task.domain.entity.ExchangeInfo;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class ExchangeInfoDtoConverter {
    public  ExchangeInfoDTO convert(ExchangeInfo exchangeInfo) {
        return new ExchangeInfoDTO(exchangeInfo.getName(),
                exchangeInfo.getCurrValue(),
                exchangeInfo.getNominal(),
                exchangeInfo.getValType(),
                exchangeInfo.getValCode(),
                exchangeInfo.getDate());
    }

    public  List<ExchangeInfoDTO> convert(List<ExchangeInfo> fromList) {
        return fromList.stream().map(from -> new ExchangeInfoDTO(from.getName(),
                from.getCurrValue(),
                from.getNominal(),
                from.getValType(),
                from.getValCode(),
                from.getDate())).collect(Collectors.toList());
    }

}
