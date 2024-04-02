package com.spark.overcharge.services;

import java.util.List;
import com.spark.overcharge.dto.DeckDto;

public interface DeckService {
	public DeckDto createDeck(DeckDto deckDto);
	
	public List<DeckDto> getAllDecks();
	
	public List<DeckDto> getAllDecksByUserId(Long userId);
	
    public DeckDto getDeckByDeckId(Long deckId);

    public DeckDto updateDeck(Long deckId, DeckDto deckDto);
    
    public boolean deleteDeck(Long id);
}