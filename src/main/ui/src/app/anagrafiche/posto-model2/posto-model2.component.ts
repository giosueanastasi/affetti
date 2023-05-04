import { Component,EventEmitter, Input, Output } from '@angular/core';
import { Contratto } from 'src/app/app-state/models';
import { AppService } from 'src/app/app.service';

declare var $ : any;

@Component({
  selector: 'app-posto-model2',
  templateUrl: './posto-model2.component.html',
  styleUrls: ['./posto-model2.component.css']
})
export class PostoModel2Component {

  @Input() contratto: Contratto = new Contratto();
  @Output() save = new EventEmitter<any>();

  constructor(private appService: AppService) { }

 

  showContrattoModal(){
    $('#contrattoModal').modal('show');
  }

  saveContratto() {
    //debugger;
    this.appService.saveContratto(this.contratto).pipe().subscribe(data => {
      this.save.emit(data);
      $('#contrattoModal').modal('hide');
    });
  }


  }

