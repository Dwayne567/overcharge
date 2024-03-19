export class Card {
    question: string;
    answer: string;
    flipped?: boolean; // Boolean property to track whether the card is flipped
  
  
    constructor(question: string, answer: string) {
      this.question = question;
      this.answer = answer;
      this.flipped = false;
    }
  }