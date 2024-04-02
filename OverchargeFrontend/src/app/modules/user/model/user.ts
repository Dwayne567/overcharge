export class User {
    id?: number;
    name?: string;
    oldPassword?: string;
    newPassword?: string;
  
    constructor(id?: number, name?: string, oldPassword?: string, newPassword?: string) {
      this.id = id;
      this.name = name;
      this.oldPassword = oldPassword;
      this.newPassword = newPassword;
    }
  }