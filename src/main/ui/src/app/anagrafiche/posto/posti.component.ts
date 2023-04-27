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
  loculo: new FormControl('', Validators.nullValidator && Validators.required),
  fornice: new FormControl('', Validators.nullValidator),
  stato: new FormControl('', Validators.nullValidator),

});

posti: any[] = [];
postoCount = 0;

destroy$: Subject<boolean> = new Subject<boolean>();

posto1: Posto1 = new Posto1();
 errorMessage: string = "";

 @Output() save =  new EventEmitter<any>();
 constructor(private appService: AppService) { }

 filtraPosti(postoForm: Posto1) {
   this.appService.cercaPosti(postoForm).pipe(takeUntil(this.destroy$)).subscribe((data: any) => {
     this.postoCount =data.length;
     this.posti = data.posti;
     });
 }

/*onSubmit() {
  this.appService.addPosto(this.postoForm.value, this.postoCount + 1).pipe(takeUntil(this.destroy$)).subscribe(data => {
    console.log('message::::', data);
    this.postoCount = this.postoCount + 1;
    console.log(this.postoCount);
    this.postoForm.reset();
    this.getAllPosti();
  });
}*/

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
