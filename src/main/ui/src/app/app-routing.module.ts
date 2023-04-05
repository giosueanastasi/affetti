import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { HomeComponent } from './guest/home/home.component';
import { AdminComponent } from './admin/admin/admin.component';
import { UsersComponent } from './anagrafiche/user/users.component';
import { PostiComponent } from './anagrafiche/posto/posti.component';
import { ContrattiComponent } from './anagrafiche/contratto/contratti.component';
import { AssegnatariComponent } from './anagrafiche/assegnatario/assegnatari.component';



const routes: Routes = [
  {path:'home', component: HomeComponent},
  {path:'admin', component: AdminComponent},
  {path:'user', component: UsersComponent},
  {path:'posto', component: PostiComponent},
  {path:'assegnatario', component: AssegnatariComponent},
  {path:'contratto', component: ContrattiComponent}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
