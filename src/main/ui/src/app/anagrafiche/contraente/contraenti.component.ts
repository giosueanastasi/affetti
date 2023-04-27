import { Component, EventEmitter, Input, OnDestroy, OnInit, Output, ViewChild } from '@angular/core';
import { FormGroup, FormControl, Validators } from '@angular/forms';
import { AppService } from '../../app.service';
import { takeUntil } from 'rxjs/operators';
import { Subject } from 'rxjs';
import { ContraentiModelComponent } from '../contraenti-model/contraenti-model.component';
import { Contraente1 } from 'src/app/app-state/models';


@Component({
  selector: 'app-contraenti',
  templateUrl: './contraenti.component.html',
  styleUrls: ['./contraenti.component.css']
})

export class ContraentiComponent implements OnInit, OnDestroy {


title = 'angular-nodejs-example';

contraenteForm = new FormGroup({
  nome: new FormControl('', Validators.nullValidator ),
  cognome: new FormControl('', Validators.nullValidator ),
  codice_fiscale: new FormControl('', Validators.nullValidator ),
  protocollo: new FormControl('', Validators.nullValidator ),
  via_residenza: new FormControl('', Validators.nullValidator ),
  comune_residenza: new FormControl('', Validators.nullValidator ),
  provincia_residenza: new FormControl('', Validators.nullValidator ),
  protocolloC: new FormControl('', Validators.nullValidator ),


  nomeAss: new FormControl('', Validators.nullValidator),
  cognomeAss: new FormControl('', Validators.nullValidator),

});

contraenti: any[] = [];
contraenteCount = 0;

destroy$: Subject<boolean> = new Subject<boolean>();

contraente1: Contraente1 = new Contraente1();
 errorMessage: string = "";

 @Output() save =  new EventEmitter<any>();
 constructor(private appService: AppService) { }


getAllContraenti() {
  this.appService.getContraenti().pipe(takeUntil(this.destroy$)).subscribe((contraenti: any[]) => {
  this.contraenteCount =contraenti.length;
      this.contraenti = contraenti;
  });
}

filtraContraenti() {
  let contraenteFiltrato = new Contraente1();
  contraenteFiltrato.nome = this.contraenteForm.controls['nome'].value;
  contraenteFiltrato.cognome = this.contraenteForm.controls['cognome'].value;
  contraenteFiltrato.codice_fiscale = this.contraenteForm.controls['codice_fiscale'].value;
  contraenteFiltrato.via_residenza = this.contraenteForm.controls['via_residenza'].value;
  contraenteFiltrato.comune_residenza = this.contraenteForm.controls['comune_residenza'].value;
  contraenteFiltrato.provincia_residenza = this.contraenteForm.controls['provincia_residenza'].value;
  contraenteFiltrato.nomeAss = this.contraenteForm.controls['nomeAss'].value;
  contraenteFiltrato.cognomeAss = this.contraenteForm.controls['cognomeAss'].value;
  contraenteFiltrato.protocolloC = this.contraenteForm.controls['protocolloC'].value;

  this.appService.cercaCercacontraenti(contraenteFiltrato).pipe(takeUntil(this.destroy$)).subscribe((data: any) => {
    this.contraenteCount =data.length;
    this.contraenti = data.contraenti;
    });
  
}

ngOnDestroy() {
  this.destroy$.next(true);
  this.destroy$.unsubscribe();
}


ngOnInit() {
  console.log('esegui all contraenti on init');
  this.getAllContraenti();
  }



}
