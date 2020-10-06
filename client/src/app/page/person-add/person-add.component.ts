import {Component, OnInit} from '@angular/core';
import {FormBuilder, FormGroup, Validators} from "@angular/forms";
import {PersonService} from "../../core/service/person.service";
import {Person} from "../../core/model/person";
import {Router} from "@angular/router";
import {Contact} from "../../core/model/contact";

@Component({
  selector: 'app-person-add',
  templateUrl: './person-add.component.html',
  styleUrls: ['./person-add.component.scss']
})
export class PersonAddComponent implements OnInit {

  firstFormGroup: FormGroup;
  secondFormGroup: FormGroup;
  person = {} as Person;
  contact = {} as Contact;

  constructor(private formBuilder: FormBuilder,
              private personService: PersonService,
              private router: Router) {
  }

  ngOnInit() {
    this.firstFormGroup = this.formBuilder.group({
      nameInput: ['', Validators.required]
    });
    this.secondFormGroup = this.formBuilder.group({
      telephoneInput: ['', Validators.required]
    });
  }

  put() {
    this.person.contacts = [];
    this.person.contacts.push(this.contact);
    this.personService.put(this.person).subscribe(() => {
      this.router.navigate(['']);
    });
  }
}
