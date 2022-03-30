import { Component, OnInit } from '@angular/core';
import {ActivatedRoute, Router} from "@angular/router";
import {AuthorService} from "../../service/author.service";
import {Author} from "../../model/author";

@Component({
  selector: 'app-author-form',
  templateUrl: './author-form.component.html',
  styleUrls: ['./author-form.component.css']
})
export class AuthorFormComponent {

  author: Author = new Author();

  constructor(private route: ActivatedRoute,
              private router: Router,
              private authorService: AuthorService) { }

  onSubmit() {
    this.authorService.saveAuthor(this.author).subscribe(result => this.gotoAuthorList());
  }

  gotoAuthorList() {
    this.router.navigate(['/main/author-list']);
  }
}
