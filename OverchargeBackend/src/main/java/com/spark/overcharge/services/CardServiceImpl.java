package com.spark.overcharge.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spark.overcharge.dto.CardDto;
import com.spark.overcharge.entity.Card;
import com.spark.overcharge.repository.CardRepository;

@Service
public class CardServiceImpl implements CardService {

	@Autowired
	private CardRepository cardRepository;

	public List<CardDto> getAllCardsByUserId(Long userId) {
	    Optional<List<Card>> cardsOpt = cardRepository.findByUserId(userId);
	    if (cardsOpt.isPresent()) {
	        List<Card> cards = cardsOpt.get();
	        return cards.stream().map(Card::getDto).collect(Collectors.toList());
	    } else {
	        return new ArrayList<>();
	    }
	}
}
