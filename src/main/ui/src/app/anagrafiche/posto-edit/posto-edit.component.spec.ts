import { ComponentFixture, TestBed } from '@angular/core/testing';

import { PostoEditComponent } from './posto-edit.component';

describe('PostoEdit2Component', () => {
  let component: PostoEditComponent;
  let fixture: ComponentFixture<PostoEditComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ PostoEditComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(PostoEditComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
