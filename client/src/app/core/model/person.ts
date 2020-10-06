import {Contact} from "./contact";

export interface Person {
  personId: number;
  name: string;
  contacts: Contact[];
}
