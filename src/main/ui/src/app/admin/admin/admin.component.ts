import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Tenant } from 'src/app/app-state/models/tenant.model';
import { CimiteroSelect } from 'src/app/app-state/models/cimitero-select.model';
import { TenantService } from 'src/app/service/tenant.service';
import { AuthStateService } from 'src/app/security/auth-state.service';
import { Roles } from 'src/app/app-state/enum/roles.enum';

@Component({
  selector: 'app-admin',
  templateUrl: './admin.component.html',
  styleUrls: ['./admin.component.css']
})
export class AdminComponent implements OnInit {

  // Tenant
  tenants: Tenant[] = [];
  showTenantForm: boolean = false;
  editingTenant: Tenant | null = null;
  tenantForm: FormGroup;
  savingTenant: boolean = false;
  isSuperadmin: boolean = false;
  logoFile: File | null = null;
  uploadingLogo: boolean = false;

  // Cimiteri
  selectedTenantId: number | null = null;
  cimiteri: CimiteroSelect[] = [];
  showCimiteroForm: boolean = false;
  cimiteroForm: FormGroup;
  savingCimitero: boolean = false;

  constructor(
    private tenantService: TenantService,
    private authState: AuthStateService,
    private fb: FormBuilder
  ) {
    this.tenantForm = this.fb.group({
      descrizione: ['', Validators.required],
      slug: [''],
      logoUrl: [''],
      colorePrimario: ['#667eea'],
      coloreSecondario: ['#764ba2'],
      sinossi: ['']
    });

    this.cimiteroForm = this.fb.group({
      codice: ['', Validators.required],
      nome: ['', Validators.required],
      indirizzo: [''],
      comune: this.fb.group({
        id: [null, Validators.required]
      })
    });
  }

  ngOnInit(): void {
    this.isSuperadmin = this.authState.hasRole(Roles.SUPERADMIN);
    this.loadTenants();
  }

  // ===== TENANT =====

  loadTenants(): void {
    this.tenantService.getTenants().subscribe(tenants => {
      this.tenants = tenants;
      if (this.tenants.length > 0 && !this.selectedTenantId) {
        this.selectTenant(this.tenants[0].id);
      } else if (this.selectedTenantId) {
        this.loadCimiteri();
      }
    });
  }

  openTenantForm(tenant?: Tenant): void {
    this.editingTenant = tenant || null;
    this.logoFile = null;
    if (tenant) {
      this.tenantForm.patchValue({
        descrizione: tenant.descrizione,
        slug: tenant.slug,
        logoUrl: tenant.logoUrl || '',
        colorePrimario: tenant.colorePrimario || '#667eea',
        coloreSecondario: tenant.coloreSecondario || '#764ba2',
        sinossi: tenant.sinossi || ''
      });
      this.tenantForm.get('slug')?.markAsDirty();
    } else {
      this.tenantForm.reset({ colorePrimario: '#667eea', coloreSecondario: '#764ba2' });
    }
    this.showTenantForm = true;
  }

  closeTenantForm(): void {
    this.showTenantForm = false;
    this.editingTenant = null;
    this.logoFile = null;
    this.tenantForm.reset({ colorePrimario: '#667eea', coloreSecondario: '#764ba2' });
  }

  onDescrizioneChange(): void {
    if (this.editingTenant) return;
    const desc = this.tenantForm.get('descrizione')?.value || '';
    const slugCtrl = this.tenantForm.get('slug');
    if (slugCtrl && !slugCtrl.dirty) {
      slugCtrl.setValue(this.generateSlug(desc));
    }
  }

  onLogoFileSelected(event: Event): void {
    const input = event.target as HTMLInputElement;
    if (input.files && input.files.length > 0) {
      this.logoFile = input.files[0];
    }
  }

  saveTenant(): void {
    if (this.tenantForm.invalid || this.savingTenant) return;
    this.savingTenant = true;
    const formValue = this.tenantForm.value as Tenant;

    const save$ = this.editingTenant
      ? this.tenantService.updateTenant(this.editingTenant.id, formValue)
      : this.tenantService.createTenant(formValue);

    save$.subscribe({
      next: (saved: Tenant) => {
        if (this.logoFile && saved.id) {
          this.uploadLogo(saved.id);
        } else {
          this.onTenantSaved();
        }
      },
      error: () => {
        this.savingTenant = false;
      }
    });
  }

  private uploadLogo(tenantId: number): void {
    if (!this.logoFile) { this.onTenantSaved(); return; }
    this.uploadingLogo = true;
    this.tenantService.uploadTenantLogo(tenantId, this.logoFile).subscribe({
      next: () => this.onTenantSaved(),
      error: () => this.onTenantSaved()
    });
  }

  private onTenantSaved(): void {
    this.savingTenant = false;
    this.uploadingLogo = false;
    this.closeTenantForm();
    this.loadTenants();
  }

  deleteTenant(tenant: Tenant): void {
    const msg = `ATTENZIONE: Eliminare "${tenant.descrizione}" e tutti i suoi dati (cimiteri, aree, posti, defunti, domande, contratti)?\n\nQuesta operazione NON e' reversibile.`;
    if (!confirm(msg)) return;
    this.tenantService.deleteTenant(tenant.id).subscribe({
      next: () => {
        if (this.selectedTenantId === tenant.id) {
          this.selectedTenantId = null;
          this.cimiteri = [];
        }
        this.loadTenants();
      }
    });
  }

  // ===== CIMITERI =====

  selectTenant(tenantId: number): void {
    this.selectedTenantId = tenantId;
    this.loadCimiteri();
  }

  get selectedTenant(): Tenant | undefined {
    return this.tenants.find(t => t.id === this.selectedTenantId);
  }

  loadCimiteri(): void {
    if (!this.selectedTenantId) return;
    this.tenantService.getCimiteriByTenant(this.selectedTenantId).subscribe(cimiteri => {
      this.cimiteri = cimiteri;
    });
  }

  toggleCimiteroForm(): void {
    this.showCimiteroForm = !this.showCimiteroForm;
    if (!this.showCimiteroForm) {
      this.cimiteroForm.reset();
    }
  }

  saveCimitero(): void {
    if (this.cimiteroForm.invalid || this.savingCimitero || !this.selectedTenantId) return;
    this.savingCimitero = true;

    const formValue = this.cimiteroForm.value;
    const payload = {
      codice: formValue.codice,
      nome: formValue.nome,
      indirizzo: formValue.indirizzo || null,
      comune: { id: formValue.comune.id },
      tenant: { id: this.selectedTenantId }
    };

    this.tenantService.saveCimitero(payload).subscribe({
      next: () => {
        this.savingCimitero = false;
        this.showCimiteroForm = false;
        this.cimiteroForm.reset();
        this.loadCimiteri();
      },
      error: () => {
        this.savingCimitero = false;
      }
    });
  }

  deleteCimitero(id: number, nome: string): void {
    if (!confirm(`Eliminare il cimitero "${nome}"? L\'operazione non e\' reversibile.`)) return;
    this.tenantService.deleteCimitero(id).subscribe({
      next: () => this.loadCimiteri()
    });
  }

  // ===== UTILS =====

  private generateSlug(input: string): string {
    return input.toLowerCase()
      .replace(/[àáâãäå]/g, 'a')
      .replace(/[èéêë]/g, 'e')
      .replace(/[ìíîï]/g, 'i')
      .replace(/[òóôõö]/g, 'o')
      .replace(/[ùúûü]/g, 'u')
      .replace(/[^a-z0-9\s-]/g, '')
      .trim()
      .replace(/\s+/g, '-');
  }
}
