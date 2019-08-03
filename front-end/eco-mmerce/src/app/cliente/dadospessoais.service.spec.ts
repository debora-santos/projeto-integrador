import { TestBed } from '@angular/core/testing';

import { DadospessoaisService } from './dadospessoais.service';

describe('DadospessoaisService', () => {
  beforeEach(() => TestBed.configureTestingModule({}));

  it('should be created', () => {
    const service: DadospessoaisService = TestBed.get(DadospessoaisService);
    expect(service).toBeTruthy();
  });
});
