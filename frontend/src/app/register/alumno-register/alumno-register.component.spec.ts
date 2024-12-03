import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AlumnoRegisterComponent } from './alumno-register.component';

describe('AlumnoRegisterComponent', () => {
  let component: AlumnoRegisterComponent;
  let fixture: ComponentFixture<AlumnoRegisterComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [AlumnoRegisterComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(AlumnoRegisterComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
