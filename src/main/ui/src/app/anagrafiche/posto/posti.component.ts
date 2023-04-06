import { Component, EventEmitter, Input, OnDestroy, OnInit, Output } from '@angular/core';
import { FormGroup, FormControl, Validators } from '@angular/forms';
import { AppService } from '../../app.service';
import { takeUntil } from 'rxjs/operators';
import { Subject } from 'rxjs';


@Component({
  selector: 'app-posti',
  templateUrl: './posti.component.html',
  styleUrls: ['./posti.component.css']
})

export class PostiComponent implements OnInit, OnDestroy {

constructor(private appService: AppService) {}

title = 'angular-nodejs-example';

postoForm = new FormGroup({
  id: new FormControl('', Validators.nullValidator && Validators.required),
  loculo: new FormControl('', Validators.nullValidator && Validators.required),
  fornice: new FormControl('', Validators.nullValidator && Validators.required),
  data_insert: new FormControl('', Validators.nullValidator && Validators.required),
  data_update: new FormControl('', Validators.nullValidator && Validators.required),
  tipo: new FormControl('', Validators.nullValidator && Validators.required),
  fk_user_modifier: new FormControl('', Validators.nullValidator && Validators.required),

});

posti: any[] = [];
postoCount = 0;

destroy$: Subject<boolean> = new Subject<boolean>();

onSubmit() {
  this.appService.addPosto(this.postoForm.value, this.postoCount + 1).pipe(takeUntil(this.destroy$)).subscribe(data => {
    console.log('message::::', data);
    this.postoCount = this.postoCount + 1;
    console.log(this.postoCount);
    this.postoForm.reset();
    this.getAllPosti();
  });
}

getAllPosti() {
  this.appService.getPosti().pipe(takeUntil(this.destroy$)).subscribe((posti: any[]) => {
  this.postoCount =posti.length;
      this.posti = posti;
  });
}

ngOnDestroy() {
  this.destroy$.next(true);
  this.destroy$.unsubscribe();
}




/*
@Input() posti: any[];
*/
ngOnInit() {
  console.log('esegui all posto on init');
  this.getAllPosti();
  }

}
