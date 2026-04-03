import { Injectable } from '@angular/core';
import { HttpClient, HttpParams } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Tenant } from '../app-state/models/tenant.model';
import { Cimitero } from '../app-state/models/cimitero.model';
import { CimiteroSelect } from '../app-state/models/cimitero-select.model';
import { Defunto } from '../app-state/models/defunto.model';
import { DefuntoCard } from '../app-state/models/defunto-card.model';
import { TenantStatistiche } from '../app-state/models/tenant-statistiche.model';

@Injectable({
  providedIn: 'root'
})
export class TenantService {

  private rootURL = '/api';

  constructor(private http: HttpClient) { }

  getTenants(): Observable<Tenant[]> {
    return this.http.get<Tenant[]>(this.rootURL + '/tenants');
  }

  getTenant(id: number): Observable<Tenant> {
    return this.http.get<Tenant>(this.rootURL + '/tenant/' + id);
  }

  getTenantBySlug(slug: string): Observable<Tenant> {
    return this.http.get<Tenant>(this.rootURL + '/tenant/slug/' + slug);
  }

  getCimiteriByTenant(tenantId: number): Observable<CimiteroSelect[]> {
    return this.http.get<CimiteroSelect[]>(this.rootURL + '/tenant/' + tenantId + '/cimiteri');
  }

  searchDefuntiByTenant(tenantId: number, ricerca: string, cimiteroIds?: number[], page: number = 0, size: number = 10): Observable<DefuntoCard[]> {
    let params = new HttpParams();
    if (cimiteroIds && cimiteroIds.length > 0) {
      cimiteroIds.forEach(id => {
        params = params.append('cimiteroIds', id.toString());
      });
    }
    return this.http.post<DefuntoCard[]>(
      this.rootURL + '/tenant/' + tenantId + '/search_defunti',
      { ricerca, page, size },
      { params }
    );
  }

  createTenant(tenant: Tenant): Observable<Tenant> {
    return this.http.post<Tenant>(this.rootURL + '/tenant', tenant);
  }

  updateTenant(id: number, tenant: Tenant): Observable<Tenant> {
    return this.http.put<Tenant>(this.rootURL + '/tenant/' + id, tenant);
  }

  deleteTenant(id: number): Observable<any> {
    return this.http.delete<any>(this.rootURL + '/tenant/' + id);
  }

  uploadTenantLogo(tenantId: number, file: File): Observable<{ logoUrl: string }> {
    const formData = new FormData();
    formData.append('file', file);
    return this.http.post<{ logoUrl: string }>(this.rootURL + '/tenant/' + tenantId + '/logo', formData);
  }

  saveCimitero(cimitero: any): Observable<any> {
    return this.http.post<any>(this.rootURL + '/cimitero', cimitero);
  }

  getCimitero(id: number): Observable<any> {
    return this.http.get<any>(this.rootURL + '/cimitero/' + id);
  }

  deleteCimitero(id: number): Observable<any> {
    return this.http.delete<any>(this.rootURL + '/cimitero/' + id);
  }

  getStatistiche(tenantId: number, cimiteroIds: number[]): Observable<TenantStatistiche> {
    let params = new HttpParams();
    if (cimiteroIds && cimiteroIds.length > 0) {
      cimiteroIds.forEach(id => {
        params = params.append('cimiteroIds', id.toString());
      });
    }
    return this.http.get<TenantStatistiche>(this.rootURL + '/tenant/' + tenantId + '/statistiche', { params });
  }

  getRecentDefuntiByTenant(tenantId: number, cimiteroIds?: number[], limit: number = 20): Observable<DefuntoCard[]> {
    let params = new HttpParams().set('limit', limit.toString());
    if (cimiteroIds && cimiteroIds.length > 0) {
      cimiteroIds.forEach(id => {
        params = params.append('cimiteroIds', id.toString());
      });
    }
    return this.http.get<DefuntoCard[]>(this.rootURL + '/tenant/' + tenantId + '/defunti', { params });
  }
}
