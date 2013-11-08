/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.safira.akunting.service;

import com.safira.akunting.domain.AccJurnal;
import com.safira.akunting.domain.AccJurnalDetail;
import java.util.Date;
import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 *
 * @author faheem
 */
public interface AccJurnalRestfulService extends MonitoredService{
    void save(AccJurnal j);
    void delete(AccJurnal j);
    Page<AccJurnal> findAllJournal(Pageable pageable);
    Long countAllJurnal();
    AccJurnal findJurnalById(String id);
    Page<AccJurnal> filterJurnalPerTanggal(Date mulai, Date sampai, Pageable pageable);
    List<AccJurnalDetail> getDetail(String id);
    
}
