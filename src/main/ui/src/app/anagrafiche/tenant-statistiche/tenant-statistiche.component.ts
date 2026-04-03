import { Component, Input, OnChanges, SimpleChanges } from '@angular/core';
import { TenantStatistiche, ChartData, ChartGroupData } from 'src/app/app-state/models/tenant-statistiche.model';
import { TenantService } from 'src/app/service/tenant.service';

@Component({
  selector: 'app-tenant-statistiche',
  templateUrl: './tenant-statistiche.component.html',
  styleUrls: ['./tenant-statistiche.component.css']
})
export class TenantStatisticheComponent implements OnChanges {

  @Input() tenantId: number;
  @Input() cimiteroIds: number[] = [];
  @Input() primaryColor: string = '#667eea';
  @Input() secondaryColor: string = '#764ba2';

  stats: TenantStatistiche | null = null;
  loading: boolean = false;
  isOpen: boolean = false;
  needsReload: boolean = true;

  activeSlide: number = 0;
  slideLabels: string[] = ['KPI', 'Distribuzione', 'Trend', 'Confronto'];

  // Dropdown selections
  distribuzioneSel: string = 'postiPerStato';
  trendSel: string = 'decessiPerMese';
  confrontoSel: string = 'capienzaVsOccupazione';

  colorScheme: any;

  constructor(private tenantService: TenantService) {}

  ngOnChanges(changes: SimpleChanges): void {
    this.buildColorScheme();
    if (changes['cimiteroIds'] && !changes['cimiteroIds'].firstChange) {
      if (this.isOpen) {
        this.loadStats();
      } else {
        this.needsReload = true;
      }
    }
  }

  onAccordionToggle(isOpen: boolean): void {
    this.isOpen = isOpen;
    if (isOpen && this.needsReload) {
      this.loadStats();
    }
  }

  loadStats(): void {
    if (!this.tenantId || this.loading) return;
    this.loading = true;
    this.tenantService.getStatistiche(this.tenantId, this.cimiteroIds).subscribe({
      next: (data) => {
        this.stats = data;
        this.loading = false;
        this.needsReload = false;
      },
      error: () => {
        this.loading = false;
      }
    });
  }

  // Getters for current chart data based on dropdown selection
  get distribuzioneData(): ChartData[] {
    if (!this.stats) return [];
    switch (this.distribuzioneSel) {
      case 'postiPerStato': return this.stats.postiPerStato || [];
      case 'postiPerTipoSepoltura': return this.stats.postiPerTipoSepoltura || [];
      case 'domandePerStato': return this.stats.domandePerStato || [];
      case 'sepolturePerTipoOperazione': return this.stats.sepolturePerTipoOperazione || [];
      default: return [];
    }
  }

  get trendData(): ChartGroupData[] {
    if (!this.stats) return [];
    switch (this.trendSel) {
      case 'decessiPerMese': return this.stats.decessiPerMese || [];
      case 'domandePerMese': return this.stats.domandePerMese || [];
      case 'contrattiPerMese': return this.stats.contrattiPerMese || [];
      default: return [];
    }
  }

  get confrontoData(): ChartGroupData[] {
    if (!this.stats) return [];
    switch (this.confrontoSel) {
      case 'capienzaVsOccupazione': return this.stats.capienzaVsOccupazione || [];
      case 'postiPerStatoPerCimitero': return this.stats.postiPerStatoPerCimitero || [];
      default: return [];
    }
  }

  private buildColorScheme(): void {
    const p = this.primaryColor || '#667eea';
    const s = this.secondaryColor || '#764ba2';
    this.colorScheme = {
      domain: [p, s, this.lighten(p, 30), this.lighten(s, 30), this.lighten(p, 60), '#adb5bd']
    };
  }

  private lighten(hex: string, percent: number): string {
    const num = parseInt(hex.replace('#', ''), 16);
    const r = Math.min(255, (num >> 16) + Math.round(255 * percent / 100));
    const g = Math.min(255, ((num >> 8) & 0x00FF) + Math.round(255 * percent / 100));
    const b = Math.min(255, (num & 0x0000FF) + Math.round(255 * percent / 100));
    return '#' + (0x1000000 + r * 0x10000 + g * 0x100 + b).toString(16).slice(1);
  }
}
