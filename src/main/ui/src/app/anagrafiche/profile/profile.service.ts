import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root',
})
export class ProfileService {

  private rootURL = '/api';

  constructor(private http: HttpClient) {}

  getProfile(): Observable<any> {
    return this.http.get(this.rootURL + '/profile');
  }

  changePassword(oldPassword: string, newPassword: string): Observable<any> {
    return this.http.put(this.rootURL + '/profile/password', { oldPassword, newPassword }, { responseType: 'text' });
  }

  changeEmail(email: string): Observable<any> {
    return this.http.put(this.rootURL + '/profile/email', { email }, { responseType: 'text' });
  }

  register(username: string, password: string, email: string, fkComune: number): Observable<any> {
    return this.http.post(this.rootURL + '/register', { username, password, email, fkComune }, { responseType: 'text' });
  }
}
