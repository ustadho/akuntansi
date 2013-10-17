/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.safira.akunting.service.impl;

import com.safira.akunting.dao.AccCoaDao;
import com.safira.akunting.dao.AccGroupDao;
import com.safira.akunting.dao.ApplicationConfigDao;
import com.safira.akunting.domain.AccCoa;
import com.safira.akunting.domain.AccGroup;
import com.safira.akunting.domain.Role;
import com.safira.akunting.service.MasterRestfulService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

/**
 *
 * @author faheem
 */
@SuppressWarnings("unchecked")
@Service("masterRestfulService")
@Transactional
public class MasterRestfulServiceImpl implements MasterRestfulService{
    @Autowired
    private AccCoaDao accCoaDao;
    
    @Autowired
    private AccGroupDao accGroupDao;
    
    @Override
    public void save(AccCoa coa) {
        accCoaDao.save(coa);
    }

    @Override
    public void delete(AccCoa coa) {
        accCoaDao.delete(coa);
    }

    @Override
    public AccCoa findByAccNo(String accNo) {
         if(!StringUtils.hasText(accNo)){
            return null;
        }
        
        AccCoa r = accCoaDao.findOne(accNo);
        if(r != null){
//            r.getPermissionSet().size();
//            r.getMenuSet().size();
        }
        
        return r;
    }

    @Override
    public Page<AccCoa> findAllCoa(Pageable pageable) {
         if(pageable == null){
            pageable = new PageRequest(0, 20);
        }
        return accCoaDao.findAll(pageable);
    }

    @Override
    public Long countAllCoa() {
        return accCoaDao.count();
    }

    @Override
    public Long countCoa(String search) {
        if (!StringUtils.hasText(search)) {
            return countAllCoa();
        }
        return accCoaDao.count("%" + search + "%");
    }

    @Override
    public Page<AccCoa> findCoas(String search, Pageable pageable) {
        if (!StringUtils.hasText(search)) {
            return findAllCoa(pageable);
        }
        
        if(pageable == null){
            pageable = new PageRequest(0, 20);
        }

        return accCoaDao.search("%" + search + "%", pageable);
    }

    @Override
    public void save(AccGroup coa) {
        accGroupDao.save(coa);
    }

    @Override
    public void delete(AccGroup coa) {
        accGroupDao.delete(coa);
    }

    @Override
    public AccGroup findByTypeId(String typeId) {
        if(!StringUtils.hasText(typeId)){
            return null;
        }
        
        AccGroup r = accGroupDao.findOne(typeId);
        if(r != null){
//            r.getPermissionSet().size();
//            r.getMenuSet().size();
        }
        
        return r;
    }

    @Override
    public Page<AccGroup> findAllAccGroups(Pageable page) {
         if(page == null){
            page = new PageRequest(0, 20);
        }
        return accGroupDao.findAll(page);
    }

    @Override
    public Long countAllAccGroups() {
        return accGroupDao.count();
    }

    @Override
    public List<AccCoa> listAllCoa() {
        return accCoaDao.listAll();
    }

    
}
