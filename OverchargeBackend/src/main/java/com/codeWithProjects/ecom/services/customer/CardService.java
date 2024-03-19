package com.codeWithProjects.ecom.services.customer;

import java.util.List;
import com.codeWithProjects.ecom.dto.customer.CardDto;

public interface CardService {
	public List<CardDto> getCardsByDeckId(Long deckId);
}
