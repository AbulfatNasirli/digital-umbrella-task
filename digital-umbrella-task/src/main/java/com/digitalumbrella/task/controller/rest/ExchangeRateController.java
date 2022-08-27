package com.digitalumbrella.task.controller.rest;

import com.digitalumbrella.task.rest.model.dto.ExchangeInfoDTO;
import com.digitalumbrella.task.service.ExchangeRateService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.server.ResponseStatusException;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RequestMapping(produces = "application/json", value = "api/v1")
@CrossOrigin
@RestController
public class ExchangeRateController {

    private final ExchangeRateService exchangeRateService;

    private static final String API_KEY = "X-API-Key";

    private final String apiKey;

    public ExchangeRateController(ExchangeRateService exchangeRateService, @Value("api.key") String apiKey) {
        this.exchangeRateService = exchangeRateService;
        this.apiKey = apiKey;
    }


    //-	Azərbaycan manatının seçilmiş tarixdə bütün xarici valyutalara qarşı məzənnələrinin əldə olunması;
    @GetMapping("/exchange-rates/{date}")
    public List<ExchangeInfoDTO> getInfo(@PathVariable String date) {
        return exchangeRateService.findExchangeInfoByDate(date);
    }

    //Seçilmiş tarix üçün yadda saxlanılmış məzənnələrin silinməsi;
    @DeleteMapping("exchange-rates/{date}")
    @Transactional
    public void deleteInfo(@PathVariable String date) {
        exchangeRateService.deleteAllByDate(date);
    }

    /*
Seçilmiş tarix üçün məzənnələrin Azərbaycan Respublikası Mərkəzi Bankının məzənnə bülletenindən yüklənərək verilənlər bazasında saxlanılması;
Seçilmiş tarix üçün məzənnələr artıq verilənlər bazasında mövcuddursa, o zaman, heç bir dəyişiklik baş vermir və bu barədə uyğun cavab geri qaytarılır.
     */
    @PostMapping("exchange-rates/{date}")
    public ResponseEntity<String> saveExchangeInfo(@PathVariable String date) {
        String authorizationHeader = getAuthorizationHeader();
        if (authorizationHeader == null) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "No key provided");
        }

        if (!apiKey.equals(authorizationHeader)) {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "Invalid api key");
        }
        try {
            exchangeRateService.saveExchangeInfo(date);
        } catch (IllegalStateException exception) {
            return ResponseEntity.ok(exception.getMessage());
        }
        return ResponseEntity.ok("Exchange information saved successfully");
    }

    //Azərbaycan manatının seçilmiş tarixdə və seçilmiş valyutaya qarşı məzənnəsinin əldə olunması;
    @GetMapping("exchange-rates/azn/{target-curr}/{date}")
    public List<ExchangeInfoDTO> convert(@PathVariable String date, @PathVariable("target-curr") String curr) {
        return exchangeRateService.findByDateAndCurrencyCode(date, curr);
    }

    //Azərbaycan manatının seçilmiş xarici valyutaya qarşı bütün tarixlərdəki məzənnələrinin əldə olunması.
    @GetMapping("api/v1/azn/{target-curr}")
    public List<ExchangeInfoDTO> convert(@PathVariable("target-curr") String curr) {
        return exchangeRateService.findExchangeInfoByValCode(curr);
    }

    private String getAuthorizationHeader() {
        ServletRequestAttributes requestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        Assert.notNull(requestAttributes, "Request attributes must not be null");
        HttpServletRequest request = requestAttributes.getRequest();
        return request.getHeader(API_KEY);
    }

}

