export class User {
  public id: number;
  public name: string;
  public email: string;
  public role: string;
  public img: string;

  constructor() {
    this.id = 0;
    this.name = '';
    this.email = '';
    this.role = '';
    this.img = '';
  }
}
