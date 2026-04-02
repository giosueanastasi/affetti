import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { ProfileService } from 'src/app/anagrafiche/profile/profile.service';
import { AppService } from 'src/app/app.service';

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.css']
})
export class RegisterComponent implements OnInit {

  username: string = '';
  password: string = '';
  confirmPassword: string = '';
  email: string = '';
  fkComune: number = 0;
  comuniList: any[] = [];
  filteredComuni: any[] = [];
  comuneSearch: string = '';

  errorMessage: string = '';
  successMessage: string = '';

  constructor(
    private profileService: ProfileService,
    private appService: AppService,
    private router: Router
  ) {}

  ngOnInit(): void {
    this.appService.getComuni().subscribe((data: any) => {
      this.comuniList = data.comuni || [];
    });
  }

  filterComuni(): void {
    if (this.comuneSearch.length < 2) {
      this.filteredComuni = [];
      return;
    }
    const search = this.comuneSearch.toLowerCase();
    this.filteredComuni = this.comuniList.filter(
      (c: any) => c.nome.toLowerCase().includes(search)
    ).slice(0, 20);
  }

  selectComune(comune: any): void {
    this.fkComune = comune.id;
    this.comuneSearch = comune.nome;
    this.filteredComuni = [];
  }

  register(): void {
    this.errorMessage = '';
    this.successMessage = '';

    if (!this.username || this.username.trim() === '') {
      this.errorMessage = 'Username obbligatorio';
      return;
    }
    if (!this.password || this.password.length < 8) {
      this.errorMessage = 'La password deve essere di almeno 8 caratteri';
      return;
    }
    if (this.password !== this.confirmPassword) {
      this.errorMessage = 'Le password non coincidono';
      return;
    }
    if (!this.fkComune) {
      this.errorMessage = 'Seleziona un comune';
      return;
    }

    this.profileService.register(this.username, this.password, this.email, this.fkComune).subscribe(
      (response) => {
        this.successMessage = 'Registrazione completata! Verrai reindirizzato al login...';
        setTimeout(() => {
          this.router.navigate(['/login']);
        }, 2000);
      },
      (error) => {
        this.errorMessage = error.error || 'Errore durante la registrazione';
      }
    );
  }
}
