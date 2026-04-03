import { Component, Input } from '@angular/core';
import { Router } from '@angular/router';
import { Tenant } from 'src/app/app-state/models/tenant.model';

@Component({
  selector: 'app-tenant-card',
  templateUrl: './tenant-card.component.html',
  styleUrls: ['./tenant-card.component.css']
})
export class TenantCardComponent {

  @Input() tenant: Tenant;

  constructor(private router: Router) { }

  goToTenant(): void {
    this.router.navigate(['/', this.tenant.slug]);
  }
}
