/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.safira.akunting.service.impl;

import com.safira.akunting.dao.AccCoaDao;
import com.safira.akunting.dao.AccCurrencyDao;
import com.safira.akunting.dao.AccTypeDao;
import com.safira.akunting.dao.OfficeDao;
import com.safira.akunting.domain.acc.Coa;
import com.safira.akunting.domain.acc.CoaType;
import com.safira.akunting.domain.acc.Currency;
import com.safira.akunting.domain.acc.Office;
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
    private AccTypeDao accTypeDao;
    
    @Autowired
    private OfficeDao officeDao;
    
    @Autowired
    private AccCurrencyDao accCurrencyDao;
    
    @Override
    public void save(Coa coa) {
        accCoaDao.save(coa);
    }

    @Override
    public void delete(Coa coa) {
        accCoaDao.delete(coa);
    }

    @Override
    public Coa findByAccNo(String accNo) {
         if(!StringUtils.hasText(accNo)){
            return null;
        }
        
        Coa r = accCoaDao.findOne(accNo);
        if(r != null){
//            r.getPermissionSet().size();
//            r.getMenuSet().size();
        }
        
        return r;
    }

    @Override
    public Page<Coa> findAllCoa(Pageable pageable) {
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
    public Page<Coa> findCoas(String search, Pageable pageable) {
        if (!StringUtils.hasText(search)) {
            return findAllCoa(pageable);
        }
        
        if(pageable == null){
            pageable = new PageRequest(0, 20);
        }

        return accCoaDao.search("%"+search+"%", pageable);
    }

    @Override
    public void save(CoaType coa) {
        accTypeDao.save(coa);
    }

    @Override
    public void delete(CoaType coa) {
        accTypeDao.delete(coa);
    }

    @Override
    public CoaType findByTypeId(String typeId) {
        return accTypeDao.findOne(typeId);
    }

    @Override
    public Page<CoaType> findAllAccGroups(Pageable page) {
        return accTypeDao.findAll(page);
    }

    @Override
    public Long countAllAccGroups() {
        return accTypeDao.count();
    }

    @Override
    public List<Coa> listAllCoa() {
        return accCoaDao.listAll();
    }

    @Override
    public void save(Currency curr) {
        accCurrencyDao.save(curr);
    }

    @Override
    public void delete(Currency curr) {
        accCurrencyDao.delete(curr);
    }

    @Override
    public Currency findById(String kode) {
        return accCurrencyDao.findOne(kode);
    }

    @Override
    public List<Currency> findAllCurrency() {
        return accCurrencyDao.listAll();
    }

    @Override
    public Long countAllCurrency() {
        return accCurrencyDao.count();
    }

    @Override
    public Long countCurrency(String search) {
        return accCurrencyDao.count(search);
    }

    @Override
    public List<CoaType> listAll() {
        return accTypeDao.listAll();
    }

    @Override
    public void save(Office o) {
        officeDao.save(o);
    }

    @Override
    public void delete(Office o) {
        officeDao.delete(0);
    }

    @Override
    public Office findByOfficeId(String id) {
        return officeDao.findOne(id);
    }

    @Override
    public Page<Office> findAllOffice(Pageable page) {
        return officeDao.findAll(page);
    }

    @Override
    public Long countAllOffice() {
        return officeDao.count();
    }

    @Override
    public List<Office> listAllOffice() {
        return officeDao.listAll();
    }

}
