import { Component, EventEmitter, Input, Output, ViewChild } from '@angular/core';
import { Posto1 } from 'src/app/app-state/models';
import { AppService } from 'src/app/app.service';
import { CoordinateMapModalComponent } from '../coordinate-map-modal/coordinate-map-modal.component';

declare var $ : any;

@Component({
  selector: 'app-posto-edit',
  templateUrl: './posto-edit.component.html',
  styleUrls: ['./posto-edit.component.css']
})
export class PostoEditComponent {

  @Input() posto1: Posto1 = new Posto1();
  @Output() save = new EventEmitter<any>();
  @ViewChild(CoordinateMapModalComponent) coordinateMapModal: CoordinateMapModalComponent | undefined;

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

  openCoordinateModal() {
    this.coordinateMapModal?.showMapModal(
      this.posto1.latitudine,
      this.posto1.longitudine
    );
  }

  savePostoCoordinates(coordinates: {latitudine: number, longitudine: number}) {
    this.posto1.latitudine = coordinates.latitudine;
    this.posto1.longitudine = coordinates.longitudine;
  }

}

