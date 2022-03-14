import {NgModule} from '@angular/core';
import {RouterModule, Routes} from '@angular/router';
import {BookListComponent} from './component/book-list/book-list.component';
import {BookFormComponent} from './component/book-form/book-form.component';
import {BookEditFormComponent} from "./component/book-edit-form/book-edit-form.component";
import {AuthorListComponent} from "./component/author-list/author-list.component";
import {AuthorEditFormComponent} from "./component/author-edit-form/author-edit-form.component";
import {AuthorFormComponent} from "./component/author-form/author-form.component";
import {GenreListComponent} from "./component/genre-list/genre-list.component";
import {GenreFormComponent} from "./component/genre-form/genre-form.component";
import {GenreEditFormComponent} from "./component/genre-edit-form/genre-edit-form.component";
import {AuthFormComponent} from "./component/auth-form/auth-form.component";
import {MainComponent} from "./component/main/main.component";

const routes: Routes = [
  { path: '', redirectTo: 'main/book-list', pathMatch: 'full'},
  { path: 'auth-form', component: AuthFormComponent },
  { path: 'main', component: MainComponent, children: [
      { path: 'book-list', component: BookListComponent },
      { path: 'book-add', component: BookFormComponent },
      { path: 'book-edit-form/:id', component: BookEditFormComponent },
      { path: 'author-list', component: AuthorListComponent },
      { path: 'author-add', component: AuthorFormComponent },
      { path: 'author-edit-form/:id', component: AuthorEditFormComponent },
      { path: 'genre-list', component: GenreListComponent },
      { path: 'genre-add', component: GenreFormComponent },
      { path: 'genre-edit-form/:id', component: GenreEditFormComponent }
      ]
  },
  { path: '**', redirectTo: 'main' }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)]
})
export class AppRoutingModule { }
