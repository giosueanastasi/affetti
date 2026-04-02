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
import { ProfileComponent } from './anagrafiche/profile/profile.component';


const routes: Routes = [
  {path:'home', component: HomeComponent},
  {path:'admin', component: AdminComponent, canActivate: [authGuard, roleGuard([Roles.SUPERADMIN, Roles.ADMIN])]},
  {path:'user', component: UsersComponent, canActivate: [authGuard, roleGuard([Roles.SUPERADMIN, Roles.ADMIN, Roles.OPERATOR])]},
  {path:'comune', component: ComuniComponent, canActivate: [authGuard, roleGuard([Roles.SUPERADMIN, Roles.ADMIN, Roles.OPERATOR])]},
  {path:'contraente', component: ContraentiComponent, canActivate: [authGuard, roleGuard([Roles.SUPERADMIN, Roles.ADMIN, Roles.OPERATOR])]},
  {path:'posto', component: PostiComponent, canActivate: [authGuard, roleGuard([Roles.SUPERADMIN, Roles.ADMIN, Roles.OPERATOR])]},
  {path:'assegnatario', component: AssegnatariComponent, canActivate: [authGuard, roleGuard([Roles.SUPERADMIN, Roles.ADMIN, Roles.OPERATOR])]},
  {path:'contratto', component: ContrattiComponent, canActivate: [authGuard, roleGuard([Roles.SUPERADMIN, Roles.ADMIN, Roles.OPERATOR, Roles.USER])]},
  {path:'domanda', component: DomandeComponent, canActivate: [authGuard, roleGuard([Roles.SUPERADMIN, Roles.ADMIN])]},
  {path:'domandaFull', component: DomandaFullComponent, canActivate: [authGuard, roleGuard([Roles.SUPERADMIN, Roles.ADMIN])]},
  {path:'profile', component: ProfileComponent, canActivate: [authGuard]},
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