/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.safira.akunting.service;

import com.safira.akunting.domain.acc.Coa;
import com.safira.akunting.domain.acc.CoaType;
import com.safira.akunting.domain.acc.Currency;
import com.safira.akunting.domain.acc.Office;
import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 *
 * @author faheem
 */
public interface MasterRestfulService extends MonitoredService{
    //AccGroup
    public void save(CoaType coa);
    public void delete(CoaType coa);
    public CoaType findByTypeId(String typeId);
    Page<CoaType> findAllAccGroups(Pageable page);
    public List<CoaType> listAll();
    Long countAllAccGroups();
    
    //COA
    public void save(Coa coa);
    public void delete(Coa coa);
    public Coa findByAccNo(String accNo);
    Page<Coa> findAllCoa(Pageable page);
    Long countAllCoa();
    Long countCoa(String search);
    Page<Coa> findCoas(String search, Pageable pageable);
    List<Coa> listAllCoa();
    
    
    public void save(Currency curr);
    public void delete(Currency curr);
    public Currency findById(String kode);
    public List<Currency> findAllCurrency();
    public Long countAllCurrency();
    public Long countCurrency(String search);
    
    //Office
    public void save(Office o);
    public void delete(Office o);
    public Office findByOfficeId(String id);
    public Page<Office> findAllOffice(Pageable page);
    public Long countAllOffice();
    public List<Office> listAllOffice();
}
