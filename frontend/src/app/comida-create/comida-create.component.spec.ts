import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ComidaCreateComponent } from './comida-create.component';

describe('ComidaCreateComponent', () => {
  let component: ComidaCreateComponent;
  let fixture: ComponentFixture<ComidaCreateComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [ComidaCreateComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(ComidaCreateComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
