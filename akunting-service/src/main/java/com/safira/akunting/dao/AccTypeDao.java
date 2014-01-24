/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.safira.akunting.dao;

import com.safira.akunting.domain.acc.CoaType;
import java.util.List;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

/**
 *
 * @author faheem
 */
public interface AccTypeDao extends PagingAndSortingRepository<CoaType, String>{
    @Query("select c from CoaType c ")
    List<CoaType> listAll();
    
    CoaType findByTypeName(String name);
    
}
