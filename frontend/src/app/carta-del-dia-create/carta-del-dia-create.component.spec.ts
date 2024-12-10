import { ComponentFixture, TestBed } from '@angular/core/testing';

import { CartadeldiaCreateComponent } from './carta-del-dia-create.component';

describe('CartadDelDiaCreateComponent', () => {
  let component: CartadeldiaCreateComponent;
  let fixture: ComponentFixture<CartadeldiaCreateComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [CartadeldiaCreateComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(CartadeldiaCreateComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
