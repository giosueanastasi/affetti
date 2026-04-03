import { Injectable } from '@angular/core';
import { BehaviorSubject, Observable } from 'rxjs';
import { map } from 'rxjs/operators';

export interface AuthState {
  isAuthenticated: boolean;
  username: string | null;
  roles: string[];
  tenantId: number | null;
  tenantDescrizione: string | null;
}

@Injectable({
  providedIn: 'root'
})
export class AuthStateService {
  
  private readonly authState$ = new BehaviorSubject<AuthState>({
    isAuthenticated: false,
    username: null,
    roles: [],
    tenantId: null,
    tenantDescrizione: null
  });

  constructor() {
    this.loadFromToken();
  }

  // Observable pubblici per i componenti
  get state$(): Observable<AuthState> {
    return this.authState$.asObservable();
  }

  get isAuthenticated$(): Observable<boolean> {
    return this.authState$.pipe(map(state => state.isAuthenticated));
  }

  get username$(): Observable<string | null> {
    return this.authState$.pipe(map(state => state.username));
  }

  get roles$(): Observable<string[]> {
    return this.authState$.pipe(map(state => state.roles));
  }

  // Getter sincroni per quando servono valori immediati
  get isAuthenticated(): boolean {
    return this.authState$.value.isAuthenticated;
  }

  get username(): string | null {
    return this.authState$.value.username;
  }

  get roles(): string[] {
    return this.authState$.value.roles;
  }

  // Verifica ruoli
  hasRole(role: string): boolean {
    return this.roles.includes(role);
  }

  hasAnyRole(roles: string[]): boolean {
    return roles.some(role => this.hasRole(role));
  }

  hasAllRoles(roles: string[]): boolean {
    return roles.every(role => this.hasRole(role));
  }

  // Observable per verifica ruoli (utile nei template con async pipe)
  hasRole$(role: string): Observable<boolean> {
    return this.roles$.pipe(map(roles => roles.includes(role)));
  }

  hasAnyRole$(roles: string[]): Observable<boolean> {
    return this.roles$.pipe(map(userRoles => roles.some(r => userRoles.includes(r))));
  }

  get tenantDescrizione(): string | null {
    return this.authState$.value.tenantDescrizione;
  }

  get tenantDescrizione$(): Observable<string | null> {
    return this.authState$.pipe(map(state => state.tenantDescrizione));
  }

  // Chiamato dopo login
  setAuthenticated(token: string): void {
    const payload = this.decodeToken(token);
    if (payload) {
      this.authState$.next({
        isAuthenticated: true,
        username: payload.sub || null,
        roles: payload.roles || [],
        tenantId: payload.tenantId || null,
        tenantDescrizione: payload.tenantDescrizione || null
      });
    }
  }

  // Chiamato al logout
  clearAuthentication(): void {
    this.authState$.next({
      isAuthenticated: false,
      username: null,
      roles: [],
      tenantId: null,
      tenantDescrizione: null
    });
  }

  // Carica stato dal token in localStorage (per refresh pagina)
  private loadFromToken(): void {
    const token = localStorage.getItem('jwt');
    if (token) {
      this.setAuthenticated(token);
    }
  }

  private decodeToken(token: string): any {
    try {
      const payload = token.split('.')[1];
      return JSON.parse(atob(payload));
    } catch {
      return null;
    }
  }
}