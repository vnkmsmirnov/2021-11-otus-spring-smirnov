import {Component} from '@angular/core';
import {AuthorService} from "../../service/author.service";
import {Author} from "../../model/author";

@Component({
  selector: 'app-author-list',
  templateUrl: './author-list.component.html',
  styleUrls: ['./author-list.component.css']
})
export class AuthorListComponent {

  authors: Author[] = [];

  constructor(private authorService: AuthorService) { }

  ngOnInit() {
    this.authorService.getAllAuthors().subscribe((authors: Author[]) => {
      this.authors = authors;
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
