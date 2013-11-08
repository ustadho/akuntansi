/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.safira.akunting.dao;

import com.safira.akunting.domain.AccJurnal;
import com.safira.akunting.domain.AccJurnalDetail;
import java.util.Date;
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
public interface AccJurnalDao extends PagingAndSortingRepository<AccJurnal, String>{
    @Query("select j from AccJurnal j "
            + "where j.tanggal between :mulai and :sampai ")
    public Page<AccJurnal> tampilkanJurnalByTanggal(
            @Param("mulai") Date tglAwal, 
            @Param("sampai") Date tglAkhir,
            Pageable pageable
            );

    @Query("select d, coa.accName from AccJurnalDetail d "
            + "join fetch d.akun as coa "
            + "where d.jurnal.id=:id ")
    public List<AccJurnalDetail> getDetail(@Param("id") String id);
    
    
}
