import { Component, OnInit, Output, EventEmitter } from '@angular/core';
import { Contraente } from 'src/app/app-state/models';
import { AppService } from 'src/app/app.service';



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



  saveContraente() {
    this.appService.saveContraente(this.contraente).pipe().subscribe(data => {
      this.save.emit(data);
      $('#contranetiModal').modal('hide');
    }, error => {
      this.errorMessage = "Errore imprevisto, contattare l'assistenza";
      console.log(error);
    });
  }
  
  showContraentiModal(){
    $('#contranetiModal').modal('show');
  }
}
