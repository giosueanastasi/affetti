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
import { DomandaFullComponent } from './anagrafiche/domanda-full/domanda-full.component';
import { LoginComponent } from './guest/login/login.component';
import { RegisterComponent } from './guest/register/register.component';
import { CercadefuntiComponent } from './anagrafiche/cercadefunti/cercadefunti.component';
import { DettaglioDefuntoComponent } from './anagrafiche/dettaglio-defunto/dettaglio-defunto.component';


const routes: Routes = [
  {path:'home', component: HomeComponent},
  {path:'admin', component: AdminComponent},
  {path:'user', component: UsersComponent},
  {path:'comune', component: ComuniComponent},
  {path:'contraente', component: ContraentiComponent},
  {path:'posto', component: PostiComponent},
  {path:'assegnatario', component: AssegnatariComponent},
  {path:'contratto', component: ContrattiComponent},
  {path:'domanda', component: DomandeComponent},
  {path:'domandaFull', component: DomandaFullComponent},
  {path:'login', component: LoginComponent},
  {path:'register', component: RegisterComponent}
  {path:'cercadefunti', component: CercadefuntiComponent},
  {path:'defunti/:id', component: DettaglioDefuntoComponent},
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }