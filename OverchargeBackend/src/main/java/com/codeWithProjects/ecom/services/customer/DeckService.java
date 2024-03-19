package com.codeWithProjects.ecom.services.customer;

import java.util.List;

import com.codeWithProjects.ecom.dto.customer.DeckDto;
import com.codeWithProjects.ecom.entity.Deck;

public interface DeckService {
	public Deck createDeck(DeckDto deckDto);
	
	public List<DeckDto> getAllDecks();
	
	public List<DeckDto> getDeckByUserId(Long userId);
	
    public DeckDto getDeckById(Long deckId);

    public DeckDto updateDeck(Long deckId, DeckDto deckDto);
    
    public boolean deleteDeck(Long id);
}
