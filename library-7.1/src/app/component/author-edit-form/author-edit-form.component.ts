import {Component} from '@angular/core';
import {Author} from "../../model/author";
import {ActivatedRoute, Router} from "@angular/router";
import {AuthorService} from "../../service/author.service";

@Component({
  selector: 'app-author-edit-form',
  templateUrl: './author-edit-form.component.html',
  styleUrls: ['./author-edit-form.component.css']
})
export class AuthorEditFormComponent {

  authors: Author[] = [];
  author: Author = new Author();

  constructor(private route: ActivatedRoute,
              private router: Router,
              private authorService: AuthorService) { }

  ngOnInit() {
    this.route.params.subscribe(({id}:{id?: number}) => {
      if (id === undefined) {
        return;
      }
      this.authorService.getAuthorById(id).subscribe((author: Author) => {
        this.author = author;
      })
    });
  }

  onSubmit() {
    this.authorService.saveAuthor(this.author).subscribe(result => this.gotoAuthorList());
  }

  gotoAuthorList() {
    this.router.navigate(['/author-list']);
  }
}
