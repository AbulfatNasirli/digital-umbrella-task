//package com.digitalumbrella.task.service.impl;
//
//import com.digitalumbrella.task.repository.ExchangeRepository;
//import com.digitalumbrella.task.rest.model.dto.ExchangeInfoDtoConverter;
//import org.junit.Before;
//import org.junit.Test;
//import org.junit.jupiter.api.BeforeEach;
//import org.mockito.Mockito;
//import org.springframework.web.client.RestTemplate;
//
//import static org.junit.Assert.*;
//
//public class ExchangeRateServiceTest {
//
//    private ExchangeRateServiceImpl exchangeRateService;
//
//    private  String apiKey;
//
//    private RestTemplate restTemplate;
//
//    private ExchangeRepository exchangeRepository;
//
//    private ExchangeInfoDtoConverter exchangeInfoDtoConverter;
//
//    @BeforeEach
//    public void setUp() throws Exception {
//        exchangeRepository = Mockito.mock(ExchangeRepository.class);
//        exchangeInfoDtoConverter = Mockito.mock(ExchangeInfoDtoConverter.class);
//        restTemplate = Mockito.mock(RestTemplate.class);
//        ExchangeRateServiceImpl exchangeRateService1 = new ExchangeRateServiceImpl(apiKey,restTemplate,exchangeRepository,exchangeInfoDtoConverter);
//    }
//
//    @Test
//    public void findExchangeInfoByDate_whenExchangeRateExist_itShouldReturnExchangeInfoDto(){
//
//
//
//    }
//
//}