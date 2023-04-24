import { Component, EventEmitter, Output } from '@angular/core';
import { Subject } from 'rxjs';
import { takeUntil } from 'rxjs/operators';
import { AppService } from 'src/app/app.service';
import { Contraente1 } from 'src/app/app-state/models';
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
  
  
    filtraCercacontraenti(contraenteForm: Contraente1) {
      this.appService.cercaCercacontraenti(contraenteForm).pipe(takeUntil(this.destroy$)).subscribe((data: any) => {
        this.cercacontraenteCount =data.length;
        this.contraenti = data.contraenti;
        });
    }
  
    getAllCercaontraenti() {
      this.appService.getContraenti().pipe(takeUntil(this.destroy$)).subscribe((contraenti: any[]) => {
      this.cercacontraenteCount =contraenti.length;
          this.contraenti = contraenti;
      });
    }
  
  
    ngOnInit() {
      console.log('esegui all contraente1 on init');
      this.getAllCercaontraenti();
      }
  
      showCercacontraentiModal(){
        $('#cercacontraentiModal').modal('show');
      }
  
      assegnaContraente(contraenti: any[]){
  
        this.save.emit(contraenti);
        $('#cercacontraentiModal').modal('hide');
        
      
      }
  }
