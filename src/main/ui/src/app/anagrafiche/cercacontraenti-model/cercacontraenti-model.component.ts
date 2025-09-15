import { Component, EventEmitter, Output, ViewChild } from '@angular/core';
import { Subject } from 'rxjs';
import { takeUntil } from 'rxjs/operators';
import { AppService } from 'src/app/app.service';
import { Contraente1 } from 'src/app/app-state/models';
import { MatTableDataSource } from '@angular/material/table';
import { MatPaginator } from '@angular/material/paginator';
declare var $ : any;

@Component({
  selector: 'app-cercacontraenti-model',
  templateUrl: './cercacontraenti-model.component.html',
  styleUrls: ['./cercacontraenti-model.component.css']
})
export class CercacontraentiModelComponent {

  cercacontraenteCount = 0;

  destroy$: Subject<boolean> = new Subject<boolean>();
  
  contraente1: Contraente1 = new Contraente1();
   errorMessage: string = "";
  
    @Output() save =  new EventEmitter<any>();
    constructor(private appService: AppService) { }
  
    contraenti: any[] = [];

    //Elementi tabella material
    dataSource = new MatTableDataSource<Contraente1>([]);
    displayedColumns: string[] = ['checkbox' , 'nome' , 'cognome','codice fiscale', 'comune residenza', 'via residenza' , 'provincia residenza'];
    
    //Elementi paginator
    totalElements = 0;
    pageSize;
    
    @ViewChild(MatPaginator) paginator: MatPaginator;
    
    ngAfterViewInit() {
       this.dataSource.paginator = this.paginator;
     }
  
  
    filtraCercacontraenti(contraenteForm: Contraente1, resetPage: boolean = false) {
      if(resetPage) {
        this.paginator.pageIndex = 0;
      }

      this.appService.cercaCercacontraenti(contraenteForm, this.paginator.pageIndex, this.paginator.pageSize).pipe(takeUntil(this.destroy$)).subscribe((data: any) => {
        this.dataSource = data.contraenti.content;
        this.totalElements = data.contraenti.totalElements;
        this.contraenti = data.contraenti.content;
        });
    }
  
    getAllCercacontraenti() {
      this.appService.getContraenti().pipe(takeUntil(this.destroy$)).subscribe((contraenti: any[]) => {
      this.cercacontraenteCount =contraenti.length;
          this.contraenti = contraenti;
      });
    }
  
  
    ngOnInit() {
      //console.log('esegui all contraente1 on init');
      //this.getAllCercacontraenti();
      }
  
      showCercacontraentiModal(){
        $('#cercacontraentiModal').modal('show');
      }
  
      assegnaContraente(contraenti: any[]){
  
        this.save.emit(contraenti);
        $('#cercacontraentiModal').modal('hide');
        
      
      }

      //Funzione per impedire di selezionare più di un checkbox
      setCheckboxes(isChecked: boolean, id: string){
        isChecked && this.contraenti.forEach(contraente => {
          if (contraente.id !== id) contraente.checked = false;
        });
      }

      //Metodo per gestire il cambio di pagina del paginator
      onPageChange(event: any){
        this.filtraCercacontraenti(this.contraente1);
      }
  }
