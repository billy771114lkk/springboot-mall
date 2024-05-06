package com.chiubilly.springbootmall.dao.impl;

import com.chiubilly.springbootmall.dao.ExchangeRateDao;
import com.chiubilly.springbootmall.model.ExchangeRate;
import com.chiubilly.springbootmall.rowmapper.ExchangeRateRowMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class ExchangeRateDaoImpl implements ExchangeRateDao {

    @Autowired
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;
    @Override
    public ExchangeRate getNewOneExchangeRate() {

        String sql  = "SELECT id,exchange_date,usd_to_ntd,rmb_to_ntd,usd_to_ntd,created_date,last_modified_date " +
                " from exchangerate where 1=1 " +
                " and  id=(select max(id) from exchangerate) ";

        Map<String,Object> map = new HashMap<>();
        ExchangeRate exchangeRate  = namedParameterJdbcTemplate.queryForObject(sql,map,ExchangeRate.class);

        return exchangeRate;
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
