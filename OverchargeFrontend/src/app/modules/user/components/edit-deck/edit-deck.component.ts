import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Router } from '@angular/router';
import { DeckService } from '../../service/deck.service';
import { Deck } from '../../model/deck';
import { UserStorageService } from 'src/app/auth/auth-services/storage-service/user-storage.service';

@Component({
  selector: 'app-edit-deck',
  templateUrl: './edit-deck.component.html',
  styleUrls: ['./edit-deck.component.scss']
})
export class EditDeckComponent implements OnInit {
  deckId: number = 0;
  deck: Deck = { id: 0, userId: 0, title: '', cards: [] };
  newDeck: Deck = { id: 0, userId: 0, title: '', cards: [] };

  constructor(private router: Router, private route: ActivatedRoute, private deckService: DeckService) { }

  ngOnInit() {
    this.route.paramMap.subscribe((params) => {
      this.deckId = +params.get('id')!;
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
    this.newDeck.id = this.deck.id;
    this.newDeck.userId = parseInt(UserStorageService.getUserId());
    this.newDeck.title = this.deck.title;
    this.newDeck.cards = this.deck.cards;

    this.deckService.updateDeck(this.newDeck).subscribe((updatedDeck) => {
      this.router.navigate(['user/deck-list']);
    });
  }
}
