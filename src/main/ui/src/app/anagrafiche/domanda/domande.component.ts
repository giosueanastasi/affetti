import { Component, EventEmitter, Input, OnDestroy, OnInit, Output, ViewChild } from '@angular/core';
import { FormGroup, FormControl, Validators } from '@angular/forms';
import { AppService } from '../../app.service';
import { takeUntil } from 'rxjs/operators';
import { Subject } from 'rxjs';
import { ContraentiModelComponent } from '../contraenti-model/contraenti-model.component';
import { Domanda } from 'src/app/app-state/models';


@Component({
  selector: 'app-domande',
  templateUrl: './domande.component.html',
  styleUrls: ['./domande.component.css']
})

export class DomandeComponent implements OnInit, OnDestroy {

constructor(private appService: AppService) {}

title = 'angular-nodejs-example';

domandaForm = new FormGroup({
  nome: new FormControl('', Validators.nullValidator),
  cognome: new FormControl('', Validators.nullValidator),
  codice_fiscale: new FormControl('', Validators.nullValidator),
  email: new FormControl('', Validators.nullValidator),
  data_protocollo_iniziale: new FormControl('', Validators.nullValidator),
  data_protocollo_finale: new FormControl('', Validators.nullValidator),
  numero_protocollo: new FormControl('', Validators.nullValidator),
  
  fk_user_modifier: new FormControl('', Validators.nullValidator),
  data_insert: new FormControl('', Validators.nullValidator),
  data_update: new FormControl('', Validators.nullValidator)
});

domande: any[] = [];
domandaCount = 0;

destroy$: Subject<boolean> = new Subject<boolean>();

onSubmit() {
  this.appService.addDomanda(this.domandaForm.value, this.domandaCount + 1).pipe(takeUntil(this.destroy$)).subscribe(data => {
    console.log('message::::', data);
    this.domandaCount = this.domandaCount + 1;
    console.log(this.domandaCount);
    this.domandaForm.reset();
    this.getAllDomande();
  });
}

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
    this.appService.cercaDomandeService(cercaDomandaForm.value).pipe(takeUntil(this.destroy$)).subscribe((data: any[]) => {
      this.domande = data;
    }); 
}


/*
@Input() domande: any[];
*/
ngOnInit() {
  console.log('esegui all domande on init');
  this.getAllDomande();
  }



}
