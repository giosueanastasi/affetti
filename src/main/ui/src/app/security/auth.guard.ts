import { inject } from '@angular/core';
import { Router, CanActivateFn } from '@angular/router';
import { AuthStateService } from './auth-state.service';

export const authGuard: CanActivateFn = () => {
  const authState = inject(AuthStateService);
  const router = inject(Router);

  if (authState.isAuthenticated) {
    return true;
  }
  
  return router.createUrlTree(['/login']);
};

export const roleGuard = (allowedRoles: string[]): CanActivateFn => {
  return () => {
    const authState = inject(AuthStateService);
    const router = inject(Router);

    if (authState.hasAnyRole(allowedRoles)) {
      return true;
    }

    return router.createUrlTree(['/unauthorized']);
  };
};