import { TestBed } from '@angular/core/testing';
import {HttpInterceptorService} from "./http-interceptor.service";



describe('HttpInterseptorService', () => {
  let service: HttpInterceptorService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(HttpInterceptorService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
