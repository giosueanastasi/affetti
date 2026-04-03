import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { AuthService } from '../security/auth.service';

interface MockUser {
  username: string;
  password: string;
  description?: string;
}

@Component({
  selector: 'app-loginbackdoor',
  templateUrl: './loginbackdoor.component.html',
  styleUrls: ['./loginbackdoor.component.css']
})
export class LoginbackdoorComponent {
  mockUsers: MockUser[] = [
    { username: 'Antonio90', password: 'Antonio90', description: 'Utente standard' },
    { username: 'Stefano24', password: 'Stefano24', description: 'Utente standard' },
    { username: 'Giovanna98', password: 'Giovanna98', description: 'Utente standard' },
    { username: 'user', password: 'user', description: 'Ruolo USER' },
    { username: 'admin_napoli', password: 'admin_napoli', description: 'ADMIN Tenant Napoli' },
    { username: 'operator', password: 'operator', description: 'Ruolo OPERATOR' },
    { username: 'superadmin', password: 'superadmin', description: 'Ruolo SUPERADMIN' },
    { username: 'admin_roma', password: 'admin_roma', description: 'ADMIN Tenant Roma' },
    { username: 'admin_milano', password: 'admin_milano', description: 'ADMIN Tenant Milano' }
  ];

  isLoading: string | null = null;
  errorMessage: string = '';

  constructor(
    private authService: AuthService,
    private router: Router
  ) {}

  loginAs(user: MockUser): void {
    this.isLoading = user.username;
    this.errorMessage = '';

    this.authService.login(user.username, user.password).subscribe({
      next: () => {
        this.router.navigate(['/home']);
      },
      error: (error) => {
        this.isLoading = null;
        this.errorMessage = `Errore login per ${user.username}: ${error.message || 'Credenziali non valide'}`;
      }
    });
  }
}




