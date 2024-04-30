package com.chiubilly.springbootmall.controller;

import com.chiubilly.springbootmall.dto.ExchangeRateRequest;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;

@RestController
public class ExchangeRateController {

    private final RestTemplate restTemplate = new RestTemplate();
    //@Autowired
    //private ExchangeRateService exchangeRateService;

    @RequestMapping("/exchange")
    public String getExchangeRate(HttpServletRequest httpServletRequest){
        //需要converter轉換的METHOD
        restTemplate.getMessageConverters().add(jacksonSupportsMoreTypes());

        ResponseEntity<ExchangeRateRequest[]> responseEntity
                = restTemplate.getForEntity("https://openapi.taifex.com.tw/v1/DailyForeignExchangeRates", ExchangeRateRequest[].class);

        ExchangeRateRequest[] exchangeRateRequests=responseEntity.getBody();
        int todayRate = exchangeRateRequests.length;

        System.out.println(exchangeRateRequests[todayRate-1].getDate());
        System.out.println(exchangeRateRequests[todayRate-1].getUsdToNtd());
        System.out.println(exchangeRateRequests[todayRate-1].getRmbToNtd());
        System.out.println(exchangeRateRequests[todayRate-1].getUsdToRmb());

        return "tempString";
    }


    private HttpMessageConverter jacksonSupportsMoreTypes() {//eg. Gitlab returns JSON as plain text
        MappingJackson2HttpMessageConverter converter = new MappingJackson2HttpMessageConverter();
        converter.setSupportedMediaTypes(Arrays.asList(MediaType.parseMediaType("text/plain;charset=utf-8"), MediaType.APPLICATION_OCTET_STREAM));
        return converter;
    }

}
