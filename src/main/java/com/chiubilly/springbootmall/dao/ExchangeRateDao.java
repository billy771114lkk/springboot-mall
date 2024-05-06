package com.chiubilly.springbootmall.dao;

import com.chiubilly.springbootmall.model.ExchangeRate;

public interface ExchangeRateDao {
     ExchangeRate getNewOneExchangeRate();
     void saveExchangeRate();
     void updateExchangeRate();
     void deleteExchangeRate();
}
