import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { AppService } from '../app.service';
import { AuthService } from '../security/auth.service';
import { AuthStateService } from '../security/auth-state.service';
import { Roles } from '../app-state/enum/roles.enum';

@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.css']
})
export class HeaderComponent implements OnInit {

  Roles = Roles;

  constructor(public app: AppService, private router: Router, public authService: AuthService, public authState: AuthStateService) { }

  ngOnInit(): void {
  }

  logout() {
    this.authService.logout();
    this.router.navigate(['/home']);
  }

}
