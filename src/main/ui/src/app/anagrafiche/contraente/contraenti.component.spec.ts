import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { ContraentiComponent } from './contraenti.component';

describe('ComuneComponent', () => {
  let component: ContraentiComponent;
  let fixture: ComponentFixture<ContraentiComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ ContraentiComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ContraentiComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
