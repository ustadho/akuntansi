/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.safira.akunting.dao;

import com.safira.akunting.domain.AccCoa;
import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

/**
 *
 * @author faheem
 */
public interface AccCoaDao extends PagingAndSortingRepository<AccCoa, String>{
    @Query("select ac from AccCoa ac " +
			"where lower(ac.accNo) like lower(:search) " +
			"or lower(ac.accName) like lower(:search) " +
			"or lower(ac.accGroup.typeName) like lower(:search) " +
			"order by ac.accName")
    Page<AccCoa> search(@Param("search") String search, Pageable page);
    
    @Query("select count(ac) from AccCoa ac " +
			"where lower(ac.accNo) like lower(:search) " +
			"or lower(ac.accName) like lower(:search) " +
			"or lower(ac.accGroup.typeName) like lower(:search)")
    Long count(@Param("search") String search);
    
    @Query("from AccCoa ac order by ac.accNo")
    List<AccCoa> listAll();
    
}
