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
    this.loadUsers();
  }

  loadUsers() {
    this.userService.getAllUsers().subscribe(response => {
      this.users = response;
    });
  }
  onView(user: User) {
    this.router.navigate(['user/view-user', user.id]);
  }

  onEdit(user: User) {
    this.router.navigate(['user/edit-user', user.id]);
  }

  onDelete(user: User) {
    if (confirm(`Are you sure you want to delete "${user.email}"?`)) {
      this.userService.deleteUserById(user.id).subscribe(() => {
        this.loadUsers();
      });
    }
  }

}