import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { AuthService } from '../security/auth.service';
import { AuthStateService } from '../security/auth-state.service';
import { Roles } from '../app-state/enum/roles.enum';


@Component({
  selector: 'app-side-bar',
  templateUrl: './side-bar.component.html',
  styleUrls: ['./side-bar.component.css']
})
export class SideBarComponent {

  Roles = Roles;
  constructor(private router: Router, public authService: AuthService, public authState: AuthStateService) { }

}
