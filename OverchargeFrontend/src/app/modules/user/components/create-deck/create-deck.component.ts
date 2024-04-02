import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { Deck } from '../../model/deck';
import { DeckService } from '../../service/deck.service';
import { AuthService } from 'src/app/auth/auth-services/auth-service/auth.service';
import { UserStorageService } from 'src/app/auth/auth-services/storage-service/user-storage.service';

@Component({
  selector: 'app-create-deck',
  templateUrl: './create-deck.component.html',
  styleUrls: ['./create-deck.component.scss']
})

export class CreateDeckComponent {
  deckId: number = 0;
  userId: number = 0;
  deckTitle: string = '';
  cards: Array<{ question: string; answer: string }> = [];

  newDeck: Deck = {id: 0, userId: 0, title: '', cards: []};

  constructor(private router: Router, private deckService: DeckService) { }

  addCard() {
    this.cards.push({ question: '', answer: '' });
  }

  removeCard(index: number) {
    this.cards.splice(index, 1);
  }

  createDeck() {

    this.newDeck.id = this.deckId;
    this.newDeck.userId =  parseInt(UserStorageService.getUserId());
    this.newDeck.title = this.deckTitle;
    this.newDeck.cards = this.cards;

    this.deckService.createDeck(this.newDeck).subscribe((response) => {
      this.newDeck = response;
      this.router.navigate(['/user/deck-list']);
    });
  }
}

