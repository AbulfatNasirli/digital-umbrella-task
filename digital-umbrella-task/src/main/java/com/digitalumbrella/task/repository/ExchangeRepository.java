package com.digitalumbrella.task.repository;


import com.digitalumbrella.task.domain.entity.ExchangeInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ExchangeRepository extends JpaRepository<ExchangeInfo, Long> {

    List<ExchangeInfo> findExchangeInfoByDate(String date);

    void deleteAllByDate(String date);

    List<ExchangeInfo> findExchangeInfoByValCode(String code);

    @Query("SELECT ei FROM ExchangeInfo ei WHERE ei.date= ?1 and ei.valCode= ?2")
    List<ExchangeInfo> findByDateAndCurrencyCode(String date, String code);

}
