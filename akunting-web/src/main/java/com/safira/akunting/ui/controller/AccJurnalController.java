package com.safira.akunting.ui.controller;

import com.safira.akunting.domain.AccJurnal;
import com.safira.akunting.domain.AccJurnalDetail;
import com.safira.akunting.domain.Role;
import com.safira.akunting.service.AccJurnalRestfulService;
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
        return x;
    }
    
    @RequestMapping("/jurnal-detail/{id}")
    @ResponseBody
    public AccJurnalDetail findJurnalDetailById(@PathVariable String id) {
        AccJurnalDetail x = jurnalRestfulService.findJurnalDetailById(id);
        if (x == null) {
            throw new IllegalStateException();
        }
        return x;
    }
    @RequestMapping("/jurnal-detail-list/{id}")
    @ResponseBody
    public List<AccJurnalDetail> findJurnalDetailByJurnalId(@PathVariable Integer id) {
        List<AccJurnalDetail> x = jurnalRestfulService.findJurnalDetailByJurnalId(id);
        if (x == null) {
            throw new IllegalStateException();
        }
        return x;
    }

//    @RequestMapping("/jurnal/{id}/unselected-permission")
//    @ResponseBody
//    public List<Permission> findPermissionNotInRole(@PathVariable String id) {
//        return jurnalRestfulService.findPermissionsNotInRole(jurnalRestfulService.findRoleById(id));
//    }
//    
//    @RequestMapping("/role/{id}/unselected-menu")
//    @ResponseBody
//    public List<Menu> findMenuNotInRole(@PathVariable String id) {
//        return jurnalRestfulService.findMenuNotInRole(jurnalRestfulService.findRoleById(id));
//    }
//
    @RequestMapping(value = "/jurnal", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    public void create(@RequestBody @Valid AccJurnal x, HttpServletRequest request, HttpServletResponse response) {
        jurnalRestfulService.save(x);
        String requestUrl = request.getRequestURL().toString();
        URI uri = new UriTemplate("{requestUrl}/{id}").expand(requestUrl, x.getId());
        response.setHeader("Location", uri.toASCIIString());
    }
    @RequestMapping(value = "/jurnal-detail", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    public void createJurnalDetail(@RequestBody @Valid AccJurnalDetail x, HttpServletRequest request, HttpServletResponse response) {
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
    @RequestMapping(method = RequestMethod.PUT, value = "/jurnal-detail/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void updateJurnalDetail(@PathVariable String id, @RequestBody @Valid AccJurnalDetail x) {
        AccJurnalDetail a = jurnalRestfulService.findJurnalDetailById(id);
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
    @RequestMapping(method = RequestMethod.DELETE, value = "/jurnal-detail/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteJurnalDetail(@PathVariable String id) {
        AccJurnalDetail a = jurnalRestfulService.findJurnalDetailById(id);
        if (a == null) {
            logger.warn("Jurnal detail dengan id [{}] tidak ditemukan", id);
            throw new IllegalStateException();
        }
        jurnalRestfulService.delete(a);
    }

    @RequestMapping(value = "/jurnal", method = RequestMethod.GET)
    @ResponseBody
    public List<AccJurnal> findAll(
            Pageable pageable,
            HttpServletResponse response) {
        List<AccJurnal> hasil = jurnalRestfulService.findAllJournal(pageable).getContent();
        for (AccJurnal r : hasil) {
            r.setListJurnal(null);
        }
        return hasil;
    }
    @RequestMapping(value = "/jurnal-detail", method = RequestMethod.GET)
    @ResponseBody
    public List<AccJurnalDetail> findAllJurnalDetail(
            Pageable pageable,
            HttpServletResponse response) {
        List<AccJurnalDetail> hasil = jurnalRestfulService.findAllJurnalDetail(pageable).getContent();
        for (AccJurnalDetail r : hasil) {
            r.setAkun(null);
        }
        return hasil;
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler({IllegalStateException.class})
    public void handle() {
        logger.debug("Resource dengan URI tersebut tidak ditemukan");
    }
}
