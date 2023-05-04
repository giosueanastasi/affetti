import { ComponentFixture, TestBed } from '@angular/core/testing';

import { PostoModel2Component } from './posto-model2.component';

describe('PostoModel2Component', () => {
  let component: PostoModel2Component;
  let fixture: ComponentFixture<PostoModel2Component>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ PostoModel2Component ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(PostoModel2Component);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
