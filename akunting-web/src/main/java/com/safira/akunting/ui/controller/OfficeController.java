/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.safira.akunting.ui.controller;

import com.safira.akunting.domain.acc.Office;
import com.safira.akunting.service.MasterRestfulService;
import com.safira.akunting.service.SystemRestfulService;
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
public class OfficeController {
    @Autowired
    private MasterRestfulService masterService;
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @RequestMapping("/office/{id}")
    @ResponseBody
    public Office findById(@PathVariable String id) {
        Office x = masterService.findByOfficeId(id);
        if (x == null) {
            throw new IllegalStateException();
        }
        return x;
    }

    @RequestMapping(value = "/office", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    public void create(@RequestBody @Valid Office x, HttpServletRequest request, HttpServletResponse response) {
        masterService.save(x);
        String requestUrl = request.getRequestURL().toString();
        URI uri = new UriTemplate("{requestUrl}/{id}").expand(requestUrl, x.getId());
        response.setHeader("Location", uri.toASCIIString());
    }

    @RequestMapping(method = RequestMethod.PUT, value = "/office/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void update(@PathVariable String id, @RequestBody @Valid Office x) {
        Office a = masterService.findByOfficeId(id);
        if (a == null) {
            logger.warn("Office dengan id [{}] tidak ditemukan", id);
            throw new IllegalStateException();
        }
        x.setId(a.getId());
        masterService.save(x);
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "/office/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void delete(@PathVariable String id) {
        Office a = masterService.findByOfficeId(id);
        if (a == null) {
            logger.warn("Office dengan id [{}] tidak ditemukan", id);
            throw new IllegalStateException();
        }
        masterService.delete(a);
    }

    @RequestMapping(value = "/office", method = RequestMethod.GET)
    @ResponseBody
    public List<Office> findAll(
            Pageable pageable,
            HttpServletResponse response) {
        //List<Office> hasil = masterService.findAllOffice(pageable).getContent();
        List<Office> hasil = masterService.listAllOffice();

        return hasil;
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler({IllegalStateException.class})
    public void handle() {
        logger.debug("Resource dengan URI tersebut tidak ditemukan");
    }

}
