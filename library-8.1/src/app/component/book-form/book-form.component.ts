import {Component} from '@angular/core';
import {ActivatedRoute, Router} from "@angular/router";
import {BookService} from '../../service/book.service';
import {Book} from '../../model/book';
import {Genre} from "../../model/genre";
import {Author} from "../../model/author";
import {GenreService} from "../../service/genre.service";
import {AuthorService} from "../../service/author.service";

@Component({
  selector: 'app-book-form',
  templateUrl: './book-form.component.html',
  styleUrls: ['./book-form.component.css']
})

export class BookFormComponent {

  book: Book = new Book();
  genres: Genre[] = [];
  genre: Genre = new Genre();
  authors: Author[] = [];
  author: Author = new Author();

  constructor(private route: ActivatedRoute,
              private router: Router,
              private bookService: BookService,
              private genreService: GenreService,
              private authorService: AuthorService) {
  }

  ngOnInit() {
    this.route.params.subscribe(() => {
        this.genreService.getAllGenres().subscribe((genres: Genre[]) => {
          this.genres = genres;
        })
        this.authorService.getAllAuthors().subscribe((authors: Author[]) => {
          this.authors = authors;
        })
    });
  }

  onSubmit() {
    this.bookService.saveBook(this.book).subscribe(result => this.gotoBookList());
  }

  gotoBookList() {
    this.router.navigate(['/main/book-list']);
  }
}
