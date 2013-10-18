/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.safira.akunting.dao;

import com.safira.akunting.domain.AccJurnalDetail;
import org.springframework.data.repository.PagingAndSortingRepository;

/**
 *
 * @author faheem
 */
public interface AccJurnalDetailDao extends PagingAndSortingRepository<AccJurnalDetail, String>{
    
}
