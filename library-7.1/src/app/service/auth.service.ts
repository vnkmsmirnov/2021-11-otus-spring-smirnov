import {HttpClient} from '@angular/common/http';
import {Injectable} from '@angular/core';
import {User} from "../model/user";
import {Router} from "@angular/router";

@Injectable({
  providedIn: 'root'
})
export class AuthService {
  SESSION_KEY = 'auth_user'
  user: User = new User();

  private readonly authUrl: string;

  constructor(private http: HttpClient,
              private router: Router) {
    this.authUrl = '/auth/'
  }

  authenticate(user: User) {
    return this.http.post<User>(<string>this.authUrl + 'login', user).subscribe((user: User) => {
      this.user = user;
      this.registerInSession(user);
      this.router.navigate(['/']);
    });
  }

  registerInSession(user: User) {
    sessionStorage.setItem(this.SESSION_KEY, user.email)
  }

  logout() {
    sessionStorage.removeItem(this.SESSION_KEY);
    this.http.get(<string>this.authUrl + 'logout').subscribe(() => {
      this.router.navigate(['/auth-form']);
    });
  }

  isUserAuthenticated() {
    let user = sessionStorage.getItem(this.SESSION_KEY)
    return user !== null;
  }
}
