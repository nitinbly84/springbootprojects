package com.hackerrank.stocktrade.service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.stereotype.Component;

import com.hackerrank.stocktrade.model.Trade;

@Component
public class TradeService {

	private static Set<Trade> trades = new HashSet<>();

	public Long addTrade(Trade trade) {
		if(!trades.add(trade))
			return null;
		return trade.getId();
	}
	
	public List<Trade> getAllTrades() {
		List<Trade> tradesList = new ArrayList<>();
		tradesList.addAll(trades);
		return tradesList;
	}
	
	public List<Trade> getAllTradesForUser(Long userId) {
		List<Trade> tradesList = new ArrayList<>();
		trades.stream().filter(a -> a.getId() == userId).
			forEach(trades -> tradesList.add(trades));
		
		return tradesList;
	}

	public void deleteAll() {
		trades.clear();
		
	}

}