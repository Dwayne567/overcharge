import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable, of } from 'rxjs';
import { catchError, tap } from 'rxjs/operators';
import { Deck } from '../model/deck';
import { Card } from '../model/card';
import { environment } from 'src/environments/environment';
import { UserStorageService } from 'src/app/auth/auth-services/storage-service/user-storage.service';


const BASIC_URL = environment['BASIC_URL'];

@Injectable({
  providedIn: 'root',
})
export class DeckService {

  constructor(private http: HttpClient) { }

  createDeck(deckDto: any): Observable<any> {
    return this.http.post(`${BASIC_URL}api/user/deck`, deckDto, {
      headers: this.createAuthorizationHeader(),
    })
  }

  getAllDecks(): Observable<any> {
    return this.http.get<[]>(`${BASIC_URL}api/user/decks`, {
      headers: this.createAuthorizationHeader(),
    }).pipe(
      tap((_) => this.log('Decks fetched successfully')),
      catchError(this.handleError<[]>('Error getting decks', []))
    );
  }

  getAllDecksByUserId(userId: any): Observable<any> {
    return this.http.get<[]>(`${BASIC_URL}api/user/decks/${userId}`, {
      headers: this.createAuthorizationHeader(),
    }).pipe(
      tap((_) => this.log('Decks fetched successfully')),
      catchError(this.handleError<[]>('Error getting decks', []))
    );
  }

  getDeckById(deckId: any): Observable<any> {
    return this.http.get<[]>(`${BASIC_URL}api/user/deck/${deckId}`, {
      headers: this.createAuthorizationHeader(),
    }).pipe(
      tap((_) => this.log('Deck Fetched successfully')),
      catchError(this.handleError<[]>('Error Getting deck', []))
    );
  }

  getCardsByDeckId(deckId: any): Observable<any> {
    return this.http.get<[]>(`${BASIC_URL}api/user/cards/${deckId}`, {
      headers: this.createAuthorizationHeader(),
    }).pipe(
      tap((_) => this.log('Deck Fetched successfully')),
      catchError(this.handleError<[]>('Error Getting deck', []))
    );
  }

  updateDeck(deck: Deck): Observable<Deck> {
    return this.http.put<Deck>(`${BASIC_URL}api/user/deck/${deck.id}`, deck, {
      headers: this.createAuthorizationHeader(),
    }).pipe(
      tap((updatedDeck: Deck) => this.log('Deck updated successfully')),
      catchError(this.handleError<Deck>('Error updating deck'))
    );
  }

  deleteDeckById(deckId: any): Observable<any> {
    return this.http.delete<[]>(`${BASIC_URL}api/user/deck/${deckId}`, {
      headers: this.createAuthorizationHeader(),
    }).pipe(
      tap((_) => this.log('Deck Deleted successfully')),
      catchError(this.handleError<[]>('Error Deleting deck', []))
    );
  }

  private createAuthorizationHeader(): HttpHeaders {
    return new HttpHeaders().set(
      'Authorization',
      'Bearer ' + UserStorageService.getToken()
    );
  }

  private log(message: string): void {
    console.log(`Deck Service: ${message}`);
  }

  private handleError<T>(operation = 'operation', result?: T): any {
    return (error: any): Observable<T> => {
      console.error(error);
      this.log(`${operation} failed: ${error.message}`);
      return of(result as T);
    };
  }
}
