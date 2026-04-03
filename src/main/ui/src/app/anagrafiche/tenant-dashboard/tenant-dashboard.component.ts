import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Tenant } from 'src/app/app-state/models/tenant.model';
import { CimiteroSelect } from 'src/app/app-state/models/cimitero-select.model';
import { Defunto } from 'src/app/app-state/models/defunto.model';
import { TenantService } from 'src/app/service/tenant.service';

@Component({
  selector: 'app-tenant-dashboard',
  templateUrl: './tenant-dashboard.component.html',
  styleUrls: ['./tenant-dashboard.component.css']
})
export class TenantDashboardComponent implements OnInit {

  tenant: Tenant | null = null;
  cimiteri: CimiteroSelect[] = [];
  recentDefunti: Defunto[] = [];
  selectedCimiteroId: number | null = null;
  loading: boolean = true;

  constructor(
    private route: ActivatedRoute,
    private tenantService: TenantService
  ) { }

  ngOnInit(): void {
    const tenantId = Number(this.route.snapshot.paramMap.get('id'));
    this.tenantService.getTenant(tenantId).subscribe(tenant => {
      this.tenant = tenant;
      this.loading = false;
    });
    this.tenantService.getCimiteriByTenant(tenantId).subscribe(cimiteri => {
      this.cimiteri = cimiteri;
    });
    this.tenantService.getRecentDefuntiByTenant(tenantId, 20).subscribe(defunti => {
      this.recentDefunti = defunti;
    });
  }

  onCimiteroChange(): void {
    // The widget will re-use the cimiteroId via input binding;
    // recent defunti are always tenant-wide
  }
}
