import {Component} from '@angular/core';
import {GenreService} from "../../service/genre.service";
import {Genre} from "../../model/genre";

@Component({
  selector: 'app-genre-list',
  templateUrl: './genre-list.component.html',
  styleUrls: ['./genre-list.component.css']
})
export class GenreListComponent {

  genres: Genre[] = [];

  constructor(private genreService: GenreService) { }

  ngOnInit() {
    this.genreService.getAllGenres().subscribe((genres: Genre[]) => {
      this.genres = genres;
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
