import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { PostiModelComponent } from './posti-model.component';

describe('PostoModelComponent', () => {
  let component: PostiModelComponent;
  let fixture: ComponentFixture<PostiModelComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ PostiModelComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(PostiModelComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
