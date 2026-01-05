import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable, tap } from 'rxjs';
import { AuthStateService } from './auth-state.service';

@Injectable({
  providedIn: 'root',
})
export class AuthService {
  private apiUrl = 'api/login';

  constructor(private http: HttpClient, private authState: AuthStateService) {}

  login(username: string, password: string): Observable<any> {
    return this.http.post<any>(this.apiUrl, { username, password }).pipe(
      tap(response => {
        if (response.token) {
          localStorage.setItem('jwt', response.token);
          this.authState.setAuthenticated(response.token);
        }
      })
    );
  }

  logout(): void {
    localStorage.removeItem('jwt');
    this.authState.clearAuthentication();
  }

}
