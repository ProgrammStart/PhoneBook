import {Component, OnInit, ViewChild} from '@angular/core';
import {PersonService} from "../../core/service/person.service";
import {Person} from "../../core/model/person";
import {MatTableDataSource} from "@angular/material/table";
import {MatSort} from "@angular/material/sort";
import {MatPaginator} from "@angular/material/paginator";

@Component({
  selector: 'app-persons',
  templateUrl: './persons.component.html',
  styleUrls: ['./persons.component.scss']
})
export class PersonsComponent implements OnInit {

  persons: Person[];
  personsDisplayed: Person[];
  displayedColumns: string[] = ['id', 'name', 'options'];
  dataSource: MatTableDataSource<Person>;
  @ViewChild(MatPaginator) paginatorMat: MatPaginator;
  @ViewChild(MatSort) sort: MatSort;


  constructor(private personService: PersonService) {
  }

  ngOnInit() {
    this.personService.findAll().subscribe(result => {
      this.persons = result;
      this.personsDisplayed = result;
      this.dataSource = new MatTableDataSource(this.personsDisplayed);
      this.dataSource.paginator = this.paginatorMat;
      this.dataSource.sort = this.sort;
    });
  }

  delete(personId: number) {
    this.personService.delete(personId).subscribe(() => {
        this.personsDisplayed.forEach(el => {
          if (el.personId == personId) {
            this.personsDisplayed.splice(this.personsDisplayed.indexOf(el), 1);
            this.dataSource = new MatTableDataSource(this.personsDisplayed);
            this.dataSource.paginator = this.paginatorMat;
            this.dataSource.sort = this.sort;
          }
        })
      }
    )
  }

  applyFilter(event: Event) {
    const filterValue = (event.target as HTMLInputElement).value;
    this.dataSource.filter = filterValue.trim().toLowerCase();
    if (this.dataSource.paginator) {
      this.dataSource.paginator.firstPage()
    }
  }
}
