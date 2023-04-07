import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { AssegnatariComponent } from './assegnatari.component';

describe('AssegnatarioComponent', () => {
  let component: AssegnatariComponent;
  let fixture: ComponentFixture<AssegnatariComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ AssegnatariComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(AssegnatariComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
