import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Router } from '@angular/router';
import { UserStorageService } from 'src/app/auth/auth-services/storage-service/user-storage.service';
import { UserService } from 'src/app/modules/admin/service/user.service';
import { User } from '../../model/user';


@Component({
  selector: 'app-dashboard',
  templateUrl: './dashboard.component.html',
  styleUrls: ['./dashboard.component.scss']
})
export class DashboardComponent {
  users: User[] = [];

  constructor(private router: Router, private route: ActivatedRoute, private userService: UserService) {
  }

  ngOnInit() {
    console.log('this.users in ngInInit():', this.users);
    this.loadUsers();
  }

  loadUsers() {
    this.userService.getAllUsers().subscribe(response => {
      // Handle the response from the backend
      this.users = response;
    });
  }
  onView(user: User) {
    this.router.navigate(['customer/view-user', user.id]);
  }

  onEdit(user: User) {
    this.router.navigate(['customer/edit-user', user.id]);
  }

  // onDelete(user: User) {
  //   if (confirm(`Are you sure you want to delete "${user.email}"?`)) {
  //     const userId = parseInt(UserStorageService.getUserId());
  //     this.userService.deleteUserById(userId).subscribe(() => {
  //       this.loadUsers();
  //     });
  //   }
  // }


  onDelete(user: User) {
    console.log(user); // Add this line
    if (confirm(`Are you sure you want to delete "${user.email}"?`)) {
      this.userService.deleteUserById(user.id).subscribe(() => {
        this.loadUsers();
      });
    }
  }

}