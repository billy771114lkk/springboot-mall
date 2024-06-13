package com.chiubilly.springbootmall.dao.impl;

import com.chiubilly.springbootmall.dao.ExchangeRateDao;
import com.chiubilly.springbootmall.dto.ExchangeRateQueryParams;
import com.chiubilly.springbootmall.dto.ExchangeRateRequest;
import com.chiubilly.springbootmall.model.ExchangeRate;
import com.chiubilly.springbootmall.rowmapper.ExchangeRateRowMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Component;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;
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

    //tempStop
    public List<ExchangeRate> getNewListExchangeRate(ExchangeRateRequest exchangeRateRequest){
        String sql  = "SELECT id,exchange_date,usd_to_ntd,rmb_to_ntd,usd_to_ntd,created_date,last_modified_date " +
                " from exchangerate where 1=1 ";

        return null;
    }

    @Override
    public ExchangeRate getNewOneExchangeRateByDate(ExchangeRateRequest exchangeRateRequest) {

        String sql  = "SELECT id,exchange_date,usd_to_ntd,rmb_to_ntd,usd_to_rmb,created_date,last_modified_date " +
                " from exchangerate where 1=1 " +
                " and  exchange_date=:exchangeDate ";


        System.out.println(sql);
        Map<String,Object> map = new HashMap<>();
         try{
             System.out.println(new SimpleDateFormat("yyyyMMdd").parse(exchangeRateRequest.getDate()));
             map.put("exchangeDate", new SimpleDateFormat("yyyyMMdd").parse(exchangeRateRequest.getDate()));
         }catch(Exception e){
             e.printStackTrace();
         }

        ExchangeRate exchangeRate;
        try {
             List<ExchangeRate> exchangeRateList = namedParameterJdbcTemplate.query(sql, map, new ExchangeRateRowMapper());
            exchangeRate = exchangeRateList.get(0);
        }catch (Exception e){
            //e.printStackTrace();
            exchangeRate=null;
        }

        return exchangeRate;
    }


    @Override
    public Integer saveExchangeRate(ExchangeRateRequest exchangeRateRequest)  {
        String sql = "INSERT INTO exchangerate(exchange_date,usd_to_ntd,rmb_to_ntd,usd_to_rmb,created_date,last_modified_date)  " +
                "VALUES(:exchangeDate,:usdToNtd,:rmbToNtd,:usdToRmb,:createdDate,:lastModifiedDate)";

        Map<String,Object> map = new HashMap<>();
        try {
            map.put("exchangeDate", new SimpleDateFormat("yyyyMMdd").parse(exchangeRateRequest.getDate()));
        }catch (Exception e){
            e.printStackTrace();
        }
            map.put("usdToNtd",exchangeRateRequest.getUsdToNtd());
            map.put("rmbToNtd",exchangeRateRequest.getRmbToNtd());
            map.put("usdToRmb",exchangeRateRequest.getUsdToRmb());

        Date now=new Date();
            map.put("createdDate",now);
            map.put("lastModifiedDate",now);

        KeyHolder keyHolder = new GeneratedKeyHolder();
        namedParameterJdbcTemplate.update(sql, new MapSqlParameterSource(map),keyHolder);

        return keyHolder.getKey().intValue();

    }

    @Override
    public Integer deleteExchangeRate(ExchangeRateRequest exchangeRateRequest) {

        String sql = " DELETE FROM exchangerate " +
                " WHERE exchange_date = :exchangeDate ";

        Map<String,Object> map = new HashMap<>();
        try {
            map.put("exchangeDate", new SimpleDateFormat("yyyyMMdd").parse(exchangeRateRequest.getDate()));
        }catch (Exception e){
            e.printStackTrace();
        }

        KeyHolder keyHolder = new GeneratedKeyHolder();
        namedParameterJdbcTemplate.update(sql, new MapSqlParameterSource(map),keyHolder);

        //return keyHolder.getKey().intValue();
        return 1;
    }

    @Override
    public Integer updateExchangeRate(ExchangeRateRequest exchangeRateRequest) {
        String sql = "UPDATE exchangerate set  usd_to_ntd=:usdToNtd , rmb_to_ntd=:rmbToNtd , usd_to_rmb=:usdToRmb  last_modified_date=:lastModifiedDate " +
                " WHERE exchange_date=:exchangeDate ";

        Map<String,Object> map = new HashMap<>();
        try {
            map.put("exchangeDate",        new SimpleDateFormat("yyyyMMdd").parse(exchangeRateRequest.getDate())            );
        }catch (Exception e){
            e.printStackTrace();
        }
        map.put("usdToNtd",exchangeRateRequest.getUsdToNtd());
        map.put("rmbToNtd",exchangeRateRequest.getRmbToNtd());
        map.put("usdToRmb",exchangeRateRequest.getUsdToRmb());

        Date now=new Date();
        map.put("lastModifiedDate",now);

        KeyHolder keyHolder = new GeneratedKeyHolder();
        namedParameterJdbcTemplate.update(sql, new MapSqlParameterSource(map),keyHolder);

        return keyHolder.getKey().intValue();
    }
}
