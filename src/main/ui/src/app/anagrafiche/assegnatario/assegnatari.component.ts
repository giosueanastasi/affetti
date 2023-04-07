import { Component, EventEmitter, Input, OnDestroy, OnInit, Output } from '@angular/core';
import { FormGroup, FormControl, Validators } from '@angular/forms';
import { AppService } from '../../app.service';
import { takeUntil } from 'rxjs/operators';
import { Subject } from 'rxjs';


@Component({
  selector: 'app-assegnatari',
  templateUrl: './assegnatari.component.html',
  styleUrls: ['./assegnatari.component.css']
})

export class AssegnatariComponent implements OnInit, OnDestroy {

constructor(private appService: AppService) {}

title = 'angular-nodejs-example';

assegnatarioForm = new FormGroup({
  id: new FormControl('', Validators.nullValidator && Validators.required),
  nome: new FormControl('', Validators.nullValidator && Validators.required),
  cognome: new FormControl('', Validators.nullValidator && Validators.required),
  data_decesso: new FormControl('', Validators.nullValidator && Validators.required),
  comune_decesso: new FormControl('', Validators.nullValidator && Validators.required),
  fk_user_modifier: new FormControl('', Validators.nullValidator && Validators.required),
  data_update: new FormControl('', Validators.nullValidator && Validators.required),
  data_insert: new FormControl('', Validators.nullValidator && Validators.required)


});

assegnatari: any[] = [];
assegnatarioCount = 0;

destroy$: Subject<boolean> = new Subject<boolean>();

onSubmit() {
  this.appService.addAssegnatario(this.assegnatarioForm.value, this.assegnatarioCount + 1).pipe(takeUntil(this.destroy$)).subscribe(data => {
    console.log('message::::', data);
    this.assegnatarioCount = this.assegnatarioCount + 1;
    console.log(this.assegnatarioCount);
    this.assegnatarioForm.reset();
    this.getAllAssegnatari();
  });
}

getAllAssegnatari() {
  this.appService.getAssegnatari().pipe(takeUntil(this.destroy$)).subscribe((assegnatari: any[]) => {
  this.assegnatarioCount =assegnatari.length;
      this.assegnatari = assegnatari;
  });
}

ngOnDestroy() {
  this.destroy$.next(true);
  this.destroy$.unsubscribe();
}




/*
@Input() assegnatari: any[];
*/
ngOnInit() {
  console.log('esegui all assegnatario on init');
  this.getAllAssegnatari();
  }

}
