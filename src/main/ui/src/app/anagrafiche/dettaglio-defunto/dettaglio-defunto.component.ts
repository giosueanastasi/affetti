import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { AppService } from 'src/app/app.service';

@Component({
  selector: 'app-dettaglio-defunto',
  templateUrl: './dettaglio-defunto.component.html',
  styleUrls: ['./dettaglio-defunto.component.css']
})
export class DettaglioDefuntoComponent implements OnInit{

  defuntoId: number;
  defunto: any;

  constructor(private appService: AppService, private route: ActivatedRoute) {}

  ngOnInit(): void {
    this.defuntoId = +this.route.snapshot.paramMap.get('id');

    this.appService.cercaDefuntoById(this.defuntoId).subscribe(data => {
      this.defunto = data;
    })
  }

}
