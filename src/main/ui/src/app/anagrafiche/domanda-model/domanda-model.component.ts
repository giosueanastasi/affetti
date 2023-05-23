import { Component,EventEmitter, Input, Output } from '@angular/core';
import { AppService } from 'src/app/app.service';
import { Domanda } from 'src/app/app-state/models';

declare var $ : any;

@Component({
  selector: 'app-domanda-model',
  templateUrl: './domanda-model.component.html',
  styleUrls: ['./domanda-model.component.css']
})

export class DomandaModelComponent {
  @Input() domanda: Domanda = new Domanda();
  @Output() save = new EventEmitter<any>();

  constructor(private appService: AppService) { }

  errorMessage: string = "";

  showDomandaModal(){
    $('#domandaModal').modal('show');
  }

  saveDomandaModal() {
    this.appService.saveDomanda(this.domanda).pipe().subscribe((data: any) => {
      this.save.emit(data.domanda);
      $('#domandaModal').modal('hide');
    });
  }

}
