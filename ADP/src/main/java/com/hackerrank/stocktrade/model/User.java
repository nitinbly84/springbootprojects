package com.hackerrank.stocktrade.model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class User {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	
	@Column
    private String name;
	
	@OneToMany(mappedBy = "user")
	private List<Trade> tradesList;

	public User() {
    }

    public User(Long id, String name) {
        this.id = id;
        this.name = name;
    }
    

    public List<Trade> getTradesList() {
		return tradesList;
	}

	public void setTradesList(List<Trade> tradesList) {
		this.tradesList = tradesList;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
