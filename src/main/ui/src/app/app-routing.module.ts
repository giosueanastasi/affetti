import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { HomeComponent } from './guest/home/home.component';
import { AdminComponent } from './admin/admin/admin.component';
import { UsersComponent } from './anagrafiche/user/users.component';


const routes: Routes = [
  {path:'home', component: HomeComponent},
  {path:'admin', component: AdminComponent},
  {path:'user', component: UsersComponent}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
