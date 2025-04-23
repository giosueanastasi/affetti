import { Component, OnDestroy } from '@angular/core';
import { FormGroup, FormControl, Validators } from '@angular/forms';
import { AppService } from './app.service';
import { finalize, takeUntil } from 'rxjs/operators';
import { Subject } from 'rxjs';
import { HttpClient } from '@angular/common/http';
import { Router } from '@angular/router';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent  {

  constructor(private app: AppService, private http: HttpClient, private router: Router) {
    this.app.authenticate(undefined, undefined);
  }

  logout() {
    this.http.post('logout', {}).pipe(
      finalize(() => {
        this.app.authenticated = false;
        this.router.navigateByUrl('/login');
      })
    ).subscribe();
  }

  title = 'angular-nodejs-example';

  


}
