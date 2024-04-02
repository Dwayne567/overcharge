package com.spark.overcharge.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spark.overcharge.dto.CardDto;
import com.spark.overcharge.dto.DeckDto;
import com.spark.overcharge.entity.Card;
import com.spark.overcharge.entity.Deck;
import com.spark.overcharge.entity.User;
import com.spark.overcharge.exceptions.CardNotFoundException;
import com.spark.overcharge.exceptions.DeckNotFoundException;
import com.spark.overcharge.exceptions.UserNotFoundException;
import com.spark.overcharge.repository.CardRepository;
import com.spark.overcharge.repository.DeckRepository;
import com.spark.overcharge.repository.UserRepository;

import jakarta.transaction.Transactional;

@Service
public class DeckServiceImpl implements DeckService {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private DeckRepository deckRepository;

	@Autowired
	private CardRepository cardRepository;
	
	@Transactional
	public DeckDto createDeck(DeckDto deckDto) {
	    User user = userRepository.findById(deckDto.getUserId())
	        .orElseThrow(() -> new UserNotFoundException(deckDto.getUserId()));
	    
	    Deck deck = new Deck();
	    deck.setUser(user);
	    deck.setTitle(deckDto.getTitle());

	    List<Card> cards = deckDto.getCards().stream()
	        .map(cardDto -> {
	            Card card = new Card();
	            card.setQuestion(cardDto.getQuestion());
	            card.setAnswer(cardDto.getAnswer());
	            card.setDeck(deck); // set the deck to the card
	            card.setUser(user); // set the user to the card
	            return card;
	        })
	        .collect(Collectors.toList());

	    deck.setCards(cards); // set the list of Card objects

	    Deck savedDeck = deckRepository.save(deck);

	    // Convert the saved Deck object to a DeckDto object
	    DeckDto savedDeckDto = new DeckDto();
	    savedDeckDto.setId(savedDeck.getId());
	    savedDeckDto.setTitle(savedDeck.getTitle());
	    savedDeckDto.setUserId(savedDeck.getUser().getId());
	    savedDeckDto.setCards(savedDeck.getCards().stream()
	        .map(card -> {
	            CardDto cardDto = new CardDto();
	            cardDto.setId(card.getId());
	            cardDto.setQuestion(card.getQuestion());
	            cardDto.setAnswer(card.getAnswer());
	            return cardDto;
	        })
	        .collect(Collectors.toList()));

	    return savedDeckDto;
	}

	public List<DeckDto> getAllDecks() {
		List<Deck> decks = deckRepository.findAll();
		return decks.stream().map(Deck::getDto).collect(Collectors.toList());
	}

	public List<DeckDto> getAllDecksByUserId(Long userId) {
		List<Deck> decks = deckRepository.findByUserId(userId);
		return decks.stream().map(Deck::getDto).collect(Collectors.toList());
	}

	public DeckDto getDeckByDeckId(Long deckId) {
		Optional<Deck> optionalDeck = deckRepository.findById(deckId);
		if (optionalDeck.isPresent()) {
			Deck deck = optionalDeck.get();
			DeckDto deckDto = deck.getDto();
			List<Card> cards = cardRepository.findByDeckId(deckId).orElse(null);
			if (cards != null) {
				List<CardDto> cardDtos = cards.stream().map(Card::getDto).collect(Collectors.toList());
				deckDto.setCards(cardDtos);
			}
			return deckDto;
		} else {
			return null;
		}
	}
	
	@Transactional
	public DeckDto updateDeck(Long deckId, DeckDto deckDto) {
	    // Retrieve the existing deck from the repository
	    Deck existingDeck = deckRepository.findById(deckId)
	            .orElseThrow(() -> new DeckNotFoundException(deckId));

	    // Update the title of the existing deck with the value from the DTO
	    existingDeck.setTitle(deckDto.getTitle());

	    // If the DTO contains cards, update or create them
	    if (deckDto.getCards() != null) {
	        List<Card> updatedCards = new ArrayList<>();
	        for (CardDto cardDto : deckDto.getCards()) {
	            Card card;
	            // Check if the card already exists in the deck
	            if (cardDto.getId() != null) {
	                // If the card exists, retrieve it from the repository
	                card = cardRepository.findById(cardDto.getId())
	                        .orElseThrow(() -> new CardNotFoundException(cardDto.getId()));
	                // Update the question and answer of the existing card
	                card.setQuestion(cardDto.getQuestion());
	                card.setAnswer(cardDto.getAnswer());
	            } else {
	                // If the card doesn't exist, create a new one
	                card = new Card();
	                card.setDeck(existingDeck); // set the deck to the card
	                card.setUser(existingDeck.getUser()); // set the user to the card
	                card.setQuestion(cardDto.getQuestion());
	                card.setAnswer(cardDto.getAnswer());
	            }
	            updatedCards.add(card);
	        }

	        // Find cards that are in the existing deck but not in the updated deck
	        List<Card> cardsToRemove = existingDeck.getCards().stream()
	                .filter(card -> !updatedCards.contains(card))
	                .collect(Collectors.toList());

	        // Remove the cards from the existing deck
	        existingDeck.getCards().removeAll(cardsToRemove);

	        // Delete the cards from the repository
	        for (Card card : cardsToRemove) {
	            cardRepository.delete(card);
	        }

	        // Set the updated list of cards to the existing deck
	        existingDeck.setCards(updatedCards);
	    }

	    // Save the updated deck
	    Deck updatedDeck = deckRepository.save(existingDeck);

	    return updatedDeck.getDto();
	}

	public boolean deleteDeck(Long id) {
		Optional<Deck> optionalDeck = deckRepository.findById(id);
		if (optionalDeck.isPresent()) {
			deckRepository.deleteById(id);
			return true;
		}
		return false;
	}

}
