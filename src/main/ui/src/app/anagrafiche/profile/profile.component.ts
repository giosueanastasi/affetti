import { Component, OnInit } from '@angular/core';
import { ProfileService } from './profile.service';

@Component({
  selector: 'app-profile',
  templateUrl: './profile.component.html',
  styleUrls: ['./profile.component.css']
})
export class ProfileComponent implements OnInit {

  username: string = '';
  email: string = '';
  roles: string[] = [];

  oldPassword: string = '';
  newPassword: string = '';
  confirmPassword: string = '';

  emailMessage: string = '';
  emailError: boolean = false;
  passwordMessage: string = '';
  passwordError: boolean = false;

  constructor(private profileService: ProfileService) {}

  ngOnInit(): void {
    this.loadProfile();
  }

  loadProfile(): void {
    this.profileService.getProfile().subscribe(
      (data) => {
        this.username = data.username;
        this.email = data.email || '';
        this.roles = data.roles;
      }
    );
  }

  saveEmail(): void {
    this.emailMessage = '';
    this.emailError = false;
    this.profileService.changeEmail(this.email).subscribe(
      (response) => {
        this.emailMessage = 'Email aggiornata con successo';
        this.emailError = false;
      },
      (error) => {
        this.emailMessage = error.error || 'Errore durante l\'aggiornamento dell\'email';
        this.emailError = true;
      }
    );
  }

  changePassword(): void {
    this.passwordMessage = '';
    this.passwordError = false;

    if (this.newPassword !== this.confirmPassword) {
      this.passwordMessage = 'Le password non coincidono';
      this.passwordError = true;
      return;
    }
    if (this.newPassword.length < 8) {
      this.passwordMessage = 'La nuova password deve essere di almeno 8 caratteri';
      this.passwordError = true;
      return;
    }

    this.profileService.changePassword(this.oldPassword, this.newPassword).subscribe(
      (response) => {
        this.passwordMessage = 'Password aggiornata con successo';
        this.passwordError = false;
        this.oldPassword = '';
        this.newPassword = '';
        this.confirmPassword = '';
      },
      (error) => {
        this.passwordMessage = error.error || 'Errore durante l\'aggiornamento della password';
        this.passwordError = true;
      }
    );
  }
}
