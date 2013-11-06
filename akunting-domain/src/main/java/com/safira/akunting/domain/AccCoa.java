/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.safira.akunting.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlTransient;
import org.hibernate.validator.constraints.NotEmpty;

/**
 *
 * @author faheem
 */
@Entity
@Table(name = "acc_coa")
public class AccCoa {
    @Id
    @Column(name = "acc_no", unique = true, nullable = false)
    @NotNull
    @NotEmpty
    private String accNo;
    
    @Column(name = "acc_name", unique = true, nullable = false)
    private String accName;
    
    @Column(name = "sub_acc_of")
    private String subAccOf;
    
    @ManyToOne
    @JoinColumn(name = "acc_type", nullable = false)
//    @JsonBackReference
    private AccGroup accGroup;
    
    private Boolean active;
    
    @Column(name = "saldo_awal")
    private BigDecimal saldoAwal;
    
    @Column(name = "per_tgl")
    @Temporal(TemporalType.DATE)
    private Date perTgl;

    private String catatan;
    
    @Column(name = "saldo_rek_koran")
    private BigDecimal saldoRekKoran;
    
    @Column(name = "date_ins")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateIns=new Date();
    
    @Column(name = "user_ins")
    private String userIns;
    
    @Column(name = "date_upd")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateUpd;
    
    @Column(name = "user_upd")
    private String userUpd;
    
    @Column(name = "curr_id")
    private String currId;
    
    @Column(name = "acc_groups")
    private String accGroups;
    
    public String getAccNo() {
        return accNo;
    }

    public void setAccNo(String accNo) {
        this.accNo = accNo;
    }

    public String getAccName() {
        return accName;
    }

    public void setAccName(String accName) {
        this.accName = accName;
    }

    public String getSubAccOf() {
        return subAccOf;
    }

    public void setSubAccOf(String subAccOf) {
        this.subAccOf = subAccOf;
    }

    public AccGroup getAccGroup() {
        return accGroup;
    }

    public void setAccGroup(AccGroup accGroup) {
        this.accGroup = accGroup;
    }

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    public BigDecimal getSaldoAwal() {
        return saldoAwal;
    }

    public void setSaldoAwal(BigDecimal saldoAwal) {
        this.saldoAwal = saldoAwal;
    }

    public Date getPerTgl() {
        return perTgl;
    }

    public void setPerTgl(Date perTgl) {
        this.perTgl = perTgl;
    }

    public String getCatatan() {
        return catatan;
    }

    public void setCatatan(String catatan) {
        this.catatan = catatan;
    }

    public BigDecimal getSaldoRekKoran() {
        return saldoRekKoran;
    }

    public void setSaldoRekKoran(BigDecimal saldoRekKoran) {
        this.saldoRekKoran = saldoRekKoran;
    }

    public Date getDateIns() {
        return dateIns;
    }

    public void setDateIns(Date dateIns) {
        this.dateIns = dateIns;
    }

    public String getUserIns() {
        return userIns;
    }

    public void setUserIns(String userIns) {
        this.userIns = userIns;
    }

    public Date getDateUpd() {
        return dateUpd;
    }

    public void setDateUpd(Date dateUpd) {
        this.dateUpd = dateUpd;
    }

    public String getUserUpd() {
        return userUpd;
    }

    public void setUserUpd(String userUpd) {
        this.userUpd = userUpd;
    }

    public String getCurrId() {
        return currId;
    }

    public void setCurrId(String currId) {
        this.currId = currId;
    }

    public String getAccGroups() {
        return accGroups;
    }

    public void setAccGroups(String accGroups) {
        this.accGroups = accGroups;
    }

//    @XmlTransient
//    public List<AccJurnalDetail> getDaftarJurnal() {
//        return daftarJurnal;
//    }
//
//    public void setDaftarJurnal(List<AccJurnalDetail> daftarJurnal) {
//        this.daftarJurnal = daftarJurnal;
//    }
    
    
}
