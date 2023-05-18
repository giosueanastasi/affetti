import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ContrattoModelComponent } from './contratto-model.component';

describe('ContrattoModelComponent', () => {
  let component: ContrattoModelComponent;
  let fixture: ComponentFixture<ContrattoModelComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ContrattoModelComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(ContrattoModelComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
