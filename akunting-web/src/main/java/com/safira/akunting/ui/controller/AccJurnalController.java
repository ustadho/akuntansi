package com.safira.akunting.ui.controller;

import com.safira.akunting.domain.AccCoa;
import com.safira.akunting.domain.AccJurnal;
import com.safira.akunting.domain.AccJurnalDetail;
import com.safira.akunting.domain.User;
import com.safira.akunting.service.AccJurnalRestfulService;
import java.net.URI;
import java.util.Date;
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

@Controller
public class AccJurnalController {

    @Autowired
    private AccJurnalRestfulService jurnalRestfulService;
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @RequestMapping("/jurnal/{id}")
    @ResponseBody
    public AccJurnal findById(@PathVariable Integer id) {
        AccJurnal x = jurnalRestfulService.findJurnalById(id);
        if (x == null) {
            throw new IllegalStateException();
        }
        x.setListJurnal(null);
        fixLie(x.getUser());
        
        return x;
    }
    
    @RequestMapping(value = "/jurnal", method = RequestMethod.GET)
    @ResponseBody
    public Page<AccJurnal> findAll(Pageable pageable) {
        Page<AccJurnal> x = jurnalRestfulService.findAllJournal(pageable);
        for(AccJurnal j : x){
            j.setListJurnal(null);
            fixLie(j.getUser());
        }
        return x;
    }
    
    @RequestMapping("/jurnal-filter/{mulai}/{sampai}")
    @ResponseBody
    public List<AccJurnal> filterJurnalPerTanggal(
            @PathVariable Date mulai, @PathVariable Date sampai, Pageable pageable) {
        List<AccJurnal> x = jurnalRestfulService.filterJurnalPerTanggal(mulai, sampai, pageable);
        if (x == null) {
            throw new IllegalStateException();
        }
        for(AccJurnal j : x){
            j.setListJurnal(null);
            fixLie(j.getUser());
        }
        return x;
    }

    @RequestMapping(value = "/jurnal", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    public void create(@RequestBody @Valid AccJurnal x, HttpServletRequest request, HttpServletResponse response) {
        jurnalRestfulService.save(x);
        String requestUrl = request.getRequestURL().toString();
        URI uri = new UriTemplate("{requestUrl}/{id}").expand(requestUrl, x.getId());
        response.setHeader("Location", uri.toASCIIString());
    }
    

    @RequestMapping(method = RequestMethod.PUT, value = "/jurnal/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void update(@PathVariable Integer id, @RequestBody @Valid AccJurnal x) {
        AccJurnal a = jurnalRestfulService.findJurnalById(id);
        if (a == null) {
            logger.warn("Role dengan id [{}] tidak ditemukan", id);
            throw new IllegalStateException();
        }
        x.setId(a.getId());
        jurnalRestfulService.save(x);
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "/jurnal/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void delete(@PathVariable Integer id) {
        AccJurnal a = jurnalRestfulService.findJurnalById(id);
        if (a == null) {
            logger.warn("Role dengan id [{}] tidak ditemukan", id);
            throw new IllegalStateException();
        }
        jurnalRestfulService.delete(a);
    }


    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler({IllegalStateException.class})
    public void handle() {
        logger.debug("Resource dengan URI tersebut tidak ditemukan");
    }
    
    private void fixLie(User x){
        x.getRole().setPermissionSet(null);
        x.getRole().setMenuSet(null);
    }
    
}
