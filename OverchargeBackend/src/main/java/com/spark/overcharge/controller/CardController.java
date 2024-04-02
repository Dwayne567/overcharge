package com.spark.overcharge.controller;

import com.spark.overcharge.dto.CardDto;
import com.spark.overcharge.services.CardService;

import lombok.RequiredArgsConstructor;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:4200")
public class CardController {
	
	private final CardService cardService;
    
	@GetMapping("/cards/user/{userId}")
	public ResponseEntity<List<CardDto>> getAllCardsByUserId(@PathVariable Long userId){
	    List<CardDto> cardDto = cardService.getAllCardsByUserId(userId);
	    if(cardDto != null){
	        return ResponseEntity.ok(cardDto);
	    }else{
	        return ResponseEntity.notFound().build();
	    }
	}
}
