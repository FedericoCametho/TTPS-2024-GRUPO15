import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ComidaUpdateComponent } from './comida-update.component';

describe('ComidaUpdateComponent', () => {
  let component: ComidaUpdateComponent;
  let fixture: ComponentFixture<ComidaUpdateComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [ComidaUpdateComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(ComidaUpdateComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
