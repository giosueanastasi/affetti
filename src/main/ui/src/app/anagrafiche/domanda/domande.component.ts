import { Component, EventEmitter, Input, OnDestroy, OnInit, Output, ViewChild } from '@angular/core';
import { FormGroup, FormControl, Validators } from '@angular/forms';
import { AppService } from '../../app.service';
import { takeUntil } from 'rxjs/operators';
import { Subject } from 'rxjs';
import { ContraentiModelComponent } from '../contraenti-model/contraenti-model.component';
import { Domanda } from 'src/app/app-state/models';
import { DomandaModelComponent } from '../domanda-model/domanda-model.component';


@Component({
  selector: 'app-domande',
  templateUrl: './domande.component.html',
  styleUrls: ['./domande.component.css']
})

export class DomandeComponent implements OnInit, OnDestroy {

constructor(private appService: AppService) {}

@ViewChild(DomandaModelComponent) child: DomandaModelComponent | undefined;

selectedDomanda: Domanda = new Domanda();

title = 'angular-nodejs-example';

domandaForm = new FormGroup({
  nome: new FormControl('', Validators.nullValidator),
  cognome: new FormControl('', Validators.nullValidator),
  tipologia: new FormControl('', Validators.nullValidator),
  codice_fiscale: new FormControl('', Validators.nullValidator),
  numero_protocollo: new FormControl('', Validators.nullValidator),
  data_protocollo_iniziale: new FormControl('', Validators.nullValidator),
  data_protocollo_finale: new FormControl('', Validators.nullValidator),
  stato: new FormControl('', Validators.nullValidator)
});

domande: any[] = [];
domandaCount = 0;

destroy$: Subject<boolean> = new Subject<boolean>();


getAllDomande() {
  this.appService.getDomande().pipe(takeUntil(this.destroy$)).subscribe((domande: any[]) => {
  this.domandaCount =domande.length;
      this.domande = domande;
  });
}

ngOnDestroy() {
  this.destroy$.next(true);
  this.destroy$.unsubscribe();
}

svuotaDomanda(svuotadomandeForm: FormGroup) {
  console.log(svuotadomandeForm);
}

cercaDomande(cercaDomandaForm: FormGroup) {
    this.appService.cercaDomandeService(cercaDomandaForm.value).pipe(takeUntil(this.destroy$)).subscribe((data: any) => {
      this.domande = data.domande;
    }); 
}



ngOnInit() {
  console.log('esegui all domande on init');
 // this.getAllDomande();
  }

  editDomandaRequest(item: any){
    let domanda = new Domanda;
 
    domanda.data_protocollo = item.dataProtocollo;
    domanda.protocollo = item.numeroProtocolloDomanda;
    domanda.tipologia = item.tipologia;
    domanda.stato = item.stato;
    domanda.nome = item.nomeContraente;
    domanda.cognome= item.cognomeContraente;
    domanda.comune_nascita = item.comuneDiNascita;
    domanda.provincia_nascita = item.provinciaDiNascita;
    domanda.stato_nascita = item.statoDiNascita;
    domanda.comune_residenza = item.comuneDiResidenza;
    domanda.provincia_residenza = item.provinciaDiResidenza;
    domanda.via_residenza = item.viaDiResidenza;
    domanda.civico_residenza = item.civicoDiResidenza;
    domanda.cap_residenza = item.capDiResidenza;
    domanda.codice_fiscale =item.codiceFiscale;
    domanda.telefono = item.telefono;
    domanda.email = item.email;
    domanda.note = item.note;
    domanda.loculo = item.loculo;
    domanda.fornice = item.fornice;
    domanda.nomeAss = item.nomeAss;
    domanda.cognomeAss = item.cognomeAss;
    domanda.comune_decesso = item.comuneDecesso;
    domanda.data_decesso = item.dataDecesso;

    this.selectedDomanda = Object.assign({},domanda);
    this.child?.showDomandaModal();
  }

  saveDomandaWatcher(domanda: Domanda){
    
    let domandaIndex = this.domande.findIndex(item => item.id === domanda.id);
    if(domandaIndex !==-1){
      this.domande[domandaIndex] = domanda;
    }else{
      this.domande.push(domanda);
    }
  }


}
