package com.hackerrank.stocktrade.controller;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.hackerrank.stocktrade.model.Trade;
import com.hackerrank.stocktrade.repository.TradeRepository;
import com.hackerrank.stocktrade.service.TradeService;

@RestController
public class StockTradeApiRestController {
	
	@Autowired
	TradeService tradeService;
	
	@Autowired
	TradeRepository tradeRepository;
	
	@GetMapping("/trades")
	public List<Trade> retrieveAllTrades() {
		return tradeRepository.findAll();
//		return tradeService.getAllTrades();
	}
	
	@GetMapping("/trades/users/{userID}")
	public List<Trade> retrieveUserTrades(@PathVariable(name="userID") Long id) {
//		return tradeRepository.findAllTrade_User_id(id);
		return tradeService.getAllTradesForUser(id);
	}
	
	@PostMapping("/trades")
	public ResponseEntity<Void> postTrade(@RequestBody Trade trade) {
		
//		Long id = tradeService.addTrade(trade);
//		if(id == null)
//			return ResponseEntity.badRequest().build();
		if(tradeRepository.exists(trade.getId())){
			return ResponseEntity.badRequest().build();
		}
		
		tradeRepository.save(trade);
		
		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(trade.getId()).toUri();
		
		return ResponseEntity.created(location).build();
	}
	
	@DeleteMapping("/erase")
	public void deleteAllTrades() {
//		tradeService.deleteAll();
		tradeRepository.deleteAll();
	}
		
}
