import { TestBed } from '@angular/core/testing';
import { CanActivate, ActivatedRouteSnapshot, RouterStateSnapshot, Router } from '@angular/router';

import { AuthGuard } from './auth-guard.guard';

describe('AuthGuard', () => {
  let guard: AuthGuard;

  beforeEach(() => {
    TestBed.configureTestingModule({
      providers: [AuthGuard, Router], // Ajoutez le Router aux providers
    });
    guard = TestBed.inject(AuthGuard); // Utilisez TestBed.inject pour obtenir l'instance du guard
  });

  it('should be created', () => {
    expect(guard).toBeTruthy();
  });

  
});
