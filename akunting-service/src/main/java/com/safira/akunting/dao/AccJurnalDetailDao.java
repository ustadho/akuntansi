/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.safira.akunting.dao;

import com.safira.akunting.domain.AccJurnalDetail;
import com.safira.akunting.domain.Menu;
import java.util.List;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

/**
 *
 * @author faheem
 */
public interface AccJurnalDetailDao extends PagingAndSortingRepository<AccJurnalDetail, String>{
    @Query("select a from AccJurnalDetail a " +
			"where a.jurnal.id = :id " +
			"order by a.id")
    public List<AccJurnalDetail> findJurnalDetailByJurnalId(Integer id);
}
