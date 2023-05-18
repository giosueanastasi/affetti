import { ComponentFixture, TestBed } from '@angular/core/testing';

import { DomandaModelComponent } from './domanda-model.component';

describe('DomandaModelComponent', () => {
  let component: DomandaModelComponent;
  let fixture: ComponentFixture<DomandaModelComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ DomandaModelComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(DomandaModelComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
