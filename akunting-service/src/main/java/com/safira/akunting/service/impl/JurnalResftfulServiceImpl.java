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
    public void save(AccJurnal j) {
        accJurnalDao.save(j).getId();
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
        return accJurnalDao.findOne(String.valueOf(id));
    }

    @Override
    public AccJurnalDetail findJurnalDetailById(String id) {
        return accJurnalDetailDao.findOne(id);
    }

    @Override
    public List<AccJurnalDetail> findJurnalDetailByJurnalId(Integer id) {
        return accJurnalDetailDao.findJurnalDetailByJurnalId(id);
    }
}
