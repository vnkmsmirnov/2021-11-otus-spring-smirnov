import {Injectable} from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";
import {Author} from "../model/author";

@Injectable({
  providedIn: 'root'
})
export class AuthorService {

  private readonly authorUrl: string;

  constructor(private http: HttpClient) {
    this.authorUrl = '/api/author'
  }

  public getAuthorById(id: string): Observable<Author> {
    return this.http.get<Author>(<string>this.authorUrl + '/' + id);
  }

  public getAllAuthors(): Observable<Author[]> {
    return this.http.get<Author[]>(<string>this.authorUrl);
  }

  public saveAuthor(author: Author) {
    return this.http.post<Author>(<string>this.authorUrl, author);
  }

  public deleteByAuthorId(id: string): Observable<any> {
    return this.http.delete(<string>this.authorUrl + '/' + id);
  }
}
