import { Component,EventEmitter, Input, Output } from '@angular/core';
import { Posto1 } from 'src/app/app-state/models';
import { AppService } from 'src/app/app.service';

declare var $ : any;

@Component({
  selector: 'app-posto-edit',
  templateUrl: './posto-edit.component.html',
  styleUrls: ['./posto-edit.component.css']
})
export class PostoEditComponent {

  @Input() posto1: Posto1 = new Posto1();
  @Output() save = new EventEmitter<any>();

  constructor(private appService: AppService) { }

  errorMessage: string = "";

  showPostoModal(){
    $('#postoEdit').modal('show');
  }

  savePostoEdit() {
    this.appService.savePosto(this.posto1).pipe().subscribe((data: any) => {
      this.save.emit(data.posti[0]);
      $('#postoEdit').modal('hide');
    });
  }


  }

