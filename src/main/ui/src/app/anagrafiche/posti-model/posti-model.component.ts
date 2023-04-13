import { Component, OnInit, Output, EventEmitter } from '@angular/core';
import { FormGroup } from '@angular/forms';
import { Posto1 } from 'src/app/app-state/models';
import { AppService } from 'src/app/app.service';

declare var $ : any;

@Component({
  selector: 'app-posti-model',
  templateUrl: './posti-model.component.html',
  styleUrls: ['./posti-model.component.css']
})

export class PostiModelComponent  {

posto1: Posto1 = new Posto1();
 errorMessage: string = "";

  @Output() save =  new EventEmitter<any>();
  constructor(private appService: AppService) { }


  filtraPosti(postoForm: Posto1) {
    console.log(postoForm);
  }

  savePosto() {
    this.appService.savePosto(this.posto1).pipe().subscribe(data => {
      this.save.emit(data);
      $('#postiModal').modal('hide');
    }, error => {
      this.errorMessage = "Errore imprevisto, contattare l'assistenza";
      console.log(error);
    });
  }

  showPostiModal(){
    $('#postiModal').modal('show');
  }

}
