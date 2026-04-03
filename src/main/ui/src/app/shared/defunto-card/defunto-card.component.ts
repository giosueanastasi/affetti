import { Component, EventEmitter, Input, Output } from '@angular/core';
import { Router } from '@angular/router';
import { Defunto } from 'src/app/app-state/models/defunto.model';

@Component({
  selector: 'app-defunto-card',
  templateUrl: './defunto-card.component.html',
  styleUrls: ['./defunto-card.component.css']
})
export class DefuntoCardComponent {

  @Input() defunto: Defunto;
  @Input() primaryColor: string | null = null;
  @Input() domandaId: number | null = null;
  @Input() contrattoId: number | null = null;

  @Output() openDomanda = new EventEmitter<number>();
  @Output() openContratto = new EventEmitter<number>();

  constructor(private router: Router) { }

  dettaglio(): void {
    this.router.navigate(['/defunti', this.defunto.id]);
  }

  onDomandaClick(event: MouseEvent): void {
    event.stopPropagation();
    if (this.domandaId) {
      this.openDomanda.emit(this.domandaId);
    }
  }

  onContrattoClick(event: MouseEvent): void {
    event.stopPropagation();
    if (this.contrattoId) {
      this.openContratto.emit(this.domandaId!);
    }
  }
}
