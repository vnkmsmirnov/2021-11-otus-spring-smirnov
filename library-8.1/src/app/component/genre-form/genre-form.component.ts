import {Component} from '@angular/core';
import {ActivatedRoute, Router} from "@angular/router";
import {GenreService} from "../../service/genre.service";
import {Genre} from "../../model/genre";

@Component({
  selector: 'app-genre-form',
  templateUrl: './genre-form.component.html',
  styleUrls: ['./genre-form.component.css']
})
export class GenreFormComponent {

  genre: Genre = new Genre();

  constructor(private route: ActivatedRoute,
              private router: Router,
              private genreService: GenreService) { }

  onSubmit() {
    this.genreService.saveGenre(this.genre).subscribe(result => this.gotoAuthorList());
  }

  gotoAuthorList() {
    this.router.navigate(['/main/genre-list']);
  }
}
