import { Component, EventEmitter, Input, OnDestroy, OnInit, Output, ViewChild } from '@angular/core';
import { FormGroup, FormControl, Validators } from '@angular/forms';
import { AppService } from '../../app.service';
import { takeUntil } from 'rxjs/operators';
import { Subject } from 'rxjs';
import { ContrattoModelComponent } from '../contratto-model/contratto-model.component';
import { Contratto } from 'src/app/app-state/models';
import { MatTableDataSource } from '@angular/material/table';
import { MatPaginator } from '@angular/material/paginator';
import { ContrattiSearch } from 'src/app/app-state/models/contrattiSearch.model';



@Component({
  selector: 'app-contratti',
  templateUrl: './contratti.component.html',
  styleUrls: ['./contratti.component.css']
})
export class ContrattiComponent implements OnInit {

  selectedContratto: Contratto = new Contratto();

  constructor(private appService: AppService) {}

  title = 'angular-nodejs-example';

  @ViewChild(ContrattoModelComponent) child: ContrattoModelComponent | undefined;
  @ViewChild(MatPaginator) paginator: MatPaginator;

  ngAfterViewInit() {
   this.dataSource.paginator = this.paginator;
 }

  //Elementi tabella material
  dataSource = new MatTableDataSource<Contratto>([]);
  displayedColumns: string[] = ['numeroProtocolloContratto', 'stato','dataProtocolloContratto', 'dataProtocolloContratto', 'contraente' , 'assegnatario', 'dettaglio'];

  //Elementi paginator
  totalElements = 0;
  pageSize;
  currentPage = 0;

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

  createContrattoRequest(){
    this.selectedContratto = new Contratto();
    this.child?.showContrattoModal();
  }
  editContrattoRequest(item: Contratto){
    this.selectedContratto = Object.assign({},item);
    this.child?.showContrattoModal();
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

  filtraContratti() {
      let contrattoFiltrato = new ContrattiSearch();
      contrattoFiltrato.nome = this.contrattoForm.controls['nome'].value;
      contrattoFiltrato.cognome = this.contrattoForm.controls['cognome'].value;
      contrattoFiltrato.tipologia = this.contrattoForm.controls['tipologia'].value;
      contrattoFiltrato.codiceFiscale = this.contrattoForm.controls['codice_fiscale'].value;
      contrattoFiltrato.numeroProtocollo = this.contrattoForm.controls['numero_protocollo'].value;
      contrattoFiltrato.stato = this.contrattoForm.controls['stato'].value;
      contrattoFiltrato.stato = this.contrattoForm.controls['data_protocollo_iniziale'].value;
      contrattoFiltrato.stato = this.contrattoForm.controls['data_protocollo_finale'].value;

    this.appService.cercaContrattiService(contrattoFiltrato, this.paginator.pageIndex, this.paginator.pageSize).pipe(takeUntil(this.destroy$)).subscribe((data: any) => {
      this.dataSource = data.contratti.content;
      this.totalElements = data.contratti.totalElements;
    }); 
}
  
/*
  @Input() contratti: any[];
*/
  ngOnInit() {
    console.log('esegui all contratto on init');
    //this.getAllContratti();
    }

    saveContrattoWatcher(contratto: Contratto){
      let contrattoIndex = this.contratti.findIndex(item => item.id === contratto.id);
      if(contrattoIndex !==-1){
        this.contratti[contrattoIndex] = contratto;
      }else{
        this.contratti.push(contratto);
      }
    }

  //Metodo per gestire il cambio di pagina del paginator
  onPageChange(event: any){
  this.filtraContratti();
  }

}
