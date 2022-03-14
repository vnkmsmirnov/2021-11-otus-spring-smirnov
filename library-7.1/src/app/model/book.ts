import { Genre } from "./genre";
import { Author } from "./author";
import { Comment } from "./comment";

export class Book {
  id: number = 0;
  title: string = "";
  pages: string = "";
  genre: Genre = new Genre();
  author: Author = new Author();
  comments: Array<Comment> = [];
}
