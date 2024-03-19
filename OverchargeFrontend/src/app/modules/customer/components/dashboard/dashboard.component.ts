import { Component } from '@angular/core';
import { FormGroup, FormBuilder, Validators } from '@angular/forms';
import { MatSnackBar } from '@angular/material/snack-bar';
import { CustomerService } from '../../service/customer.service';
import { DeckService } from '../../service/deck.service';

@Component({
  selector: 'app-dashboard',
  templateUrl: './dashboard.component.html',
  styleUrls: ['./dashboard.component.scss']
})
export class DashboardComponent {

  decks: any[] = [];
  searchProductForm!: FormGroup;
  isSpinning = false;

  constructor(
    private customerService: CustomerService,
    private deckService: DeckService,
    private snackBar: MatSnackBar,
    private fb: FormBuilder
  ) { }

  ngOnInit(): void {
    this.getAllDecks();
  }



  getAllDecks(): void {
    this.isSpinning = true;
    this.decks = [];
    this.deckService.getAllDecks().subscribe((res) => {
      res.forEach(element => {
        this.decks.push(element);
      });
      this.isSpinning = false;
    });
  }

}
