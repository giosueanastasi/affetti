import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { HomeComponent } from './guest/home/home.component';
import { AdminComponent } from './admin/admin/admin.component';
import { UsersComponent } from './anagrafiche/user/users.component';
import { PostiComponent } from './anagrafiche/posto/posti.component';
import { ComuniComponent } from './anagrafiche/comune/comuni.component';
import { ContraentiComponent } from './anagrafiche/contraente/contraenti.component';
import { ContrattiComponent } from './anagrafiche/contratto/contratti.component';
import { AssegnatariComponent } from './anagrafiche/assegnatario/assegnatari.component';
import { DomandeComponent } from './anagrafiche/domanda/domande.component';


const routes: Routes = [
  {path:'home', component: HomeComponent},
  {path:'admin', component: AdminComponent},
  {path:'user', component: UsersComponent},
  {path:'comune', component: ComuniComponent},
  {path:'contraente', component: ContraentiComponent},
  {path:'posto', component: PostiComponent},
  {path:'assegnatario', component: AssegnatariComponent},
  {path:'contratto', component: ContrattiComponent},
  {path:'domanda', component: DomandeComponent}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
