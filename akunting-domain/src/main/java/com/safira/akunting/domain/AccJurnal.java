/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.safira.akunting.domain;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.validator.constraints.NotEmpty;

/**
 *
 * @author faheem
 */
@Entity
@Table(name = "acc_jurnal")
public class AccJurnal {

    @Id
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid2")
    private String id;
    
    @NotNull
    @NotEmpty
    @Column(name = "journal_no", nullable = false)
    private String journalNo;

    @Temporal(TemporalType.DATE)
    @Column(name = "tanggal", insertable = false, columnDefinition="TIMESTAMP DEFAULT CURRENT_DATE")
    private Date tanggal=new Date();
    
    private String description;
    
    @Column(name = "multi_curr")
    private Boolean multiCurr=new Boolean(false);
    
    @Column(name = "is_saldo_awal")
    private Boolean isSaldoAwal;

    @Column(name = "is_closed")
    private Boolean isClosed;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "date_ins", nullable = false, insertable = false, columnDefinition="TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private Date dateIns=new Date();

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;
    
    @OneToMany(mappedBy = "jurnal", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<AccJurnalDetail> listJurnal=new ArrayList<AccJurnalDetail>();

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getJournalNo() {
        return journalNo;
    }

    public void setJournalNo(String journalNo) {
        this.journalNo = journalNo;
    }

    public Date getTanggal() {
        return tanggal;
    }

    public void setTanggal(Date tanggal) {
        this.tanggal = tanggal;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Boolean getMultiCurr() {
        return multiCurr;
    }

    public void setMultiCurr(Boolean multiCurr) {
        this.multiCurr = multiCurr;
    }

    public Boolean getIsSaldoAwal() {
        return isSaldoAwal;
    }

    public void setIsSaldoAwal(Boolean isSaldoAwal) {
        this.isSaldoAwal = isSaldoAwal;
    }

    public Boolean getIsClosed() {
        return isClosed;
    }

    public void setIsClosed(Boolean isClosed) {
        this.isClosed = isClosed;
    }

    public Date getDateIns() {
        return dateIns;
    }

    public void setDateIns(Date dateIns) {
        this.dateIns = dateIns;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<AccJurnalDetail> getListJurnal() {
        return listJurnal;
    }

    public void setListJurnal(List<AccJurnalDetail> listJurnal) {
        this.listJurnal = listJurnal;
    }
    
    
}
