import { Component, OnInit } from '@angular/core';
import { Tenant } from 'src/app/app-state/models/tenant.model';
import { AuthStateService } from 'src/app/security/auth-state.service';
import { TenantService } from 'src/app/service/tenant.service';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent implements OnInit {

  isAuthenticated: boolean = false;
  tenants: Tenant[] = [];

  constructor(
    private authStateService: AuthStateService,
    private tenantService: TenantService
  ) { }

  ngOnInit(): void {
    this.authStateService.state$.subscribe(state => {
      this.isAuthenticated = state.isAuthenticated;
      if (this.isAuthenticated) {
        this.tenantService.getTenants().subscribe(tenants => {
          this.tenants = tenants;
        });
      }
    });
  }

}
