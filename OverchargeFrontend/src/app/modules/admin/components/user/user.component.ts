import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Router } from '@angular/router';
import { UserStorageService } from 'src/app/auth/auth-services/storage-service/user-storage.service';
import { Deck } from 'src/app/modules/customer/model/deck';
import { DeckService } from 'src/app/modules/customer/service/deck.service';


@Component({
  selector: 'app-user',
  templateUrl: './user.component.html',
  styleUrls: ['./user.component.css']
})
export class UserComponent implements OnInit {
  decks: Deck[] = [];

  constructor(private router: Router, private route: ActivatedRoute, private deckService: DeckService) {
  }

  ngOnInit() {
    console.log('this.decks in ngInInit():', this.decks);
    this.loadDecks();
  }

  loadDecks() {
    const userId = parseInt(UserStorageService.getUserId());

    if (userId) {
      this.deckService.getAllDecksByUserId(userId).subscribe(response => {

        // Handle the response from the backend
        this.decks = response;
      });
    }
  }

  // onView(deck: Deck) {
  //   this.router.navigate(['customer/view-deck', deck.id]);
  // }

  // onEdit(deck: Deck) {
  //   this.router.navigate(['customer/edit-deck', deck.id]);
  // }

  onDelete(deck: Deck) {
    if (confirm(`Are you sure you want to delete "${deck.title}"?`)) {
      const deckId = deck.id ?? -1;
      this.deckService.deleteDeckById(deckId).subscribe(() => {
        this.loadDecks();
      });
    }
  }

}
