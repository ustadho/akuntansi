/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.safira.akunting.dao;

import com.safira.akunting.domain.AccJurnal;
import org.springframework.data.repository.PagingAndSortingRepository;

/**
 *
 * @author faheem
 */
public interface AccJurnalDao extends PagingAndSortingRepository<AccJurnal, String>{
    
}
