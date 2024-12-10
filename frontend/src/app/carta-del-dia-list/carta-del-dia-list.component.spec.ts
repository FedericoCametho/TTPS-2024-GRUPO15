import { ComponentFixture, TestBed } from '@angular/core/testing';

import { CartaDelDiaListComponent } from './carta-del-dia-list.component';

describe('CartaDelDiaListComponent', () => {
  let component: CartaDelDiaListComponent;
  let fixture: ComponentFixture<CartaDelDiaListComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [CartaDelDiaListComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(CartaDelDiaListComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
