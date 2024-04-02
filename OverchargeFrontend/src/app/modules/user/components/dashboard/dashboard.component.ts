import { Component, OnInit } from '@angular/core';
import { Card } from '../../model/card';
import { CardService } from '../../service/card.service';
import { UserStorageService } from 'src/app/auth/auth-services/storage-service/user-storage.service';

@Component({
  selector: 'app-dashboard',
  templateUrl: './dashboard.component.html',
  styleUrls: ['./dashboard.component.scss']
})
export class DashboardComponent implements OnInit {
  cards: Card[] = [];

  constructor(private cardService: CardService) {}

  ngOnInit() {
    this.loadCards();
  }

  loadCards() {
    const userId = parseInt(UserStorageService.getUserId());
    this.cardService.getAllCards(userId).subscribe((retrievedCards) => {
      retrievedCards.forEach((card: { flipped: boolean; }) => (card.flipped = false));
      this.cards = retrievedCards;
    });
  }

  flipCard(card: any) {
    card.flipped = !card.flipped;
  }
}