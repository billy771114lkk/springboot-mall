package com.chiubilly.springbootmall.controller;

import com.chiubilly.springbootmall.dto.ExchangeRateRequest;
import com.chiubilly.springbootmall.service.ExchangeRateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;

@Controller
public class ExchangeRateController {

    private final RestTemplate restTemplate = new RestTemplate();
    //@Autowired

    @Autowired
    private ExchangeRateService exchangeRateService;

    //@RequestMapping("/exchange")

    @GetMapping("/exchange")
    public String getExchangeRate(Model model){
        //需要converter轉換的METHOD
        restTemplate.getMessageConverters().add(jacksonSupportsMoreTypes());

        ResponseEntity<ExchangeRateRequest[]> responseEntity
                = restTemplate.getForEntity("https://openapi.taifex.com.tw/v1/DailyForeignExchangeRates", ExchangeRateRequest[].class);

        ExchangeRateRequest[] exchangeRateRequests=responseEntity.getBody();
        int todayRate = exchangeRateRequests.length;

        ExchangeRateRequest exchangeRateRequest= new ExchangeRateRequest();

        //取最新一天
        exchangeRateRequest.setDate(exchangeRateRequests[todayRate-1].getDate());
        exchangeRateRequest.setUsdToNtd(exchangeRateRequests[todayRate-1].getUsdToNtd());
        exchangeRateRequest.setRmbToNtd(exchangeRateRequests[todayRate-1].getRmbToNtd());
        exchangeRateRequest.setUsdToRmb(exchangeRateRequests[todayRate-1].getUsdToRmb());

        model.addAttribute(exchangeRateRequest);

        return "index";
    }


    //存入DB該日匯率
    @PostMapping("/exchange/save")
    public String saveExchangeRate( @RequestParam("date") String date
                                                                ,@RequestParam("usd_to_ntd") String usdToNtd
                                                                ,@RequestParam("rmb_to_ntd") String rmbToNtd
                                                                ,@RequestParam("usd_to_rmb") String usdToRmb){


        ExchangeRateRequest exchangeRateRequest = new ExchangeRateRequest();

        exchangeRateRequest.setDate(date);
        exchangeRateRequest.setUsdToNtd(usdToNtd);
        exchangeRateRequest.setRmbToNtd(rmbToNtd);
        exchangeRateRequest.setUsdToRmb(usdToRmb);

       int myFlag = exchangeRateService.saveExchangeRate(exchangeRateRequest);
        System.out.println(myFlag);
       if(myFlag!=0)
           return "successPage";
       else
           return "failPage";
    }


    //@PutMapping("/exchange/update")
    @PostMapping("/exchange/update")
    public String updateExchangeRate(@RequestParam("date") String date
            ,@RequestParam("usd_to_ntd") String usdToNtd
            ,@RequestParam("rmb_to_ntd") String rmbToNtd
            ,@RequestParam("usd_to_rmb") String usdToRmb){

        ExchangeRateRequest exchangeRateRequest = new ExchangeRateRequest();

        exchangeRateRequest.setDate(date);
        exchangeRateRequest.setUsdToNtd(usdToNtd);
        exchangeRateRequest.setRmbToNtd(rmbToNtd);
        exchangeRateRequest.setUsdToRmb(usdToRmb);

        int myFlag = exchangeRateService.updateExchangeRate(exchangeRateRequest);

        if(myFlag!=0)
            return "successPage";
        else
            return "failPage";
    }

    @GetMapping("/exchange/get")
    public  String getExchageRate(){
//        exchangeRateService.getExchangeRate();
        return "getExchange";
    }

//    @DeleteMapping("/exchange/del")
   @GetMapping("/exchange/del")
    public String deleteExchangeRate(@RequestParam("date") String date){

        ExchangeRateRequest exchangeRateRequest = new ExchangeRateRequest();

        exchangeRateRequest.setDate(date);

       exchangeRateService.deleteExchangeRate(exchangeRateRequest);
        return "successPage";
    }




    //Converter for 匯率API OCTET STREAM
    private HttpMessageConverter jacksonSupportsMoreTypes() {//eg. Gitlab returns JSON as plain text
        MappingJackson2HttpMessageConverter converter = new MappingJackson2HttpMessageConverter();
        converter.setSupportedMediaTypes(Arrays.asList(MediaType.parseMediaType("text/plain;charset=utf-8"), MediaType.APPLICATION_OCTET_STREAM));
        return converter;
    }

}
