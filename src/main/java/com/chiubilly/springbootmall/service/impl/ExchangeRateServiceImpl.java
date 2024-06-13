package com.chiubilly.springbootmall.service.impl;

import com.chiubilly.springbootmall.dao.ExchangeRateDao;
import com.chiubilly.springbootmall.dto.ExchangeRateRequest;
import com.chiubilly.springbootmall.model.ExchangeRate;
import com.chiubilly.springbootmall.service.ExchangeRateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
public class ExchangeRateServiceImpl implements ExchangeRateService {

    @Autowired
    private ExchangeRateDao exchangeRateDao;

    @Override
    public ExchangeRate getExchangeRate() {
//        exchangeRateDao.getNewOneExchangeRate();

        return null;
    }

    @Transactional
    @Override
    public Integer saveExchangeRate(ExchangeRateRequest exchangeRateRequest) {

        ExchangeRate exchangeRate = exchangeRateDao.getNewOneExchangeRateByDate(exchangeRateRequest);
        System.out.println(exchangeRate==null);

            if(exchangeRate==null) {
                exchangeRateDao.saveExchangeRate(exchangeRateRequest);
                return 1;
            } else{
                return 0 ;
            }
    }


    @Transactional
    @Override
    public Integer updateExchangeRate(ExchangeRateRequest exchangeRateRequest) {
        if(exchangeRateDao.getNewOneExchangeRateByDate(exchangeRateRequest)!=null)
            return  exchangeRateDao.updateExchangeRate(exchangeRateRequest);
        else
            return 0;
    }

    @Transactional
    @Override
    public Integer deleteExchangeRate(ExchangeRateRequest exchangeRateRequest) {
        return exchangeRateDao.deleteExchangeRate(exchangeRateRequest);
    }
}
