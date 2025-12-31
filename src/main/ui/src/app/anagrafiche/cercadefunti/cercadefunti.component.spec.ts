import { ComponentFixture, TestBed } from '@angular/core/testing';

import { CercadefuntiComponent } from './cercadefunti.component';

describe('CercadefuntiComponent', () => {
  let component: CercadefuntiComponent;
  let fixture: ComponentFixture<CercadefuntiComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [CercadefuntiComponent]
    });
    fixture = TestBed.createComponent(CercadefuntiComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
