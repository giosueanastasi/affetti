import { Component, EventEmitter, Input, Output } from '@angular/core';
import { Contratto, User } from 'src/app/app-state/models';
import { AppService } from 'src/app/app.service';


declare var $ : any;

@Component({
  selector: 'app-contratto-model',
  templateUrl: './contratto-model.component.html',
  styleUrls: ['./contratto-model.component.css']
})
export class ContrattoModelComponent {


  @Input() contratto: Contratto = new Contratto();
  @Output() save = new EventEmitter<any>();

  constructor(private appService: AppService) { }

  showContrattoModal(){
    $('#contrattoModal').modal('show');
  }

  saveContratto() {
    debugger;
    this.appService.saveContratto(this.contratto).pipe().subscribe(data => {
      this.save.emit(data);
      $('#contrattoModal').modal('hide');
    });
  }
}
