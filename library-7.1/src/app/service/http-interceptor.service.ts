import {HttpErrorResponse, HttpEvent, HttpHandler, HttpInterceptor, HttpRequest} from '@angular/common/http';
import {Injectable} from '@angular/core';
import {Observable, tap} from 'rxjs';
import {AuthService} from './auth.service';
import {Router} from "@angular/router";

@Injectable()
export class HttpInterceptorService implements HttpInterceptor {

  constructor(private authService: AuthService,
              private router: Router) {
  }

  intercept(req: HttpRequest<any>, next: HttpHandler): Observable<HttpEvent<any>> {
    return next.handle(req).pipe(
      tap((event) => {
          if (!this.authService.isUserAuthenticated()) {
            this.router.navigate(['/auth-form']);
          }
      },
      (err) => {
        if (err instanceof HttpErrorResponse) {
          if (err.status == 401 || err.status == 403) {
            this.router.navigate(['/auth-form']);
          }
        }
      }
      ));
  }
}
