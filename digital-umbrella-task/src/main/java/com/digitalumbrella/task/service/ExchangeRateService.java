package com.digitalumbrella.task.service;
import com.digitalumbrella.task.rest.model.dto.ExchangeInfoDTO;

import java.util.List;


public interface ExchangeRateService {

    List<ExchangeInfoDTO> findExchangeInfoByDate(String date);

    void deleteAllByDate(String date);

    List<ExchangeInfoDTO> findExchangeInfoByValCode(String code);

    List<ExchangeInfoDTO> findByDateAndCurrencyCode(String date, String code);

     void  saveExchangeInfo(String date);
}
