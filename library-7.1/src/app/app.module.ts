import {NgModule} from '@angular/core';
import {BrowserModule} from '@angular/platform-browser';
import {CommonModule} from '@angular/common';
import {AppComponent} from './app.component';
import {BookListComponent} from './component/book-list/book-list.component';
import {BookFormComponent} from './component/book-form/book-form.component';
import {BookService} from "./service/book.service";
import {RouterModule} from "@angular/router";
import {FormsModule, ReactiveFormsModule} from "@angular/forms";
import {HTTP_INTERCEPTORS, HttpClientModule} from "@angular/common/http";
import {AppRoutingModule} from "./app.routing.module";
import {BookEditFormComponent} from "./component/book-edit-form/book-edit-form.component";
import {AuthorFormComponent} from "./component/author-form/author-form.component";
import {AuthorListComponent} from "./component/author-list/author-list.component";
import {AuthorEditFormComponent} from "./component/author-edit-form/author-edit-form.component";
import {GenreListComponent} from "./component/genre-list/genre-list.component";
import {GenreFormComponent} from "./component/genre-form/genre-form.component";
import {GenreEditFormComponent} from "./component/genre-edit-form/genre-edit-form.component";
import { AuthFormComponent } from './component/auth-form/auth-form.component';
import {HttpInterceptorService} from "./service/http-interceptor.service";
import { MainComponent } from './component/main/main.component';


@NgModule({
  declarations: [
    AppComponent,
    BookListComponent,
    BookFormComponent,
    BookEditFormComponent,
    AuthorListComponent,
    AuthorFormComponent,
    AuthorEditFormComponent,
    GenreListComponent,
    GenreFormComponent,
    GenreEditFormComponent,
    AuthFormComponent,
    MainComponent,
  ],
  imports: [
    BrowserModule,
    CommonModule,
    HttpClientModule,
    FormsModule,
    ReactiveFormsModule,
    AppRoutingModule,
    RouterModule
  ],
  // exports: [ RouterModule ],
  providers: [BookService, {
    provide: HTTP_INTERCEPTORS,
    useClass: HttpInterceptorService,
    multi: true
  } ],
  bootstrap: [
    AppComponent
  ]
})
export class AppModule { }
