import {Component, OnInit} from '@angular/core';
import {ActivatedRoute, Router} from "@angular/router";
import {GenreService} from "../../service/genre.service";
import {Genre} from "../../model/genre";
import {AuthService} from "../../service/auth.service";

@Component({
  selector: 'app-genre-edit-form',
  templateUrl: './genre-edit-form.component.html',
  styleUrls: ['./genre-edit-form.component.css']
})
export class GenreEditFormComponent implements OnInit {

  genres: Genre[] = [];
  genre: Genre = new Genre();

  constructor(private route: ActivatedRoute,
              private router: Router,
              private genreService: GenreService) { }

  ngOnInit() {
    this.route.params.subscribe(({id}:{id?: number}) => {
      if (id === undefined) {
        return;
      }
      this.genreService.getGenreById(id).subscribe((genre: Genre) => {
        this.genre = genre;
      })
    });
  }

  onSubmit() {
    this.genreService.saveGenre(this.genre).subscribe(result => this.gotoAuthorList());
  }

  gotoAuthorList() {
    this.router.navigate(['/main/genre-list']);
  }
}
