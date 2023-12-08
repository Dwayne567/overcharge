import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { Deck } from '../../models/deck';
import { DeckService } from '../../services/deck/deck-service.service';

@Component({
  selector: 'app-create-deck',
  templateUrl: './create-deck.component.html',
  styleUrls: ['./create-deck.component.css']
})

export class CreateDeckComponent {
  deckTitle: string = '';
  newDeck: Deck = { title: '', cards: [] };
  cards: Array<{ question: string; answer: string }> = [];

  constructor(private deckService: DeckService, private router: Router) { }

  addCard() {
    this.cards.push({ question: '', answer: '' });
  }

  removeCard(index: number) {
    this.cards.splice(index, 1);
  }

  createDeck() {
    // Set the 'title' and 'cards' properties of the 'newDeck' instance
    this.newDeck.title = this.deckTitle;
    this.newDeck.cards = this.cards;
    // Pass the 'newDeck' instance to the createDeck function
    this.deckService.createDeck(this.newDeck).subscribe((createdDeck) => {
      console.log('Created deck:', createdDeck);
      // Handle the response from the backend as needed
      this.newDeck = createdDeck; // Update the newDeck with the ID assigned by the backend
      this.router.navigate(['/deck-list']);
    });
  }
}

