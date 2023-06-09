import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { ComuniComponent } from './comuni.component';

describe('ComuneComponent', () => {
  let component: ComuniComponent;
  let fixture: ComponentFixture<ComuniComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ ComuniComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ComuniComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
