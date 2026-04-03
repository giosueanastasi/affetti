import { Component, Input } from '@angular/core';
import { Router } from '@angular/router';
import { Defunto } from 'src/app/app-state/models/defunto.model';

@Component({
  selector: 'app-defunto-card',
  templateUrl: './defunto-card.component.html',
  styleUrls: ['./defunto-card.component.css']
})
export class DefuntoCardComponent {

  @Input() defunto: Defunto;

  constructor(private router: Router) { }

  dettaglio(): void {
    this.router.navigate(['/defunti', this.defunto.id]);
  }
}
