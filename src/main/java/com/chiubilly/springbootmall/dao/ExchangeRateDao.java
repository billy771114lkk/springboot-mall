package com.chiubilly.springbootmall.dao;

import com.chiubilly.springbootmall.dto.ExchangeRateRequest;
import com.chiubilly.springbootmall.model.ExchangeRate;

import java.util.List;

public interface ExchangeRateDao {
     ExchangeRate getNewOneExchangeRate();
     ExchangeRate getNewOneExchangeRateByDate(ExchangeRateRequest exchangeRateRequest);
     public List<ExchangeRate> getNewListExchangeRate(ExchangeRateRequest exchangeRateRequest);
     Integer saveExchangeRate(ExchangeRateRequest exchangeRateRequest);
     Integer updateExchangeRate(ExchangeRateRequest exchangeRateRequest);
     Integer deleteExchangeRate(ExchangeRateRequest exchangeRateRequest);
}
