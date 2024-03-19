import { Card } from '../model/card';

export class Deck {
  id: number;
  userId: number;
  title: string;
  cards: Card[];

  constructor(id: number, userId: number, title: string, cards: Card[]) {
    this.id = id;
    this.userId = userId;
    this.title = title;
    this.cards = cards;
  }
}