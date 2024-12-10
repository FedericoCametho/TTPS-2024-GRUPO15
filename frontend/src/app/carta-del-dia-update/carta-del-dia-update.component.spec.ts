import { ComponentFixture, TestBed } from '@angular/core/testing';

import { CartaDelDiaUpdateComponent } from './carta-del-dia-update.component';

describe('CartadDelDiaUpdateComponent', () => {
  let component: CartaDelDiaUpdateComponent;
  let fixture: ComponentFixture<CartaDelDiaUpdateComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [CartaDelDiaUpdateComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(CartaDelDiaUpdateComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
