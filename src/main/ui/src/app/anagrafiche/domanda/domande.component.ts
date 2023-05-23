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
  idPosto: new FormControl('', Validators.nullValidator),
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

  editDomandaRequest(item: Domanda){

    this.selectedDomanda = Object.assign({},item);
    this.child?.showDomandaModal();
  }

  saveDomandaWatcher(domanda: Domanda){
    
    let domandaIndex = this.domande.findIndex(item => item.id === domanda.idDomanda);
    if(domandaIndex !==-1){
      this.domande[domandaIndex] = domanda;
    }else{
      this.domande.push(domanda);
    }
  }


}
