/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.safira.akunting.domain.acc;

import java.math.BigDecimal;
import java.math.BigInteger;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import org.hibernate.annotations.GenericGenerator;

/**
 *
 * @author faheem
 */
@Entity
@Table(name = "acc_jurnal_detail")
public class AccJurnalDetail {
    @Id
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid")
    private String id;
    
    @NotNull
    @ManyToOne 
    @JoinColumn(name = "acc_no", nullable = false)
    //@JsonBackReference
    private Coa akun;
    
    @NotNull
    @ManyToOne
    @JoinColumn(name = "jurnal_id", nullable = false)
    //@JsonBackReference
    private AccJurnal jurnal;
    
    
    private BigDecimal debet=new BigDecimal(BigInteger.ZERO);
    private BigDecimal kredit =new BigDecimal(BigInteger.ZERO);;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public AccJurnal getJurnal() {
        return jurnal;
    }

    public void setJurnal(AccJurnal jurnal) {
        this.jurnal = jurnal;
    }

    public Coa getAkun() {
        return akun;
    }

    public void setAkun(Coa akun) {
        this.akun = akun;
    }

    public BigDecimal getDebet() {
        return debet;
    }

    public void setDebet(BigDecimal debet) {
        this.debet = debet;
    }

    public BigDecimal getKredit() {
        return kredit;
    }

    public void setKredit(BigDecimal kredit) {
        this.kredit = kredit;
    }
    
    
}
