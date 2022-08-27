package com.digitalumbrella.task.service.impl;

import com.digitalumbrella.task.domain.entity.ExchangeInfo;
import com.digitalumbrella.task.model.ValCurs;
import com.digitalumbrella.task.model.ValType;
import com.digitalumbrella.task.model.Valute;
import com.digitalumbrella.task.repository.ExchangeRepository;
import com.digitalumbrella.task.rest.model.dto.ExchangeInfoDTO;
import com.digitalumbrella.task.rest.model.dto.ExchangeInfoDtoConverter;
import com.digitalumbrella.task.service.ExchangeRateService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.stream.Collectors;

@Service

public class ExchangeRateServiceImpl implements ExchangeRateService {

    private final String baseUrl;

    private final RestTemplate restTemplate;

    private final ExchangeRepository exchangeRepository;

    private final ExchangeInfoDtoConverter exchangeInfoDtoConverter;

    public ExchangeRateServiceImpl(@Value("exchange-rate.baseurl") String baseUrl, RestTemplate restTemplate, ExchangeRepository exchangeRepository, ExchangeInfoDtoConverter exchangeInfoDtoConverter) {
        this.baseUrl = baseUrl;
        this.restTemplate = restTemplate;
        this.exchangeRepository = exchangeRepository;
        this.exchangeInfoDtoConverter = exchangeInfoDtoConverter;
    }

    @Override
    public List<ExchangeInfoDTO> findExchangeInfoByDate(String date) {
        List<ExchangeInfo> list = exchangeRepository.findExchangeInfoByDate(date);
        return exchangeInfoDtoConverter.convert(list);
    }

    @Override
    public void deleteAllByDate(String date) {
        exchangeRepository.deleteAllByDate(date);
    }


    @Override
    public List<ExchangeInfoDTO> findExchangeInfoByValCode(String code) {
        List<ExchangeInfo> list = exchangeRepository.findExchangeInfoByValCode(code);
        return exchangeInfoDtoConverter.convert(list);
    }

    @Override
    public List<ExchangeInfoDTO> findByDateAndCurrencyCode(String date, String code) {
        List<ExchangeInfo> list = exchangeRepository.findByDateAndCurrencyCode(date, code);
        return exchangeInfoDtoConverter.convert(list);
    }

    @Override
    public void saveExchangeInfo(String date) {
        if (!exchangeRepository.findExchangeInfoByDate(date).isEmpty())
            throw new IllegalStateException("Data was already saved for specified date");

        ValCurs valCurs = restTemplate.getForObject(baseUrl + date + ".xml", ValCurs.class);
        List<ExchangeInfo> exchangeInfo =
                valCurs.getValType().stream().flatMap(
                        val ->
                                val.getValutes().stream().map(
                                        valute ->
                                                createExchangeInfo(date, val, valute)
                                )
                ).collect(Collectors.toList());

        exchangeRepository.saveAll(exchangeInfo);
    }

    private ExchangeInfo createExchangeInfo(String date, ValType val, Valute valute) {
        return ExchangeInfo.builder()
                .valType(val.getType())
                .date(date)
                .name(valute.getName())
                .currValue(valute.getValue())
                .valCode(valute.getCode())
                .nominal(valute.getNominal())
                .build();
    }


}
