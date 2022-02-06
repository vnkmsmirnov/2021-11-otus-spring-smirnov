import {Component} from '@angular/core';
import {Book} from '../../model/book';
import {BookService} from '../../service/book.service';

@Component({
  selector: 'app-book-list',
  templateUrl: './book-list.component.html',
  styleUrls: ['./book-list.component.css']
})
export class BookListComponent {

  books: Book[] = [];

  constructor(private bookService: BookService) { }

  ngOnInit() {
    this.bookService.getAllBooks().subscribe((books: Book[]) => {
      this.books = books;
    })
  }

  onDelete(id: string) {
    this.bookService.deleteByBookId(id).subscribe(() => {
      this.bookService.getAllBooks().subscribe((books: Book[]) => {
        this.books = books;
      })
    })
  }
}
