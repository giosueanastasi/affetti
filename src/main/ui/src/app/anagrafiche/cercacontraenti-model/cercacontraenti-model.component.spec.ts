import { ComponentFixture, TestBed } from '@angular/core/testing';

import { CercacontraentiModelComponent } from './cercacontraenti-model.component';

describe('CercacontraentiModelComponent', () => {
  let component: CercacontraentiModelComponent;
  let fixture: ComponentFixture<CercacontraentiModelComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ CercacontraentiModelComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(CercacontraentiModelComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
