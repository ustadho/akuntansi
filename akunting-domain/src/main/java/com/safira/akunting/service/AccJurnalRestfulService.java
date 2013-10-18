/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.safira.akunting.service;

import com.safira.akunting.domain.AccJurnal;
import com.safira.akunting.domain.AccJurnalDetail;
import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 *
 * @author faheem
 */
public interface AccJurnalRestfulService extends MonitoredService{
    Integer save(AccJurnal j);
    void delete(AccJurnal j);
    Page<AccJurnal> findAllJournal(Pageable pageable);
    Long countAllJurnal();
    AccJurnal findJurnalById(Integer id);
    
    void save(AccJurnalDetail d);
    void delete(AccJurnalDetail d);
    Page<AccJurnalDetail> findAllJurnalDetail(Pageable pageable);
    Long countAllJurnalDetail();
    List<AccJurnalDetail> findJurnalDetailById(Integer id);
}
