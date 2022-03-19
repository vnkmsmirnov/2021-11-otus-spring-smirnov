import { Component, OnInit } from '@angular/core';
import { Router, ActivatedRoute } from '@angular/router';
import {AuthService} from "../../service/auth.service";
import {User} from "../../model/user";


@Component({
  selector: 'app-auth-form',
  templateUrl: './auth-form.component.html',
  styleUrls: ['./auth-form.component.css']
})
export class AuthFormComponent implements OnInit {

  user: User = new User();

  error: string = "";

  constructor(private route: ActivatedRoute, private router: Router, private authService: AuthService) {}

  ngOnInit() {
  }

  doLogin() {
    if(this.user.email !== '' && this.user.email !== null && this.user.password !== '' && this.user.password !== null) {
      this.authService.authenticate(this.user);
    } else {
      this.error = 'Invalid Credentials';
    }
  }
}
