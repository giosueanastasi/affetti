import { Component, Input, OnInit, OnDestroy } from '@angular/core';
import { FormControl } from '@angular/forms';
import { Router } from '@angular/router';
import { Subject, debounceTime, switchMap, takeUntil } from 'rxjs';
import { Defunto } from 'src/app/app-state/models/defunto.model';
import { AppService } from 'src/app/app.service';
import { TenantService } from 'src/app/service/tenant.service';

@Component({
  selector: 'app-cerca-defunti-widget',
  templateUrl: './cerca-defunti-widget.component.html',
  styleUrls: ['./cerca-defunti-widget.component.css']
})
export class CercaDefuntiWidgetComponent implements OnInit, OnDestroy {

  @Input() tenantId: number | null = null;
  @Input() cimiteroId: number | null = null;
  @Input() showBanner: boolean = true;
  @Input() bannerTitle: string = 'Ricerca Defunti';
  @Input() bannerSubtitle: string = 'Cerca tra i registri del cimitero';

  destroy$: Subject<boolean> = new Subject<boolean>();
  ricerca = new FormControl('');
  defunti: Defunto[] = [];
  ricercaAvviata: boolean = false;
  currentPage: number = 0;
  pageSize: number = 10;
  fetchSize: number = 11;
  hasMore: boolean = true;

  constructor(
    private appService: AppService,
    private tenantService: TenantService,
    private router: Router
  ) { }

  ngOnInit(): void {
    this.ricerca.valueChanges.pipe(
      takeUntil(this.destroy$),
      debounceTime(500),
      switchMap(ricerca => {
        if (ricerca.trim()) {
          this.ricercaAvviata = true;
          this.currentPage = 0;
          this.defunti = [];
          return this.doSearch(ricerca.trim(), this.currentPage);
        } else {
          this.ricercaAvviata = false;
          this.defunti = [];
          this.hasMore = true;
          return [];
        }
      }),
    ).subscribe((data: Defunto[]) => {
      this.hasMore = data.length > this.pageSize;
      this.defunti = data.slice(0, this.pageSize);
    });
  }

  ngOnDestroy(): void {
    this.destroy$.next(true);
    this.destroy$.unsubscribe();
  }

  private doSearch(ricerca: string, page: number) {
    if (this.tenantId) {
      return this.tenantService.searchDefuntiByTenant(
        this.tenantId, ricerca, this.cimiteroId, page, this.fetchSize
      );
    } else {
      return this.appService.cercaDefunti(ricerca, page, this.fetchSize);
    }
  }

  caricaAltri(): void {
    this.currentPage++;
    const ricercaCorrente = this.ricerca.value?.trim();
    if (ricercaCorrente) {
      this.doSearch(ricercaCorrente, this.currentPage)
        .subscribe((data: Defunto[]) => {
          this.hasMore = data.length > this.pageSize;
          const nuoviDefunti = data.slice(0, this.pageSize);
          this.defunti = [...this.defunti, ...nuoviDefunti];
        });
    }
  }

  dettaglio(defuntoId: number): void {
    this.router.navigate(['/defunti', defuntoId]);
  }
}
