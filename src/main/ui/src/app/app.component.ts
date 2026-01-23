import { Component, OnDestroy } from '@angular/core';
import { FormGroup, FormControl, Validators } from '@angular/forms';
import { AppService } from './app.service';
import { finalize, takeUntil } from 'rxjs/operators';
import { Subject } from 'rxjs';
import { HttpClient } from '@angular/common/http';
import { Router } from '@angular/router';
import { AuthService } from './security/auth.service';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent  {

  constructor(public app: AppService, private router: Router, public authService: AuthService ) {}

  title = 'angular-nodejs-example';
  currentYear = new Date().getFullYear();

}
