import { Component, OnInit, Output, EventEmitter } from '@angular/core';
import { Contraente } from 'src/app/app-state/models';
import { AppService } from 'src/app/app.service';
import { takeUntil } from 'rxjs/operators';
import { Subject } from 'rxjs';

declare var $ : any;

@Component({
  selector: 'app-contraenti-model',
  templateUrl: './contraenti-model.component.html',
  styleUrls: ['./contraenti-model.component.css']
})
export class ContraentiModelComponent  {

  contraente: Contraente = new Contraente();
  errorMessage: string = "";

  @Output() save =  new EventEmitter<any>();
  constructor(private appService: AppService) { }
  comuniCount = 0;
  comuni: any[] = [];
  destroy$: Subject<boolean> = new Subject<boolean>();
  saveContraente() {
    debugger;
    this.appService.saveContraente(this.contraente).pipe().subscribe(data => {
      this.save.emit(data);
      $('#contraentiModal').modal('hide');
    }, error => {
      this.errorMessage = "Errore imprevisto, contattare l'assistenza";
      console.log(error);
    });
  }
  
  showContraentiModal(){
    $('#contraentiModal').modal('show');
    this.getAllComuni();
  }
  getAllComuni(){
    this.appService.getComuni().pipe(takeUntil(this.destroy$)).subscribe((data: any) => {
              this.comuniCount = data.length;
              this.comuni = data.comuni;
          });
  }

  ngOnInit() {
    //this.getAllComuni();
    }
}
