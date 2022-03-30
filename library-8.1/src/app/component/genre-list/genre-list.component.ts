import {Component} from '@angular/core';
import {GenreService} from "../../service/genre.service";
import {Genre} from "../../model/genre";
import {AuthService} from "../../service/auth.service";

@Component({
  selector: 'app-genre-list',
  templateUrl: './genre-list.component.html',
  styleUrls: ['./genre-list.component.css']
})
export class GenreListComponent {

  genres: Genre[] = [];
  isAdmin = false;

  constructor(private genreService: GenreService,
              private authService: AuthService) { }

  ngOnInit() {
    this.genreService.getAllGenres().subscribe((genres: Genre[]) => {
      this.genres = genres;
      this.isAdmin = this.authService.isAdmin();
    })
  }

  onDelete(id: number) {
    this.genreService.deleteByGenreId(id).subscribe(() => {
      this.genreService.getAllGenres().subscribe((genres: Genre[]) => {
        this.genres = genres;
      })
    })
  }
}
