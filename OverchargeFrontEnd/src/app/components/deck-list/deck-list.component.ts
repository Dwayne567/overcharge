import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { Deck } from '../../models/deck';
import { DeckService } from '../../services/deck/deck-service.service';

@Component({
  selector: 'app-deck-list',
  templateUrl: './deck-list.component.html',
  styleUrls: ['./deck-list.component.css'],
})
export class DeckListComponent implements OnInit {
  decks: Deck[] = [];

  constructor(private deckService: DeckService, private router: Router) {}

  ngOnInit() {
    this.loadDecks();
  }

  loadDecks() {
    this.deckService.getAllDecks().subscribe((decks: Deck[]) => {
      this.decks = decks;
    });
  }

  onView(deck: Deck) {
    this.router.navigate(['/view-deck', deck.id]);
  }

  onEdit(deck: Deck) {
    this.router.navigate(['/edit-deck', deck.id]);
  }

  onDelete(deck: Deck) {
    if (confirm(`Are you sure you want to delete "${deck.title}"?`)) {
      const deckId = deck.id ?? -1;
      this.deckService.deleteDeck(deckId).subscribe(() => {
        this.loadDecks();
      });
    }
  }
}
