/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.safira.akunting.dao;

import com.safira.akunting.domain.acc.Coa;
import com.safira.akunting.domain.acc.Office;
import java.io.Serializable;
import java.util.List;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

/**
 *
 * @author faheem
 */
public interface OfficeDao extends PagingAndSortingRepository<Office, Serializable>{
    @Query("select o from Office o ")
    List<Office> listAll();
}
