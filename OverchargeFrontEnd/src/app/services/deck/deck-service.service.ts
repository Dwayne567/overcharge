import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Deck } from '../../models/deck';
import { Card } from '../../models/card';

@Injectable({
  providedIn: 'root',
})
export class DeckService {
  private apiUrl = 'http://localhost:8080/restapi';

  constructor(private http: HttpClient) { }

  createDeck(deck: Deck): Observable<Deck> {
    const createUrl = `${this.apiUrl}/createdeck`;
    return this.http.post<Deck>(createUrl, deck);
  }

  getAllDecks(): Observable<Deck[]> {
    const getUrl = `${this.apiUrl}/getalldecks`;
    return this.http.get<Deck[]>(getUrl);
  }

  getDeckById(deckId: number): Observable<Deck> {
    const getUrl = `${this.apiUrl}/getdeck/${deckId}`;
    return this.http.get<Deck>(getUrl);
  }

  updateDeck(deck: Deck): Observable<Deck> {
    const updateUrl = `${this.apiUrl}/editdeck/${deck.id}`;
    return this.http.put<Deck>(updateUrl, deck);
  }

  deleteDeck(deckId: number): Observable<any> {
    const deleteUrl = `${this.apiUrl}/deletedeck/${deckId}`;
    return this.http.delete(deleteUrl);
  }

  getCardsByDeckId(deckId: number): Observable<Card[]> {
    const getCardsUrl = `${this.apiUrl}/getcards/${deckId}`;
    return this.http.get<Card[]>(getCardsUrl);
  }
}
