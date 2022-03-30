import { Component, OnInit } from '@angular/core';
import {AuthService} from "../../service/auth.service";
import {Router} from "@angular/router";

@Component({
  selector: 'app-main',
  templateUrl: './main.component.html',
  styleUrls: ['./main.component.css']
})
export class MainComponent implements OnInit {

  isAuthenticated = false;
  isAdmin = false;

  constructor(private authService: AuthService,
              private router: Router) {
  }

  ngOnInit() {
    this.isAuthenticated = this.authService.isUserAuthenticated();
    this.isAdmin = this.authService.isAdmin();
    if (!this.isAuthenticated) {
      this.router.navigate(['/auth-form']);
    }
  }

  doLogout() {
    console.log("LOGOUT")
    this.authService.logout();
  }

}
