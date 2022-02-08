import {Component} from '@angular/core';
import {ActivatedRoute, Router} from "@angular/router";
import {BookService} from '../../service/book.service';
import {Book} from '../../model/book';
import {Genre} from "../../model/genre";
import {GenreService} from "../../service/genre.service";
import {AuthorService} from "../../service/author.service";
import {Author} from "../../model/author";

@Component({
  selector: 'app-book-edit-form',
  templateUrl: './book-edit-form.component.html',
  styleUrls: ['./book-edit-form.component.css']
})

export class BookEditFormComponent {

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
    this.route.params.subscribe(({id}:{id?: string}) => {
      if (id === undefined) {
        return;
      }
      this.bookService.getBookById(id).subscribe((book: Book) => {
        this.genreService.getAllGenres().subscribe((genres: Genre[]) => {
          this.genres = genres;
        })
        this.authorService.getAllAuthors().subscribe((authors: Author[]) => {
          this.authors = authors;
        })
        this.book = book;
      })
    });
  }

  onSubmit() {
    this.bookService.saveBook(this.book).subscribe(result => this.gotoBookList());
  }

  gotoBookList() {
    this.router.navigate(['/book-list']);
  }
}
