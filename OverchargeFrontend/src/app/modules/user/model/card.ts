export class Card {
    question: string;
    answer: string;
    flipped?: boolean;
  
  
    constructor(question: string, answer: string) {
      this.question = question;
      this.answer = answer;
      this.flipped = false;
    }
  }