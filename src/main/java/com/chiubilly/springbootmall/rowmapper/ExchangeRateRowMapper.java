package com.chiubilly.springbootmall.rowmapper;

import com.chiubilly.springbootmall.model.ExchangeRate;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ExchangeRateRowMapper implements RowMapper<ExchangeRate> {
    @Override
    public ExchangeRate mapRow(ResultSet resultSet, int rowNum) throws SQLException {
        ExchangeRate exchangeRate = new ExchangeRate();

        exchangeRate.setId(resultSet.getInt("id"));
        exchangeRate.setExchangeDate(resultSet.getDate("exchange_date"));
        exchangeRate.setUsdToNtd(resultSet.getDouble("usd_to_ntd"));
        exchangeRate.setRmbToNtd(resultSet.getDouble("rmb_to_ntd"));
        exchangeRate.setUsdToRmb(resultSet.getDouble("usd_to_rmb"));
        exchangeRate.setCreatedDate(resultSet.getTimestamp("created_date"));
        exchangeRate.setLastModifiedDate(resultSet.getTimestamp("last_modified_date"));


        return exchangeRate;
    }
}
