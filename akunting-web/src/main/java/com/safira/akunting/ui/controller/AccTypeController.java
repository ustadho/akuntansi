/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.safira.akunting.ui.controller;

import com.safira.akunting.domain.AccGroup;
import com.safira.akunting.service.MasterRestfulService;
import java.net.URI;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
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
public class AccTypeController {
    @Autowired
    MasterRestfulService masterRestfulService;
    private Logger logger = LoggerFactory.getLogger(this.getClass());
    
    @RequestMapping("/acc-type/{typeId}")
    @ResponseBody
    public AccGroup findById(@PathVariable String typeId) {
        AccGroup x = masterRestfulService.findByTypeId(typeId);
        if (x == null) {
            throw new IllegalStateException();
        }
        return x;
    }
    
    @RequestMapping(value = "/acc-type", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    public void create(@RequestBody @Valid AccGroup x, HttpServletRequest request, HttpServletResponse response) {
        masterRestfulService.save(x);
        String requestUrl = request.getRequestURL().toString();
        URI uri = new UriTemplate("{requestUrl}/{typeId}").expand(requestUrl, x.getTypeId());
        response.setHeader("Location", uri.toASCIIString());
    }
    
    @RequestMapping(method = RequestMethod.PUT, value = "/acc-type/{typeId}")
    @ResponseStatus(HttpStatus.OK)
    public void update(@PathVariable String typeId, @RequestBody @Valid AccGroup x) {
        AccGroup a = masterRestfulService.findByTypeId(typeId);
        if (a == null) {
            logger.warn("Tipe Akund dengan nomor [{}] tidak ditemukan", typeId);
            throw new IllegalStateException();
        }
        x.setTypeId(a.getTypeId());
        masterRestfulService.save(x);
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "/acc-type/{typeId}")
    @ResponseStatus(HttpStatus.OK)
    public void delete(@PathVariable String typeId) {
        AccGroup a = masterRestfulService.findByTypeId(typeId);
        if (a == null) {
            logger.warn("Tipe akun dengan id [{}] tidak ditemukan", typeId);
            throw new IllegalStateException();
        }
        masterRestfulService.delete(a);
    }

    @RequestMapping(value = "/acc-type", method = RequestMethod.GET)
    @ResponseBody
    public List<AccGroup> findAll(
            Pageable pageable,
            HttpServletResponse response) {
        List<AccGroup> hasil = masterRestfulService.findAllAccGroups(pageable).getContent();
//        for(AccCoa r : hasil){
//            r.setPermissionSet(null);
//            r.setMenuSet(null);
//        }
        return hasil;

    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler({IllegalStateException.class})
    public void handle() {
        logger.debug("Resource dengan URI tersebut tidak ditemukan");
    }
}
