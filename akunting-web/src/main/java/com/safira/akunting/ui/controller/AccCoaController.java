/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.safira.akunting.ui.controller;

import com.fasterxml.jackson.annotation.JsonUnwrapped;
import com.safira.akunting.domain.acc.Coa;
import com.safira.akunting.service.MasterRestfulService;
import java.net.URI;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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
public class AccCoaController {
    @Autowired
    MasterRestfulService masterRestfulService;
    private Logger logger = LoggerFactory.getLogger(this.getClass());
    
    
    @RequestMapping(value = "/coa", method = RequestMethod.GET)
    @ResponseBody
    public Page<Coa> findAll(
            Pageable pageable,
            HttpServletResponse response) {
        System.out.println("Test param:"+ pageable);
       Page<Coa> hasil = masterRestfulService.findAllCoa(pageable);

        return hasil;

    }
    @RequestMapping(value="/coa/{accNo}/edit", method = RequestMethod.GET)
    @ResponseBody
    public Coa findById(@PathVariable String accNo, HttpServletResponse response) {
        System.out.println("Masuk findById");
        Coa x = masterRestfulService.findByAccNo(accNo);
        if (x == null) {
            throw new IllegalStateException();
        }
        return x;
    }
    
    @RequestMapping(value = "/coa/{search}", method = RequestMethod.GET)
    @ResponseBody
    public Page<Coa> searchCoa(@PathVariable String search,
            Pageable pageable,
            HttpServletResponse response) {
        System.out.println("Test param:"+ pageable.toString());
        Page<Coa> hasil = masterRestfulService.findCoas("%"+search+"%", pageable);

        return hasil;

    }
    
    @RequestMapping(value = "/coa", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    public void create(@RequestBody @Valid Coa x, HttpServletRequest request, HttpServletResponse response) {
        masterRestfulService.save(x);
        String requestUrl = request.getRequestURL().toString();
        URI uri = new UriTemplate("{requestUrl}/{accNo}").expand(requestUrl, x.getAccNo());
        response.setHeader("Location", uri.toASCIIString());
    }
    
    @RequestMapping(method = RequestMethod.PUT, value = "/coa/{accNo}")
    @ResponseStatus(HttpStatus.OK)
    public void update(@PathVariable String accNo, @RequestBody @Valid Coa x) {
        Coa a = masterRestfulService.findByAccNo(accNo);
        if (a == null) {
            logger.warn("Akun dengan nomor [{}] tidak ditemukan", accNo);
            throw new IllegalStateException();
        }
        x.setAccNo(a.getAccNo());
        masterRestfulService.save(x);
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "/coa/{accNo}")
    @ResponseStatus(HttpStatus.OK)
    public void delete(@PathVariable String accNo) {
        System.out.println("Masuk: /coa/{accNo}");
        Coa a = masterRestfulService.findByAccNo(accNo);
        if (a == null) {
            logger.warn("Coa dengan accNo [{}] tidak ditemukan", accNo);
            throw new IllegalStateException();
        }
        masterRestfulService.delete(a);
    }

    
    
    @JsonUnwrapped
    @RequestMapping("/coa/all")
    @ResponseBody
    public List<Coa> findAllCoaList() {
        List<Coa> hasil=masterRestfulService.listAllCoa();
//        Hibernate.initialize(hasil);
//        for(int i=0; i<hasil.size(); i++){
//            Hibernate.initialize(hasil.get(i).getDaftarJurnal());
//        }
        return hasil;
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler({IllegalStateException.class})
    public void handle() {
        logger.debug("Resource dengan URI tersebut tidak ditemukan");
    }
    
}
