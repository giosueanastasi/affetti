import { Component, OnInit, ViewChild } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Tenant } from 'src/app/app-state/models/tenant.model';
import { CimiteroSelect } from 'src/app/app-state/models/cimitero-select.model';
import { DefuntoCard } from 'src/app/app-state/models/defunto-card.model';
import { Domanda, Contratto, Posto, Assegnatario, Contraente } from 'src/app/app-state/models';
import { TenantService } from 'src/app/service/tenant.service';
import { AppService } from 'src/app/app.service';
import { DomandaModelComponent } from '../domanda-model/domanda-model.component';
import { ContrattoModelComponent } from '../contratto-model/contratto-model.component';

@Component({
  selector: 'app-tenant-dashboard',
  templateUrl: './tenant-dashboard.component.html',
  styleUrls: ['./tenant-dashboard.component.css']
})
export class TenantDashboardComponent implements OnInit {

  @ViewChild(DomandaModelComponent) domandaModal: DomandaModelComponent | undefined;
  @ViewChild(ContrattoModelComponent) contrattoModal: ContrattoModelComponent | undefined;

  tenant: Tenant | null = null;
  cimiteri: CimiteroSelect[] = [];
  activeCimiteroIds: Set<number> = new Set();
  recentDefunti: DefuntoCard[] = [];
  loading: boolean = true;
  sortBy: string = 'decesso_desc';

  selectedDomanda: Domanda = new Domanda();
  selectedContratto: Contratto = new Contratto();

  constructor(
    private route: ActivatedRoute,
    private tenantService: TenantService,
    private appService: AppService
  ) { }

  get activeCimiteroIdsArray(): number[] {
    return Array.from(this.activeCimiteroIds);
  }

  get sortedDefunti(): DefuntoCard[] {
    return [...this.recentDefunti].sort((a, b) => {
      switch (this.sortBy) {
        case 'cognome_asc': return a.cognome.localeCompare(b.cognome);
        case 'cognome_desc': return b.cognome.localeCompare(a.cognome);
        case 'decesso_asc': return new Date(a.data_decesso).getTime() - new Date(b.data_decesso).getTime();
        case 'decesso_desc': return new Date(b.data_decesso).getTime() - new Date(a.data_decesso).getTime();
        default: return 0;
      }
    });
  }

  ngOnInit(): void {
    const slug = this.route.snapshot.paramMap.get('slug');
    this.tenantService.getTenantBySlug(slug!).subscribe(tenant => {
      this.tenant = tenant;
      this.loading = false;
      this.tenantService.getCimiteriByTenant(tenant.id).subscribe(cimiteri => {
        this.cimiteri = cimiteri;
        cimiteri.forEach(c => this.activeCimiteroIds.add(c.id));
        this.loadRecentDefunti();
      });
    });
  }

  isCimiteroActive(cimiteroId: number): boolean {
    return this.activeCimiteroIds.has(cimiteroId);
  }

  toggleCimitero(cimiteroId: number): void {
    if (this.activeCimiteroIds.has(cimiteroId)) {
      if (this.activeCimiteroIds.size > 1) {
        this.activeCimiteroIds.delete(cimiteroId);
      }
    } else {
      this.activeCimiteroIds.add(cimiteroId);
    }
    this.activeCimiteroIds = new Set(this.activeCimiteroIds);
    this.loadRecentDefunti();
  }

  onOpenDomanda(domandaId: number): void {
    this.appService.getDomandaById(domandaId).subscribe((item: any) => {
      this.selectedDomanda = this.buildDomandaFromResponse(item);
      this.domandaModal?.showDomandaModal();
    });
  }

  onOpenContratto(domandaId: number): void {
    this.appService.getDomandaById(domandaId).subscribe((item: any) => {
      this.selectedContratto = this.buildContrattoFromResponse(item);
      this.contrattoModal?.showContrattoModal();
    });
  }

