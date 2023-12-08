package com.spark.millhouse.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.spark.millhouse.exception.ResourceNotFoundException;
import com.spark.millhouse.model.Card;
import com.spark.millhouse.model.Deck;
import com.spark.millhouse.repository.CardRepository;
import com.spark.millhouse.repository.DeckRepository;


@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/restapi")
public class DeckController {

	@Autowired
    private DeckRepository deckRepository;
	
	@Autowired
    private CardRepository cardRepository;
    
    @PostMapping("/createdeck")
    public Deck createDeck(@RequestBody Deck deck) {
		Deck createdDeck = deckRepository.save(deck);
        for (Card card : createdDeck.getCards()) {
            card.setDeck(createdDeck);
        }
        cardRepository.saveAll(createdDeck.getCards());
        return createdDeck;
    }

    @GetMapping("/getalldecks")
    public List<Deck> getAllDecks() {
        return deckRepository.findAll();
    }

    @GetMapping("/getdeck/{id}")
    public ResponseEntity<Deck> getDeckById(@PathVariable Long id) {
        Deck deck = deckRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Deck not exist with id :" + id));
        return ResponseEntity.ok(deck);
    }
    
    @PutMapping("/editdeck/{id}")
    public ResponseEntity<Deck> updateDeck(@PathVariable Long id, @RequestBody Deck updatedDeckData) {
    	deckRepository.deleteById(id);
    	Deck updatedDeck = deckRepository.save(updatedDeckData);
    	return ResponseEntity.ok(updatedDeck);
    }


    @DeleteMapping("/deletedeck/{id}")
    public ResponseEntity<Map<String, Boolean>> deleteDeck(@PathVariable Long id) {
        Deck deck = deckRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Deck not exist with id :" + id));
        deckRepository.delete(deck);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return ResponseEntity.ok(response);
    }
    
    @GetMapping("/getcards/{deckId}")
    public List<Card> getCardsByDeckId(@PathVariable Long deckId) {
        return cardRepository.findByDeckId(deckId);
    }
}
