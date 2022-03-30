import {Injectable} from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";
import {Genre} from "../model/genre";

@Injectable({
  providedIn: 'root'
})
export class GenreService {

  private readonly genreUrl: string;

  constructor(private http: HttpClient) {
    this.genreUrl = '/api/genre'
  }

  public getGenreById(id: number): Observable<Genre> {
    return this.http.get<Genre>(<string>this.genreUrl + '/' + id);
  }

  public getAllGenres(): Observable<Genre[]> {
    return this.http.get<Genre[]>(<string>this.genreUrl);
  }

  public saveGenre(genre: Genre) {
    return this.http.post<Genre>(<string>this.genreUrl, genre);
  }

  public deleteByGenreId(id: number): Observable<any> {
    return this.http.delete(<string>this.genreUrl + '/' + id);
  }
}
