/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.safira.akunting.dao;

import com.safira.akunting.domain.acc.Coa;
import com.safira.akunting.domain.acc.Currency;
import org.springframework.data.repository.PagingAndSortingRepository;

/**
 *
 * @author faheem
 */
public interface AccCurrency extends PagingAndSortingRepository<Currency, String>{
    
}
