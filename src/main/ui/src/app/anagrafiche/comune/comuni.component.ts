import { Component, EventEmitter, Input, OnDestroy, OnInit, Output } from '@angular/core';
import { FormGroup, FormControl, Validators } from '@angular/forms';
import { AppService } from '../../app.service';
import { takeUntil } from 'rxjs/operators';
import { Subject } from 'rxjs';


@Component({
  selector: 'app-comuni',
  templateUrl: './comuni.component.html',
  styleUrls: ['./comuni.component.css']
})

export class ComuniComponent implements OnInit, OnDestroy {

constructor(private appService: AppService) {}

title = 'angular-nodejs-example';

comuneForm = new FormGroup({
  id: new FormControl('', Validators.nullValidator && Validators.required),
  nome: new FormControl('', Validators.nullValidator && Validators.required),
  provincia: new FormControl('', Validators.nullValidator && Validators.required),
});

comuni: any[] = [];
comuneCount = 0;

destroy$: Subject<boolean> = new Subject<boolean>();

onSubmit() {
  this.appService.addComune(this.comuneForm.value, this.comuneCount + 1).pipe(takeUntil(this.destroy$)).subscribe(data => {
    console.log('message::::', data);
    this.comuneCount = this.comuneCount + 1;
    console.log(this.comuneCount);
    this.comuneForm.reset();
    this.getAllComuni();
  });
}

getAllComuni() {
  this.appService.getComuni().pipe(takeUntil(this.destroy$)).subscribe((comuni: any[]) => {
  this.comuneCount =comuni.length;
      this.comuni = comuni;
  });
}

ngOnDestroy() {
  this.destroy$.next(true);
  this.destroy$.unsubscribe();
}




/*
@Input() comuni: any[];
*/
ngOnInit() {
  console.log('esegui all posti on init');
  this.getAllComuni();
  }

}
