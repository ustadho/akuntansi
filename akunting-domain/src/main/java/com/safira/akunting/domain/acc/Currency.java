/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.safira.akunting.domain.acc;

import java.math.BigDecimal;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

/**
 *
 * @author faheem
 */
@Entity
@Table(name = "acc_currency")
public class Currency {
    @Id
    @NotNull
    @Column(unique = true, nullable = false, length = 5)
    private String id;
    
    private String name;
    
    @Column(name = "exchange_rate")
    private BigDecimal exchangeRate;
    
    @Column(name = "country_name")
    private String countryName;
    
    private String symbol;
    
    private Boolean active;
    
    public String getId() {
        return id;
    }

    public void setId(String name) {
        this.id = name;
    }

    public BigDecimal getExchangeRate() {
        return exchangeRate;
    }

    public void setExchangeRate(BigDecimal exchangeRate) {
        this.exchangeRate = exchangeRate;
    }

    public String getCountryName() {
        return countryName;
    }

    public void setCountryName(String countryName) {
        this.countryName = countryName;
    }

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public Boolean isActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    
    
    
}
