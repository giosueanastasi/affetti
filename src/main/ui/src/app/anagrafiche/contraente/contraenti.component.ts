import { Component, EventEmitter, Input, OnDestroy, OnInit, Output, ViewChild } from '@angular/core';
import { FormGroup, FormControl, Validators } from '@angular/forms';
import { AppService } from '../../app.service';
import { takeUntil } from 'rxjs/operators';
import { Subject } from 'rxjs';
import { ContraentiModelComponent } from '../contraenti-model/contraenti-model.component';
import { Contraente1, Contratto } from 'src/app/app-state/models';
import { ContrattoModelComponent } from '../contratto-model/contratto-model.component';
import {MatTableDataSource} from '@angular/material/table';
import {MatPaginator} from '@angular/material/paginator';


@Component({
  selector: 'app-contraenti',
  templateUrl: './contraenti.component.html',
  styleUrls: ['./contraenti.component.css']
})

export class ContraentiComponent implements OnInit, OnDestroy {


title = 'angular-nodejs-example';

contraenteForm = new FormGroup({
  nome: new FormControl('', Validators.nullValidator ),
  cognome: new FormControl('', Validators.nullValidator ),
  codice_fiscale: new FormControl('', Validators.nullValidator ),
  protocollo: new FormControl('', Validators.nullValidator ),

});

@ViewChild(ContrattoModelComponent) child: ContrattoModelComponent | undefined;

contraenti: any[] = [];
contraenteCount = 0;

contratto: Contratto = new Contratto();
//protocolloSelezionato: string= "";


destroy$: Subject<boolean> = new Subject<boolean>();

contraente1: Contraente1 = new Contraente1();
 errorMessage: string = "";

 @Output() save =  new EventEmitter<any>();
 constructor(private appService: AppService) { }

//Elementi tabella material
dataSource = new MatTableDataSource<Contraente1>([]);
displayedColumns: string[] = ['nome', 'cognome','codice fiscale', 'residenza', 'contratto' , 'dettaglio'];

//Elementi paginator
totalElements = 0;
pageSize ;
currentPage = 0;

@ViewChild(MatPaginator) paginator: MatPaginator;

ngAfterViewInit() {
   this.dataSource.paginator = this.paginator;
 }

getAllContraenti() {
  this.appService.getContraenti().pipe(takeUntil(this.destroy$)).subscribe((contraenti: any[]) => {
  this.contraenteCount =contraenti.length;
      this.contraenti = contraenti;
  });
}

filtraContraenti() {
  let contraenteFiltrato = new Contraente1();
  contraenteFiltrato.nome = this.contraenteForm.controls['nome'].value;
  contraenteFiltrato.cognome = this.contraenteForm.controls['cognome'].value;
  contraenteFiltrato.codice_fiscale = this.contraenteForm.controls['codice_fiscale'].value;
  contraenteFiltrato.protocolloC = this.contraenteForm.controls['protocollo'].value;

  this.appService.cercaCercacontraenti(contraenteFiltrato, this.paginator.pageIndex, this.paginator.pageSize).pipe(takeUntil(this.destroy$)).subscribe((data: any) => {
    // this.contraenteCount =data.length;
    // this.contraenti = data.contraenti.content;
    this.dataSource = data.contraenti.content;
    this.totalElements = data.contraenti.totalElements;
    });
  
}

ngOnDestroy() {
  this.destroy$.next(true);
  this.destroy$.unsubscribe();
}


ngOnInit() {
  console.log('esegui all contraenti on init');
 // this.getAllContraenti();
  }

//Metodo per recuperare il dettaglio del contratto selezionato tramite il numero di protocollo
showContrattoByProtocollo( numProtocollo: String ){
  this.appService.getContrattoByProtocollo(numProtocollo).pipe(takeUntil(this.destroy$)).subscribe((data: any) => {
    this.contratto = data.contratti[0];
    this.child?.showContrattoModal();
  });
   
}

//Metodo per gestire il cambio di pagina del paginator
onPageChange(event: any){
  this.filtraContraenti();
}

}
