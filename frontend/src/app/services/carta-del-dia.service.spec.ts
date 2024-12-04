import { TestBed } from '@angular/core/testing';

import { CartaDelDiaService } from './carta-del-dia.service';

describe('CartaDelDiaService', () => {
  let service: CartaDelDiaService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(CartaDelDiaService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
