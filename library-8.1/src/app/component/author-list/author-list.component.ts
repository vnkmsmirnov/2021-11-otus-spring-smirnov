import {Component} from '@angular/core';
import {AuthorService} from "../../service/author.service";
import {Author} from "../../model/author";
import {AuthService} from "../../service/auth.service";

@Component({
  selector: 'app-author-list',
  templateUrl: './author-list.component.html',
  styleUrls: ['./author-list.component.css']
})
export class AuthorListComponent {

  authors: Author[] = [];
  isAdmin = false;

  constructor(private authorService: AuthorService,
              private authService: AuthService) { }

  ngOnInit() {
    this.authorService.getAllAuthors().subscribe((authors: Author[]) => {
      this.authors = authors;
      this.isAdmin = this.authService.isAdmin();
    })
  }

  onDelete(id: number) {
    this.authorService.deleteByAuthorId(id).subscribe(() => {
      this.authorService.getAllAuthors().subscribe((authors: Author[]) => {
        this.authors = authors;
      })
    })
  }
}
