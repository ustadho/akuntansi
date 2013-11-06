/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.safira.akunting.service.impl;

import com.safira.akunting.dao.AccJurnalDao;
import com.safira.akunting.dao.AccJurnalDetailDao;
import com.safira.akunting.domain.AccJurnal;
import com.safira.akunting.domain.AccJurnalDetail;
import com.safira.akunting.service.AccJurnalRestfulService;
import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author faheem
 */
@SuppressWarnings("unchecked")
@Service("accJurnalService")
@Transactional

public class JurnalResftfulServiceImpl implements AccJurnalRestfulService{
    @Autowired
    AccJurnalDao accJurnalDao;
    
    @Autowired
    AccJurnalDetailDao accJurnalDetailDao;
    
    @Override
    public void save(AccJurnal j) {
        List<AccJurnalDetail> det=j.getListJurnal();
        for(int i=0; i<det.size(); i++){
            det.get(i).setJurnal(j);
        }
        j.setListJurnal(det);
        accJurnalDao.save(j);
    }

    @Override
    public void delete(AccJurnal j) {
        if(j==null || j.getId()==null){
            return;
        }
        accJurnalDao.delete(j);
    }

    @Override
    public Page<AccJurnal> findAllJournal(Pageable pageable) {
        if(pageable==null){
            pageable=new PageRequest(0, 20);
        }
        return accJurnalDao.findAll(pageable);
    }

    @Override
    public Long countAllJurnal() {
        return accJurnalDao.count();
    }

    @Override
    public AccJurnal findJurnalById(Integer id) {
        return accJurnalDao.findOne(String.valueOf(id));
    }

    @Override
    public List<AccJurnal> filterJurnalPerTanggal(Date mulai, Date sampai, Pageable pageable) {
        return accJurnalDao.tampilkanJurnalByTanggal(mulai, sampai, pageable);
    }
}
