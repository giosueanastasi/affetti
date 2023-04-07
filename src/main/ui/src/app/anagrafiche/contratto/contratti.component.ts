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
    protocollo: new FormControl('', Validators.nullValidator ),
    data_inizio: new FormControl('', Validators.nullValidator && Validators.required),
    data_scadenza: new FormControl('', Validators.nullValidator),
    stato: new FormControl('', Validators.nullValidator && Validators.required),
    fk_domanda_loculo: new FormControl('', Validators.nullValidator && Validators.required),
    fk_domanda_disposizione: new FormControl('', Validators.nullValidator ),
    fk_user_modifier: new FormControl('', Validators.nullValidator ),
    data_update: new FormControl('', Validators.nullValidator ),
    data_insert: new FormControl('', Validators.nullValidator)
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
  
/*
  @Input() contratti: any[];
*/
  ngOnInit() {
    console.log('esegui all contratto on init');
    this.getAllContratti();
    }

}
