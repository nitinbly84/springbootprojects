package com.hackerrank.stocktrade.model;

import java.sql.Timestamp;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class Trade {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
    private Long id;
	@Column
    private String type;
	@ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="user")
    private User user;
    @Column
    private String stockSymbol;
    @Column
    private Integer stockQuantity;
    @Column
    private Float stockPrice;
    @Column
    private Timestamp tradeTimestamp;

    public Trade() {
    }

    public Trade(Long id, String type, User user, String stockSymbol, Integer stockQuantity, Float stockPrice, Timestamp tradeTimestamp) {
        this.id = id;
        this.type = type;
        this.user = user;
        this.stockSymbol = stockSymbol;
        this.stockQuantity = stockQuantity;
        this.stockPrice = stockPrice;
        this.tradeTimestamp = tradeTimestamp;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getStockSymbol() {
        return stockSymbol;
    }

    public void setStockSymbol(String stockSymbol) {
        this.stockSymbol = stockSymbol;
    }

    public Integer getStockQuantity() {
        return stockQuantity;
    }

    public void setStockQuantity(Integer stockQuantity) {
        this.stockQuantity = stockQuantity;
    }

    public Float getStockPrice() {
        return stockPrice;
    }

    public void setStockPrice(Float stockPrice) {
        this.stockPrice = stockPrice;
    }

    public Timestamp getTradeTimestamp() {
        return tradeTimestamp;
    }

    public void setTradeTimestamp(Timestamp tradeTimestamp) {
        this.tradeTimestamp = tradeTimestamp;
    }
    
    @Override
    public int hashCode() {
    	return id.hashCode();
    }
    
    @Override
    public boolean equals(Object t) {
    	return this.id == ((Trade)t).id;
    }
}
