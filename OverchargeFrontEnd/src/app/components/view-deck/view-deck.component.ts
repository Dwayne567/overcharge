import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Deck } from '../../models/deck';
import { DeckService } from '../../services/deck/deck-service.service';

@Component({
  selector: 'app-view-deck',
  templateUrl: './view-deck.component.html',
  styleUrls: ['./view-deck.component.css']
})

export class ViewDeckComponent implements OnInit {
  deckId: number = 0;
  deck: Deck = { title: '', cards: [] };

  constructor(private deckService: DeckService, private route: ActivatedRoute) {}

  ngOnInit() {
    this.route.paramMap.subscribe((params) => {
      this.deckId = +params.get('deckId')!;
      this.loadDeck();
    });
  }

  loadDeck() {
    this.deckService.getDeckById(this.deckId).subscribe((retrievedDeck) => {
      // Initialize the flipped property for each card
      retrievedDeck.cards.forEach((card) => (card.flipped = false));
      this.deck = retrievedDeck;
    });
  }

  flipCard(card: any) {
    // Toggle the flipped state of the clicked card
    card.flipped = !card.flipped;
  }
}
