import { Component, OnInit, Output, EventEmitter } from '@angular/core';
import { FormGroup } from '@angular/forms';
import { Subject } from 'rxjs';
import { takeUntil } from 'rxjs/operators';
import {  Posto1 } from 'src/app/app-state/models';
import { AppService } from 'src/app/app.service';
declare var $ : any;

@Component({
  selector: 'app-posti-model',
  templateUrl: './posti-model.component.html',
  styleUrls: ['./posti-model.component.css']
})

export class PostiModelComponent  {

postoCount = 0;

destroy$: Subject<boolean> = new Subject<boolean>();

posto1: Posto1 = new Posto1();
 errorMessage: string = "";

  @Output() save =  new EventEmitter<any>();
  constructor(private appService: AppService) { }

  posti: any[] = [];
  searchValue : string;

  filtraPosti(postoForm: Posto1) {
    this.appService.cercaPosti(postoForm).pipe(takeUntil(this.destroy$)).subscribe((data: any) => {
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
    console.log('esegui all posto on init');
    this.getAllPosti();
    }

    showPostiModal(){
      $('#postiModal').modal('show');
    }

    assegnaPosto(posti: any[]){

      this.save.emit(posti);
      $('#postiModal').modal('hide');
      
    
    }
}