import { Component, EventEmitter, Input, OnInit, Output } from '@angular/core';
import { FormGroup, FormControl, Validators } from '@angular/forms';
import { AppService } from '../../app.service';
import { takeUntil } from 'rxjs/operators';
import { Subject } from 'rxjs';
import {  Posto1 } from 'src/app/app-state/models';


@Component({
  selector: 'app-posti',
  templateUrl: './posti.component.html',
  styleUrls: ['./posti.component.css']
})

export class PostiComponent implements OnInit {

  
title = 'angular-nodejs-example';

postoForm = new FormGroup({
  loculo: new FormControl('', Validators.nullValidator),
  fornice: new FormControl('', Validators.nullValidator),
  nome: new FormControl('', Validators.nullValidator),
  cognome: new FormControl('', Validators.nullValidator),
  stato: new FormControl('', Validators.nullValidator),
  data_inizio: new FormControl('', Validators.nullValidator),
  data_scadenza: new FormControl('', Validators.nullValidator),

  nomeAss: new FormControl('', Validators.nullValidator),
  cognomeAss: new FormControl('', Validators.nullValidator),

});

posti: any[] = [];
postoCount = 0;

destroy$: Subject<boolean> = new Subject<boolean>();

posto1: Posto1 = new Posto1();
 errorMessage: string = "";

 @Output() save =  new EventEmitter<any>();
 constructor(private appService: AppService) { }

 filtraPosti() {
  let postoFiltrato = new Posto1();
  postoFiltrato.loculo = this.postoForm.controls['loculo'].value;
  postoFiltrato.fornice = this.postoForm.controls['fornice'].value;
  postoFiltrato.stato = this.postoForm.controls['stato'].value;
  postoFiltrato.data_inizio = this.postoForm.controls['data_inizio'].value;
  postoFiltrato.data_scadenza = this.postoForm.controls['data_scadenza'].value;
  postoFiltrato.nome = this.postoForm.controls['nomeAss'].value;
  postoFiltrato.cognome = this.postoForm.controls['cognomeAss'].value;


   this.appService.cercaPosti(postoFiltrato).pipe(takeUntil(this.destroy$)).subscribe((data: any) => {
     this.postoCount =data.length;
     this.posti = data.posti;
     });
 }


getAllPosti() {
  this.appService.getPosti().pipe(takeUntil(this.destroy$)).subscribe((posti: any[]) => {
  this.postoCount =posti.length;
      this.posti = posti;
  });
}


ngOnInit() {
  console.log('esegui all posto1 on init');
  this.getAllPosti();
  }

}
