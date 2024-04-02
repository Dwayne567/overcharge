import { Component } from '@angular/core';
import { FormGroup, FormBuilder, Validators } from '@angular/forms';
import { MatSnackBar } from '@angular/material/snack-bar';
import { Router } from '@angular/router';
import { AuthService } from 'src/app/auth/auth-services/auth-service/auth.service';
import { UserStorageService } from 'src/app/auth/auth-services/storage-service/user-storage.service';
import { User } from '../../model/user';

@Component({
  selector: 'app-change-password',
  templateUrl: './change-password.component.html',
  styleUrls: ['./change-password.component.scss']
})
export class ChangePasswordComponent {

  changePasswordForm!: FormGroup;
  isSpinning = false;
  hideOldPassword = true;
  hideNewPassword = true;
  hideConfirmPassword = true;
  
  constructor(
    private fb: FormBuilder,
    private snackBar: MatSnackBar,
    private authService: AuthService,
    private router: Router) { }

  ngOnInit(): void {
    this.changePasswordForm = this.fb.group({
      oldPassword: [null, [Validators.required]],
      newPassword: [null, [Validators.required]],
      confirmPassword: [null, [Validators.required]],
    });
  }

  onSubmit(): void {

    if (this.changePasswordForm.valid) {
      const oldPassword = this.changePasswordForm.get('oldPassword')?.value;
      const newPassword = this.changePasswordForm.get('newPassword')?.value;
      const confirmPassword = this.changePasswordForm.get('confirmPassword')?.value;

      if (newPassword !== confirmPassword) {
        this.snackBar.open('Passwords do not match.', 'Close', { duration: 5000, panelClass: 'error-snackbar' });
        return;
      }

      this.isSpinning = true;
      const user = new User(
        parseInt(UserStorageService.getUserId(), 10),
        null,
        oldPassword,
        newPassword
      );

      this.authService.updatePassword(user).subscribe(
        (response) => {
          this.isSpinning = false;
          if (response.id != null) {
            this.snackBar.open('Password Changed successfully', 'Close', { duration: 5000 });
          } else {
            this.snackBar.open('Incorrect old password', 'Close', { duration: 5000 });
            this.changePasswordForm.reset();
          }
          this.router.navigate(['user/profile']);

        },
        (error) => {
          this.isSpinning = false;
          this.snackBar.open('Failed. Please try again.', 'Close', { duration: 5000, panelClass: 'error-snackbar' });
        }
      );
    } else {
      for (const i in this.changePasswordForm.controls) {
        this.changePasswordForm.controls[i].markAsDirty();
        this.changePasswordForm.controls[i].updateValueAndValidity();
      }
    }
  }
}
