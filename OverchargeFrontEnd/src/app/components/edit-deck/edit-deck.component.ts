import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Router } from '@angular/router';
import { Deck } from '../../models/deck';
import { DeckService } from '../../services/deck/deck-service.service';

@Component({
  selector: 'app-edit-deck',
  templateUrl: './edit-deck.component.html',
  styleUrls: ['./edit-deck.component.css']
})
export class EditDeckComponent implements OnInit {
  deckId!: number;
  deck: Deck = { title: '', cards: [] };

  constructor(private deckService: DeckService, private route: ActivatedRoute, private router: Router) { }

  ngOnInit() {
    this.route.paramMap.subscribe((params) => {
      this.deckId = +params.get('id')!;
      console.log('Loaded deck ID:', this.deckId);
      this.loadDeck();
    });
  }

  loadDeck() {
    this.deckService.getDeckById(this.deckId).subscribe((retrievedDeck) => {
      this.deck = retrievedDeck;
    });
  }

  addCard() {
    this.deck.cards.push({ question: '', answer: '' });
  }

  removeCard(index: number) {
    this.deck.cards.splice(index, 1);
  }

  updateDeck() {
    this.deckService.updateDeck(this.deck).subscribe((updatedDeck) => {
      console.log('Updated deck:', updatedDeck);
      this.router.navigate(['/deck-list']);
    });
  }
}
