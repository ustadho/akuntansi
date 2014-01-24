/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.safira.akunting.domain.acc;

import com.safira.akunting.domain.User;
import java.math.BigDecimal;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import org.hibernate.validator.constraints.NotEmpty;

/**
 *
 * @author faheem
 */
@Entity
@Table(name = "acc_coa")
public class Coa {
    @Id
    @Column(name = "acc_no", unique = true, nullable = false)
    @NotNull
    @NotEmpty
    private String accNo;
    
    @Column(name = "acc_name", unique = true, nullable = false)
    private String accName;
    
    @ManyToOne
    @JoinColumn(name = "type_id", nullable = false)
    private CoaType accType;
    
    @ManyToOne
    @JoinColumn(name = "office_id", nullable = false)
    private Office office;
    
    
    @Column(name = "is_budget")
    private Boolean isBudget;
    
    @ManyToOne
    @JoinColumn(name = "currency_id", nullable = false)
    private Currency currency;
    
//    @ManyToMany
//    @JoinTable(
//        name="acc_coa_currency", 
//        joinColumns=@JoinColumn(name="acc_no", nullable=false),
//        inverseJoinColumns=@JoinColumn(name="currency_name", nullable=false)
//    )
//    private Set<Currency> currencySet = new HashSet<Currency>();
    
    private String memo;
    private Boolean active;
    
    @Column(name = "saldo_rek_koran")
    private BigDecimal saldoRekKoran;
    
    @Column(name = "date_ins")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateIns=new Date();
    
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
    
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

    public CoaType getAccType() {
        return accType;
    }

    public void setAccType(CoaType accType) {
        this.accType = accType;
    }

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    public String getCatatan() {
        return memo;
    }

    public void setCatatan(String catatan) {
        this.memo = catatan;
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

    public Office getOffice() {
        return office;
    }

    public void setOffice(Office office) {
        this.office = office;
    }

    public Boolean isIsBudget() {
        return isBudget;
    }

    public void setIsBudget(Boolean isBudget) {
        this.isBudget = isBudget;
    }

    public Currency getCurrency() {
        return currency;
    }

    public void setCurrency(Currency currency) {
        this.currency = currency;
    }

    
    public String getMemo() {
        return memo;
    }

    public void setMemo(String memo) {
        this.memo = memo;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Boolean getKelompokAnggaran() {
        return isBudget;
    }

    public void setKelompokAnggaran(Boolean kelompokAnggaran) {
        this.isBudget = kelompokAnggaran;
    }
    
    
}
