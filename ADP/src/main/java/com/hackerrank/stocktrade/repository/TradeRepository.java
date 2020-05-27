package com.hackerrank.stocktrade.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hackerrank.stocktrade.model.Trade;

public interface TradeRepository extends JpaRepository<Trade, Long> {
//	public List<Trade> findAllTrade_User_id(final Long id);
}
