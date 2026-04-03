import { Component, OnInit, ViewChild } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Location } from '@angular/common';
import { AppService } from 'src/app/app.service';
import { CoordinateMapModalComponent } from '../coordinate-map-modal/coordinate-map-modal.component';

@Component({
  selector: 'app-dettaglio-defunto',
  templateUrl: './dettaglio-defunto.component.html',
  styleUrls: ['./dettaglio-defunto.component.css']
})
export class DettaglioDefuntoComponent implements OnInit{

  @ViewChild(CoordinateMapModalComponent) coordinateMapModal: CoordinateMapModalComponent | undefined;

  defuntoId: number;
  defunto: any;

  constructor(
    private appService: AppService,
    private route: ActivatedRoute,
    private location: Location
  ) {}

  ngOnInit(): void {
    this.defuntoId = +this.route.snapshot.paramMap.get('id');

    this.appService.cercaDefuntoById(this.defuntoId).subscribe(data => {
      this.defunto = data;
    })
  }

  goBack(): void {
    this.location.back();
  }

  openMap(): void {
    if (this.defunto.posto?.latitudine && this.defunto.posto?.longitudine) {
      this.coordinateMapModal?.showMapModal(
        this.defunto.posto.latitudine,
        this.defunto.posto.longitudine,
        true // readOnly = true per modalità visualizzazione
      );
    }
  }

}
