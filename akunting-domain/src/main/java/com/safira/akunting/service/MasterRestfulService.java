/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.safira.akunting.service;

import com.safira.akunting.domain.AccCoa;
import com.safira.akunting.domain.AccGroup;
import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 *
 * @author faheem
 */
public interface MasterRestfulService extends MonitoredService{
    //AccGroup
    public void save(AccGroup coa);
    public void delete(AccGroup coa);
    public AccGroup findByTypeId(String typeId);
    Page<AccGroup> findAllAccGroups(Pageable page);
    Long countAllAccGroups();
    
    //COA
    public void save(AccCoa coa);
    public void delete(AccCoa coa);
    public AccCoa findByAccNo(String accNo);
    Page<AccCoa> findAllCoa(Pageable page);
    Long countAllCoa();
    Long countCoa(String search);
    Page<AccCoa> findCoas(String search, Pageable pageable);
    List<AccCoa> listAllCoa();
    
}
