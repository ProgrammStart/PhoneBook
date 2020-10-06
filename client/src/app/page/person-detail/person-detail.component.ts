import {Component, OnInit} from '@angular/core';
import {ActivatedRoute} from "@angular/router";
import {Person} from "../../core/model/person";
import {PersonService} from "../../core/service/person.service";
import {Contact} from "../../core/model/contact";

@Component({
  selector: 'app-person-detail',
  templateUrl: './person-detail.component.html',
  styleUrls: ['./person-detail.component.scss']
})
export class PersonDetailComponent implements OnInit {

  person = {} as Person;

  constructor(private activatedRoute: ActivatedRoute,
              private personService: PersonService) {
  }

  ngOnInit() {
    this.activatedRoute.params.subscribe(params => {
      let id = params['id'];
      this.personService.findById(id).subscribe(result => {
        this.person = result;
      });
    });
  }

  update(personId: number) {
    this.personService.update(personId, this.person).subscribe(result => {
      this.person = result;
    });
  }

  delete(contactId: number) {
    this.person.contacts.forEach(el => {
      if (el.contactId == contactId || el.contactId == null) {
        this.person.contacts.splice(this.person.contacts.indexOf(el), 1);
      }
    })
  }

  addPhone() {
    let newContact = {} as Contact;
    this.person.contacts.push(newContact);
  }
}
