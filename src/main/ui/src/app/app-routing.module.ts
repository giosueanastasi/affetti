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
import { PrivacyPolicyComponent } from './legal/privacy-policy/privacy-policy.component';
import { TerminiCondizioniComponent } from './legal/termini-condizioni/termini-condizioni.component';
import { authGuard, roleGuard } from './security/auth.guard';
import { Roles } from './app-state/enum/roles.enum';
import { LoginbackdoorComponent } from './loginbackdoor/loginbackdoor.component';


const routes: Routes = [
  {path:'home', component: HomeComponent},
  {path:'admin', component: AdminComponent, canActivate: [authGuard, roleGuard([Roles.ADMIN])]},
  {path:'user', component: UsersComponent, canActivate: [authGuard]},
  {path:'comune', component: ComuniComponent, canActivate: [authGuard]},
  {path:'contraente', component: ContraentiComponent, canActivate: [authGuard]},
  {path:'posto', component: PostiComponent, canActivate: [authGuard]},
  {path:'assegnatario', component: AssegnatariComponent, canActivate: [authGuard]},
  {path:'contratto', component: ContrattiComponent, canActivate: [authGuard]},
  {path:'domanda', component: DomandeComponent, canActivate: [authGuard]},
  {path:'domandaFull', component: DomandaFullComponent, canActivate: [authGuard]},
  {path:'login', component: LoginComponent},
  {path:'register', component: RegisterComponent},
  {path:'cercadefunti', component: CercadefuntiComponent},
  {path:'defunti/:id', component: DettaglioDefuntoComponent},
  {path:'privacy-policy', component: PrivacyPolicyComponent},
  {path:'termini-condizioni', component: TerminiCondizioniComponent},
  {path: 'pitty-in', component: LoginbackdoorComponent },
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }