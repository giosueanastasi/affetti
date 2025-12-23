import { ComponentFixture, TestBed } from '@angular/core/testing';

import { DettaglioDefuntoComponent } from './dettaglio-defunto.component';

describe('DettaglioDefuntoComponent', () => {
  let component: DettaglioDefuntoComponent;
  let fixture: ComponentFixture<DettaglioDefuntoComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [DettaglioDefuntoComponent]
    });
    fixture = TestBed.createComponent(DettaglioDefuntoComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
