import {Role} from "./role";

export class User {
  id: number = 0;
  name: string = "";
  email: string = "";
  password: string = "";
  readonly roles: Array<Role> = [];
}
