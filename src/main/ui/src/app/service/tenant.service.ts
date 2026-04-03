import { Injectable } from '@angular/core';
import { HttpClient, HttpParams } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Tenant } from '../app-state/models/tenant.model';
import { CimiteroSelect } from '../app-state/models/cimitero-select.model';
import { Defunto } from '../app-state/models/defunto.model';

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

  getCimiteriByTenant(tenantId: number): Observable<CimiteroSelect[]> {
    return this.http.get<CimiteroSelect[]>(this.rootURL + '/tenant/' + tenantId + '/cimiteri');
  }

  searchDefuntiByTenant(tenantId: number, ricerca: string, cimiteroId?: number, page: number = 0, size: number = 10): Observable<Defunto[]> {
    let params = new HttpParams();
    if (cimiteroId) {
      params = params.set('cimiteroId', cimiteroId.toString());
    }
    return this.http.post<Defunto[]>(
      this.rootURL + '/tenant/' + tenantId + '/search_defunti',
      { ricerca, page, size },
      { params }
    );
  }

  getRecentDefuntiByTenant(tenantId: number, limit: number = 20): Observable<Defunto[]> {
    const params = new HttpParams().set('limit', limit.toString());
    return this.http.get<Defunto[]>(this.rootURL + '/tenant/' + tenantId + '/defunti', { params });
  }
}
