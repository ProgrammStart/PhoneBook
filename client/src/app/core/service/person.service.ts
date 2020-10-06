import {Injectable} from '@angular/core';
import {HttpClient, HttpHeaders} from "@angular/common/http";
import {Observable} from "rxjs";
import {Person} from "../model/person";

@Injectable({
  providedIn: 'root'
})
export class PersonService {

  headers = new HttpHeaders().set('Content-Type', 'application/json');

  constructor(private httpClient: HttpClient) {
  }

  findAll(): Observable<Person[]> {
    return this.httpClient.get('/api/persons/all',
      {headers: this.headers}) as Observable<Person[]>;
  }

  delete(personId: number) {
    return this.httpClient.delete('/api/persons/' + personId,
      {headers: this.headers});
  }

  findById(personId: number): Observable<Person> {
    return this.httpClient.get('/api/persons/' + personId,
      {headers: this.headers}) as Observable<Person>;
  }

  put(person: Person): Observable<Person> {
    return this.httpClient.post('/api/persons/',
      person, {headers: this.headers}) as Observable<Person>
  }

  update(personId: number, person: Person): Observable<Person> {
    return this.httpClient.put('/api/persons/' + personId,
      person, {headers: this.headers}) as Observable<Person>
  }
}
