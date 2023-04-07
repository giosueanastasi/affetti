import { Component, EventEmitter, Input, OnDestroy, OnInit, Output } from '@angular/core';
import { FormGroup, FormControl, Validators } from '@angular/forms';
import { AppService } from '../../app.service';
import { takeUntil } from 'rxjs/operators';
import { Subject } from 'rxjs';


@Component({
  selector: 'app-domande',
  templateUrl: './domande.component.html',
  styleUrls: ['./domande.component.css']
})

export class DomandeComponent implements OnInit, OnDestroy {

constructor(private appService: AppService) {}

title = 'angular-nodejs-example';

domandaForm = new FormGroup({
  id: new FormControl('', Validators.nullValidator && Validators.required),
  protocollo: new FormControl('', Validators.nullValidator && Validators.required),
  data_protocollo: new FormControl('', Validators.nullValidator && Validators.required),
  stato: new FormControl('', Validators.nullValidator && Validators.required),
  fk_posto: new FormControl('', Validators.nullValidator && Validators.required),
  fk_assegnatario: new FormControl('', Validators.nullValidator && Validators.required),
  fk_contraente: new FormControl('', Validators.nullValidator && Validators.required),
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




/*
@Input() domande: any[];
*/
ngOnInit() {
  console.log('esegui all domande on init');
  this.getAllDomande();
  }

}