  private buildDomandaFromResponse(item: any): Domanda {
    const domanda = new Domanda();
    const posto = new Posto();
    posto.id = item.fk_posto;
    posto.fornice = item.fornice;
    posto.loculo = item.loculo;
    const assegnatario = new Assegnatario();
    assegnatario.id = item.fk_assegnatario;
    assegnatario.nome = item.nomeAss;
    assegnatario.cognome = item.cognomeAss;
    const contraente = new Contraente();
    contraente.id = item.fk_contraente;
    domanda.contraente = contraente;
    domanda.id = item.id;
    domanda.posto = posto;
    domanda.assegnatario = assegnatario;
    domanda.data_protocollo = item.dataProtocollo;
    domanda.protocollo = item.numeroProtocolloDomanda;
    domanda.tipologia = item.tipologia;
    domanda.stato = item.stato;
    domanda.nome = item.nomeContraente;
    domanda.cognome = item.cognomeContraente;
    domanda.comune_nascita = item.comuneDiNascita;
    domanda.provincia_nascita = item.provinciaDiNascita;
    domanda.stato_nascita = item.statoDiNascita;
    domanda.comune_residenza = item.comuneDiResidenza;
    domanda.provincia_residenza = item.provinciaDiResidenza;
    domanda.via_residenza = item.viaDiResidenza;
    domanda.civico_residenza = item.civicoDiResidenza;
    domanda.cap_residenza = item.capDiResidenza;
    domanda.codice_fiscale = item.codiceFiscale;
    domanda.telefono = item.telefono;
    domanda.email = item.email;
    domanda.note = item.note;
    domanda.loculo = item.loculo;
    domanda.fornice = item.fornice;
    domanda.nomeAss = item.nomeAss;
    domanda.cognomeAss = item.cognomeAss;
    domanda.comune_decesso = item.comuneDecesso;
    domanda.data_decesso = item.dataDecesso;
    return domanda;
  }

  private buildContrattoFromResponse(item: any): Contratto {
    const contratto = new Contratto();
    if (!item.contratto) return contratto;
    contratto.id = item.contratto.id;
    contratto.idContratto = item.contratto.id;
    contratto.numeroProtocolloContratto = item.contratto.protocollo;
    contratto.dataProtocolloContratto = item.contratto.data_inizio;
    contratto.stato = item.contratto.stato;
    contratto.dataScadenzaContratto = item.contratto.data_scadenza;
    contratto.nomeC = item.nomeContraente;
    contratto.cognomeC = item.cognomeContraente;
    contratto.data_nascita = item.dataNascita;
    contratto.provincia_nascita = item.provinciaDiNascita;
    contratto.stato_nascita = item.statoDiNascita;
    contratto.comune_nascita = item.comuneDiNascita;
    contratto.comune_residenza = item.comuneDiResidenza;
    contratto.provincia_residenza = item.provinciaDiResidenza;
    contratto.via_residenza = item.viaDiResidenza;
    contratto.cap_residenza = item.capDiResidenza;
    contratto.codice_fiscale = item.codiceFiscale;
    contratto.email = item.email;
    contratto.telefono = item.telefono;
    contratto.note = item.note;
    contratto.loculo = item.loculo;
    contratto.fornice = item.fornice;
    contratto.nomeA = item.nomeAss;
    contratto.cognomeA = item.cognomeAss;
    contratto.comune_decesso = item.comuneDecesso;
    contratto.data_decesso = item.dataDecesso;
    contratto.protocolloDomanda = item.numeroProtocolloDomanda;
    contratto.dataProtocolloDomanda = item.dataProtocollo;
    return contratto;
  }

  private loadRecentDefunti(): void {
    if (!this.tenant) return;
    this.tenantService.getRecentDefuntiByTenant(
      this.tenant.id, this.activeCimiteroIdsArray, 20
    ).subscribe((defunti: DefuntoCard[]) => {
      this.recentDefunti = defunti;
    });
  }
}
