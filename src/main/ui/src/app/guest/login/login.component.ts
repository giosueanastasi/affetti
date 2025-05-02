import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { AuthService } from 'src/app/security/auth.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  credentials = {username: '', password: ''};
  errorMessage: string = '';

  constructor(private authService: AuthService, private router: Router) {
  }

  login() {
    this.authService.login(this.credentials.username, this.credentials.password).subscribe(
      (response) => {
        this.authService.setToken(response.token);
        this.router.navigate(['/']);
      },
      (error) => {
        this.errorMessage = 'Credenziali non valide!';
      }
    );
  }

  ngOnInit(): void {
  }

}
