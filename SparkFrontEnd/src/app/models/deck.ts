import { Card } from '../models/card';

export class Deck {
  id?: number; // Make the 'id' property optional
  title: string;
  cards: Card[];

  constructor(title: string, cards: Card[], id?: number) {
    this.id = id;
    this.title = title;
    this.cards = cards;
  }
}