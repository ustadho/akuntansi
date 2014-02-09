/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.safira.akunting.dao;

import com.safira.akunting.domain.acc.Coa;
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
public interface AccCoaDao extends PagingAndSortingRepository<Coa, String>{
    @Query("select ac from Coa ac " +
			"where lower(ac.accNo) like lower(:search) " +
			"or lower(ac.accName) like lower(:search) " +
			"")
    Page<Coa> search(@Param("search") String search, Pageable page);
    
    @Query("select count(ac) from Coa ac " +
			"where lower(ac.accNo) like lower(:search) " +
			"or lower(ac.accName) like lower(:search) ")
    Long count(@Param("search") String search);
    
    @Query("select ac from Coa ac "
            //+ "join fetch ac.accGroup "
            + "order by ac.accNo")
    List<Coa> listAll();
    
    @Query("select ac from Coa ac order by ac.accNo")
    @Override
    public Page<Coa> findAll(Pageable pgbl);
}
