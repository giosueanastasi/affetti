import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { DomandaFullComponent } from './domanda-full.component';

describe('DomandaFullComponent', () => {
  let component: DomandaFullComponent;
  let fixture: ComponentFixture<DomandaFullComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ DomandaFullComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(DomandaFullComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
