import { ComponentFixture, TestBed } from '@angular/core/testing';

import { GenreEditFormComponent } from './genre-edit-form.component';

describe('GenreEditFormComponent', () => {
  let component: GenreEditFormComponent;
  let fixture: ComponentFixture<GenreEditFormComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ GenreEditFormComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(GenreEditFormComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
