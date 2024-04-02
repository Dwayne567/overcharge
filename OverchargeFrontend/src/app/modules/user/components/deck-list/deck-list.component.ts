import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Router } from '@angular/router';
import { Deck } from '../../model/deck';
import { DeckService } from '../../service/deck.service';
import { UserStorageService } from 'src/app/auth/auth-services/storage-service/user-storage.service';


@Component({
  selector: 'app-deck-list',
  templateUrl: './deck-list.component.html',
  styleUrls: ['./deck-list.component.scss'],
})
export class DeckListComponent implements OnInit {
  decks: Deck[] = [];

  constructor(private router: Router, private route: ActivatedRoute, private deckService: DeckService) {
  }

  ngOnInit() {
    this.loadDecks();
  }

  loadDecks() {
    const userId = parseInt(UserStorageService.getUserId());

    if (userId) {
      this.deckService.getAllDecksByUserId(userId).subscribe(response => {
        this.decks = response;
      });
    }
  }

  onView(deck: Deck) {
    this.router.navigate(['user/view-deck', deck.id]);
  }

  onEdit(deck: Deck) {
    this.router.navigate(['user/edit-deck', deck.id]);
  }

  onDelete(deck: Deck) {
    if (confirm(`Are you sure you want to delete "${deck.title}"?`)) {
      const deckId = deck.id ?? -1;
      this.deckService.deleteDeckById(deckId).subscribe(() => {
        this.loadDecks();
      });
    }
  }

}
