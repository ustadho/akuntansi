/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.safira.akunting.dao;

import com.safira.akunting.domain.acc.Currency;
import java.util.List;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

/**
 *
 * @author faheem
 */
public interface AccCurrencyDao extends PagingAndSortingRepository<Currency, String>{
    @Query("select c from Currency c order by c.id")
    List<Currency> listAll();
    
    @Query("select c from Currency c "
            + "where lower(id) like :search "
            + "or lower(name) like :search")
    public List<Currency> listAll(@Param("search")String search);
    
    @Query("select count(c) from Currency c "
            + "where lower(id) like :search "
            + "or lower(name) like :search")
    public Long count(@Param("search")String search);
}
