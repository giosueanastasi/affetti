import { Component, OnInit, Output, EventEmitter, ViewChild } from '@angular/core';
import { FormGroup } from '@angular/forms';
import { MatPaginator } from '@angular/material/paginator';
import { MatTableDataSource } from '@angular/material/table';
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

  //Elementi tabella material
  dataSource = new MatTableDataSource<Posto1>([]);
  displayedColumns: string[] = ['checkbox', 'stato', 'loculo', 'fornice', 'nome', 'cognome', 'scadenza'];

  //Elementi paginator
  totalElements = 0;
  pageSize;
  currentPage = 0;

  @ViewChild(MatPaginator) paginator: MatPaginator;

  ngAfterViewInit() {
    this.dataSource.paginator = this.paginator;
  }

  filtraPosti(postoForm: Posto1) {
    this.appService.cercaPosti(postoForm, this.paginator.pageIndex, this.paginator.pageSize).pipe(takeUntil(this.destroy$)).subscribe((data: any) => {
      this.totalElements = data.posti.totalElements;
      this.posti = data.posti.content;
      this.dataSource = data.posti.content;
      });
  }

  getAllPosti() {
    this.appService.getPosti().pipe(takeUntil(this.destroy$)).subscribe((posti: any[]) => {
    this.postoCount =posti.length;
        this.posti = posti;
    });
  }


  ngOnInit() {
    console.log('esegui all posto1 on init');
    //this.getAllPosti();
    }

    showPostiModal(){
      $('#postiModal').modal('show');
    }

    assegnaPosto(posti: any[]){

      this.save.emit(posti);
      $('#postiModal').modal('hide');
      
    
    }

    //Funzione per impedire di selezionare più di un checkbox
    setCheckboxes(isChecked: boolean, id: string){
      isChecked && this.posti.forEach(posti => {
        if (posti.id !== id) posti.checked = false;
      });
    }

    //Metodo per gestire il cambio di pagina del paginator
    onPageChange(event: any){
      this.filtraPosti(this.posto1);
    }
}