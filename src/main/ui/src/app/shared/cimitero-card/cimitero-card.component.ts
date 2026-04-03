import { Component, Input, Output, EventEmitter } from '@angular/core';
import { CimiteroSelect } from 'src/app/app-state/models/cimitero-select.model';

@Component({
  selector: 'app-cimitero-card',
  templateUrl: './cimitero-card.component.html',
  styleUrls: ['./cimitero-card.component.css']
})
export class CimiteroCardComponent {

  @Input() cimitero: CimiteroSelect;
  @Input() active: boolean = true;
  @Output() toggled = new EventEmitter<void>();

  toggle(): void {
    this.toggled.emit();
  }
}
