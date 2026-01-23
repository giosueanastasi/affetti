import { Component } from '@angular/core';

@Component({
  selector: 'app-termini-condizioni',
  templateUrl: './termini-condizioni.component.html',
  styleUrls: ['./termini-condizioni.component.css']
})
export class TerminiCondizioniComponent {
  currentDate: string;

  constructor() {
    const today = new Date();
    this.currentDate = today.toLocaleDateString('it-IT', {
      year: 'numeric',
      month: 'long',
      day: 'numeric'
    });
  }
}
