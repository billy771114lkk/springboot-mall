package com.chiubilly.springbootmall.service;

import com.chiubilly.springbootmall.model.ExchangeRate;

public interface ExchangeRateService {


    ExchangeRate getExchangeRate();

    void saveExchangeRate();

    void updateExchangeRate();

    void deleteExchangeRate();

}
