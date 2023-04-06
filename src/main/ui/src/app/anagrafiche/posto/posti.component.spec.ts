import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { PostiComponent } from './posti.component';

describe('PostiComponent', () => {
  let component: PostiComponent;
  let fixture: ComponentFixture<PostiComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ PostiComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(PostiComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
