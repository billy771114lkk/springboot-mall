package com.chiubilly.springbootmall.service;

import com.chiubilly.springbootmall.dto.ExchangeRateRequest;
import com.chiubilly.springbootmall.model.ExchangeRate;

public interface ExchangeRateService {


    ExchangeRate getExchangeRate();

    Integer saveExchangeRate(ExchangeRateRequest exchangeRateRequest);

    Integer updateExchangeRate(ExchangeRateRequest exchangeRateRequest);

    Integer deleteExchangeRate(ExchangeRateRequest exchangeRateRequest);

}
