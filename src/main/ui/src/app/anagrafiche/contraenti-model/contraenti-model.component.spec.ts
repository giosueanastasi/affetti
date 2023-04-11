import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { ContraentiModelComponent } from './contraenti-model.component';

describe('ContraentiModelComponent', () => {
  let component: ContraentiModelComponent;
  let fixture: ComponentFixture<ContraentiModelComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ ContraentiModelComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ContraentiModelComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
