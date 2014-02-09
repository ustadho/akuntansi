/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.safira.akunting.ui.controller;

import com.safira.akunting.domain.acc.Currency;
import com.safira.akunting.service.MasterRestfulService;
import java.net.URI;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.util.UriTemplate;

/**
 *
 * @author faheem
 */
@Controller
public class AccCurrencyController {
    @Autowired
    private MasterRestfulService masterService;
    private Logger logger=LoggerFactory.getLogger(this.getClass());
    
    @RequestMapping(value="currency/{id}", method = RequestMethod.GET)
    @ResponseBody
    public Currency findById(@PathVariable String id){
        Currency curr=masterService.findById(id);
        if(curr ==null){
            throw new IllegalStateException();
        }
        return curr;
    }
    
    @RequestMapping(value="currency")
    @ResponseBody
    public List<Currency> findAll(HttpServletResponse response){
       List<Currency> hasil=masterService.findAllCurrency();
       return hasil;
    }
    
    @RequestMapping(value = "/currency", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    public void create(@RequestBody @Valid Currency x, HttpServletRequest request, HttpServletResponse response){
        masterService.save(x);
        String requestUrl=request.getRequestURL().toString();
        URI uri=new UriTemplate("{requestUrl}/{id}").expand(requestUrl, x.getId());
        response.setHeader("Location", uri.toASCIIString());
    }
    
    @RequestMapping(method = RequestMethod.PUT, value="currency/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void update(@PathVariable String id, @RequestBody @Valid Currency c){
        Currency curr=masterService.findById(id);
        if(curr==null){
            logger.warn("Currency dengan id [{}] tidak ditemukan", id);
        }
        c.setId(curr.getId());
        masterService.save(c);
    }
    
    public void delete(@PathVariable String id){
        Currency a=masterService.findById(id);
        if(a==null){
            logger.warn("Currency dengan id [{}] tidak ditemukan", id);
            throw new IllegalStateException();
        }
        masterService.delete(a);
    }
    
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler({IllegalStateException.class})
    public void handle(){
        logger.debug("Resource dengan URI tersebut tidak ditemukan");
    }
}
