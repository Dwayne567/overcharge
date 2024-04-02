package com.spark.overcharge.controller;

import com.spark.overcharge.dto.DeckDto;
import com.spark.overcharge.services.DeckService;

import lombok.RequiredArgsConstructor;

import java.io.IOException;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/user")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:4200")
public class DeckController {
	
	private final DeckService deckService;
    
    @PostMapping("/deck")
    public ResponseEntity<DeckDto> createDeck(@RequestBody DeckDto deckDto){
    	DeckDto deck = deckService.createDeck(deckDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(deck);
    }

    @GetMapping("/decks")
    public ResponseEntity<List<DeckDto>> getAllDecks(){
        List<DeckDto> deckDtos = deckService.getAllDecks();
        return ResponseEntity.ok(deckDtos);
    }
    
    @GetMapping("/decks/{userId}")
    public ResponseEntity<List<DeckDto>> getAllDecksByUserId(@PathVariable Long userId){
        List<DeckDto> deckDtos = deckService.getAllDecksByUserId(userId);
        return ResponseEntity.ok(deckDtos);
    }
    
    @GetMapping("/deck/{deckId}")
    public ResponseEntity<DeckDto> getDeckById(@PathVariable Long deckId) {
        DeckDto deckDto = deckService.getDeckByDeckId(deckId);
        if(deckDto == null) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(deckDto);
    }
    
    @PutMapping("/deck/{deckId}")
    public ResponseEntity<DeckDto> updateDeck(@PathVariable Long deckId, @RequestBody DeckDto deckDto) throws IOException {
    	DeckDto updatedDeck = deckService.updateDeck(deckId, deckDto);
        if(updatedDeck != null){
            return ResponseEntity.ok(updatedDeck);
        }else{
            return ResponseEntity.notFound().build();
        }
    }
    
    @DeleteMapping("/deck/{deckId}")
    public ResponseEntity<Void> deleteDeck(@PathVariable Long deckId) {
        boolean deleted = deckService.deleteDeck(deckId);
        if(deleted){
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
    
}
