import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Book } from '../model/book'
import { Observable } from "rxjs";

@Injectable()
export class BookService {

  private readonly bookUrl: string;

  constructor(private http: HttpClient) {
    this.bookUrl = '/api/book'
  }

  public getBookById(id: string): Observable<Book> {
    return this.http.get<Book>(<string>this.bookUrl + '/' + id);
  }

  public getAllBooks(): Observable<Book[]> {
    return this.http.get<Book[]>(<string>this.bookUrl);
  }

  public saveBook(book: Book) {
    return this.http.post<Book>(<string>this.bookUrl, book);
  }

  public deleteByBookId(id: string): Observable<any> {
    return this.http.delete(<string>this.bookUrl + '/' + id);
  }
}
