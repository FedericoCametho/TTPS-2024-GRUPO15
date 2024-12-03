import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ResponsableDeTurnoRegisterComponent } from './responsable-de-turno-register.component';

describe('ResponsableDeTurnoRegisterComponent', () => {
  let component: ResponsableDeTurnoRegisterComponent;
  let fixture: ComponentFixture<ResponsableDeTurnoRegisterComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [ResponsableDeTurnoRegisterComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(ResponsableDeTurnoRegisterComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
