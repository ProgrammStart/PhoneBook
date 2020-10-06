import {NgModule} from '@angular/core';
import {RouterModule, Routes} from '@angular/router';
import {PersonDetailComponent} from "./page/person-detail/person-detail.component";
import {PersonsComponent} from "./page/persons/persons.component";
import {PersonAddComponent} from "./page/person-add/person-add.component";

const routes: Routes = [
  {path: 'person-add', component: PersonAddComponent},
  {path: 'person-detail', component: PersonDetailComponent},
  {path: 'person-detail/:id', component: PersonDetailComponent},
  {path: '', component: PersonsComponent, pathMatch: 'full'},
  {path: "**", component: PersonsComponent}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule {
}
