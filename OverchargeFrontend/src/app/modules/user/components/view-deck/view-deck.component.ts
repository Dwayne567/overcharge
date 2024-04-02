import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Deck } from '../../model/deck';
import { DeckService } from '../../service/deck.service';


@Component({
  selector: 'app-view-deck',
  templateUrl: './view-deck.component.html',
  styleUrls: ['./view-deck.component.scss']
})

export class ViewDeckComponent implements OnInit {
  deckId: number = 0;
  deck: Deck = {id: 0, userId: 0, title: '', cards: []};

  constructor(private route: ActivatedRoute, private deckService: DeckService) {}

  ngOnInit() {
    this.route.paramMap.subscribe((params) => {
      this.deckId = +params.get('deckId')!;
      this.loadDeck();
    });
  }

  loadDeck() {
    this.deckService.getDeckById(this.deckId).subscribe((retrievedDeck) => {
      retrievedDeck.cards.forEach((card: { flipped: boolean; }) => (card.flipped = false));
      this.deck = retrievedDeck;

    });
  }

  flipCard(card: any) {
    card.flipped = !card.flipped;
  }
}
