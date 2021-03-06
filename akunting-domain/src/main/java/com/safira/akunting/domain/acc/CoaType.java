/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.safira.akunting.domain.acc;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import org.hibernate.validator.constraints.NotEmpty;

/**
 *
 * @author faheem
 */
@Entity
@Table(name = "acc_type")
public class CoaType {
    @Id
    @Column(name = "type_id", nullable = false, length = 2)        
    private String typeId;
    
    @Column(name = "type_name", length = 150)
    @NotNull
    @NotEmpty
    private String typeName;
    
    private String catatan;
    
    @Column(name = "group_name", length = 30)
    private String groupName;
    
    public String getTypeId() {
        return typeId;
    }

    public void setTypeId(String typeId) {
        this.typeId = typeId;
    }

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

    public String getCatatan() {
        return catatan;
    }

    public void setCatatan(String catatan) {
        this.catatan = catatan;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

//    public List<AccCoa> getDaftarCoa() {
//        return daftarCoa;
//    }
//
//    public void setDaftarCoa(List<AccCoa> daftarCoa) {
//        this.daftarCoa = daftarCoa;
//    }
 
    
}
