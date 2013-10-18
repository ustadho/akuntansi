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
    public Integer save(AccJurnal j) {
        return accJurnalDao.save(j).getId();
        
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
    public void save(AccJurnalDetail d) {
        accJurnalDetailDao.save(d);
    }

    @Override
    public void delete(AccJurnalDetail d) {
        if(d==null || d.getId()==null){
            return;
        }
        accJurnalDetailDao.delete(d);
    }

    @Override
    public Page<AccJurnalDetail> findAllJurnalDetail(Pageable pageable) {
        if(pageable ==null){
            pageable=new PageRequest(0, 20);
        }
        return accJurnalDetailDao.findAll(pageable);
    }

    @Override
    public Long countAllJurnalDetail() {
        return accJurnalDetailDao.count();
    }

    @Override
    public AccJurnal findJurnalById(Integer id) {
        return null; //accJurnalDao.findOne(i);
    }

    @Override
    public List<AccJurnalDetail> findJurnalDetailById(Integer id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
