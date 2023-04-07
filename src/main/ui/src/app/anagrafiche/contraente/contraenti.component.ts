import { Component, EventEmitter, Input, OnDestroy, OnInit, Output } from '@angular/core';
import { FormGroup, FormControl, Validators } from '@angular/forms';
import { AppService } from '../../app.service';
import { takeUntil } from 'rxjs/operators';
import { Subject } from 'rxjs';


@Component({
  selector: 'app-contraenti',
  templateUrl: './contraenti.component.html',
  styleUrls: ['./contraenti.component.css']
})

export class ContraentiComponent implements OnInit, OnDestroy {

constructor(private appService: AppService) {}

title = 'angular-nodejs-example';

contraenteForm = new FormGroup({
  id: new FormControl('', Validators.nullValidator && Validators.required),
  nome: new FormControl('', Validators.nullValidator && Validators.required),
  cognome: new FormControl('', Validators.nullValidator && Validators.required),
  comune_nascita: new FormControl('', Validators.nullValidator && Validators.required),
  provincia_nascita: new FormControl('', Validators.nullValidator && Validators.required),
  stato_nascita: new FormControl('', Validators.nullValidator && Validators.required),
  data_nascita: new FormControl('', Validators.nullValidator && Validators.required),
  comune_residenza: new FormControl('', Validators.nullValidator && Validators.required),
  provincia_residenza: new FormControl('', Validators.nullValidator && Validators.required),
  via_residenza: new FormControl('', Validators.nullValidator && Validators.required),
  civico_residenza: new FormControl('', Validators.nullValidator && Validators.required),
  cap_residenza: new FormControl('', Validators.nullValidator && Validators.required),
  telefono: new FormControl('', Validators.nullValidator && Validators.required),
  codice_fiscale: new FormControl('', Validators.nullValidator && Validators.required),
  email: new FormControl('', Validators.nullValidator ),
  note: new FormControl('', Validators.nullValidator ),
  fk_user_modifier: new FormControl('', Validators.nullValidator ),
  data_insert: new FormControl('', Validators.nullValidator ),
  data_update: new FormControl('', Validators.nullValidator ),

});

contraenti: any[] = [];
contraenteCount = 0;

destroy$: Subject<boolean> = new Subject<boolean>();

onSubmit() {
  this.appService.addContraente(this.contraenteForm.value, this.contraenteCount + 1).pipe(takeUntil(this.destroy$)).subscribe(data => {
    console.log('message::::', data);
    this.contraenteCount = this.contraenteCount + 1;
    console.log(this.contraenteCount);
    this.contraenteForm.reset();
    this.getAllContraenti();
  });
}

getAllContraenti() {
  this.appService.getContraenti().pipe(takeUntil(this.destroy$)).subscribe((contraenti: any[]) => {
  this.contraenteCount =contraenti.length;
      this.contraenti = contraenti;
  });
}

ngOnDestroy() {
  this.destroy$.next(true);
  this.destroy$.unsubscribe();
}




/*
@Input() contraenti: any[];
*/
ngOnInit() {
  console.log('esegui all contraenti on init');
  this.getAllContraenti();
  }

}
