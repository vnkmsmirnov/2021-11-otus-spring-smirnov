import {Component} from '@angular/core';
import {Book} from '../../model/book';
import {BookService} from '../../service/book.service';
import {AuthService} from "../../service/auth.service";

@Component({
  selector: 'app-book-list',
  templateUrl: './book-list.component.html',
  styleUrls: ['./book-list.component.css']
})
export class BookListComponent {

  books: Book[] = [];
  isAdmin = false;

  constructor(private bookService: BookService,
              private authService: AuthService) { }

  ngOnInit() {
    this.bookService.getAllBooks().subscribe((books: Book[]) => {
      this.books = books;
      this.isAdmin = this.authService.isAdmin();
    })
  }

  onDelete(id: number) {
    this.bookService.deleteByBookId(id).subscribe(() => {
      this.bookService.getAllBooks().subscribe((books: Book[]) => {
        this.books = books;
      })
    })
  }
}
