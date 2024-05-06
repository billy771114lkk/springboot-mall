package com.chiubilly.springbootmall.service.impl;

import com.chiubilly.springbootmall.dao.ExchangeRateDao;
import com.chiubilly.springbootmall.model.ExchangeRate;
import com.chiubilly.springbootmall.service.ExchangeRateService;
import org.springframework.stereotype.Component;

@Component
public class ExchangeRateServiceImpl implements ExchangeRateService {
    private ExchangeRateDao exchangeRateDao;


    @Override
    public ExchangeRate getExchangeRate() {
//        exchangeRateDao.getNewOneExchangeRate();
        return null;
    }

    @Override
    public void saveExchangeRate() {

    }

    @Override
    public void updateExchangeRate() {

    }

    @Override
    public void deleteExchangeRate() {

    }
}
