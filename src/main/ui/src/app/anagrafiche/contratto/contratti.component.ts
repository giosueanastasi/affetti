import { Component, EventEmitter, Input, OnDestroy, OnInit, Output } from '@angular/core';
import { FormGroup, FormControl, Validators } from '@angular/forms';
import { AppService } from '../../app.service';
import { takeUntil } from 'rxjs/operators';
import { Subject } from 'rxjs';



@Component({
  selector: 'app-contratti',
  templateUrl: './contratti.component.html',
  styleUrls: ['./contratti.component.css']
})
export class ContrattiComponent implements OnInit, OnDestroy {

  constructor(private appService: AppService) {}

  title = 'angular-nodejs-example';

  contrattoForm = new FormGroup({
    nome: new FormControl('', Validators.nullValidator),
    cognome: new FormControl('', Validators.nullValidator),
    tipologia: new FormControl('', Validators.nullValidator),
    codice_fiscale: new FormControl('', Validators.nullValidator),
    numero_protocollo: new FormControl('', Validators.nullValidator),
    data_protocollo_iniziale: new FormControl('', Validators.nullValidator),
    data_protocollo_finale: new FormControl('', Validators.nullValidator),
    stato: new FormControl('', Validators.nullValidator)
  });

  contratti: any[] = [];
  contrattoCount = 0;

  destroy$: Subject<boolean> = new Subject<boolean>();

  onSubmit() {
    this.appService.addContratto(this.contrattoForm.value, this.contrattoCount + 1).pipe(takeUntil(this.destroy$)).subscribe(data => {
      console.log('message::::', data);
      this.contrattoCount = this.contrattoCount + 1;
      console.log(this.contrattoCount);
      this.contrattoForm.reset();
      this.getAllContratti();
    });
  }

  getAllContratti() {
    this.appService.getContratti().pipe(takeUntil(this.destroy$)).subscribe((contratti: any[]) => {
		this.contrattoCount = contratti.length;
        this.contratti = contratti;
    });
  }

  ngOnDestroy() {
    this.destroy$.next(true);
    this.destroy$.unsubscribe();
  }

  cercaContratti(cercaContrattiForm: FormGroup) {
    this.appService.cercaContrattiService(cercaContrattiForm.value).pipe(takeUntil(this.destroy$)).subscribe((data: any) => {
      this.contratti = data.contratti;
    }); 
}
  
/*
  @Input() contratti: any[];
*/
  ngOnInit() {
    console.log('esegui all contratto on init');
    this.getAllContratti();
    }

}
