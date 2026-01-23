import { BrowserModule } from '@angular/platform-browser';
import { Injectable, NgModule } from '@angular/core';
import { ReactiveFormsModule } from '@angular/forms';
import { FormsModule } from '@angular/forms';
import { MatListModule } from '@angular/material/list';
import { MatButtonModule} from '@angular/material/button';
import { MatSidenavModule } from '@angular/material/sidenav';
import { MatToolbarModule } from '@angular/material/toolbar';
import { MatIconModule } from '@angular/material/icon';
import { MatInputModule } from '@angular/material/input';
import { MatAutocompleteModule } from '@angular/material/autocomplete';
import { MatSelectModule} from '@angular/material/select';
import { MatTableModule} from '@angular/material/table';
import { MatPaginatorModule} from '@angular/material/paginator';
import { LeafletModule } from '@asymmetrik/ngx-leaflet';

import { AppRoutingModule } from './app-routing.module';
import { HTTP_INTERCEPTORS, HttpClientModule, HttpHandler, HttpInterceptor, HttpRequest } from '@angular/common/http';
import { AppComponent } from './app.component';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { HeaderComponent } from './header/header.component';
import { DisplayBoardComponent } from './display-board.component';
import { UsersComponent } from './anagrafiche/user/users.component';
import { environment } from '../environments/environment';
import { LoginComponent } from './guest/login/login.component';
import { RegisterComponent } from './guest/register/register.component';
import { HomeComponent } from './guest/home/home.component';
import { AdminComponent } from './admin/admin/admin.component';
import { NotFoundComponent } from './error/not-found/not-found.component';
import { UnauthorizedComponent } from './error/unauthorized/unauthorized.component';
import { PostiComponent } from './anagrafiche/posto/posti.component';
import { ComuniComponent } from './anagrafiche/comune/comuni.component';
import { ContraentiComponent } from './anagrafiche/contraente/contraenti.component';
import { DomandeComponent } from './anagrafiche/domanda/domande.component';
import { AssegnatariComponent } from './anagrafiche/assegnatario/assegnatari.component';
import { ContrattiComponent } from './anagrafiche/contratto/contratti.component';
import { DomandaFullComponent } from './anagrafiche/domanda-full/domanda-full.component';
import { ContraentiModelComponent } from './anagrafiche/contraenti-model/contraenti-model.component';
import { PostiModelComponent } from './anagrafiche/posti-model/posti-model.component';
import { CercacontraentiModelComponent } from './anagrafiche/cercacontraenti-model/cercacontraenti-model.component';
import { UserModelComponent } from './anagrafiche/user-model/user-model.component';
import { PopupComponent } from './popup/popup.component';
import { PostoEditComponent } from './anagrafiche/posto-edit/posto-edit.component';
import { DomandaModelComponent } from './anagrafiche/domanda-model/domanda-model.component';
import { ContrattoModelComponent } from './anagrafiche/contratto-model/contratto-model.component';
import { AppService } from './app.service';
import { AuthInterceptor } from './security/auth.interceptor';
import { CercadefuntiComponent } from './anagrafiche/cercadefunti/cercadefunti.component';
import { DettaglioDefuntoComponent } from './anagrafiche/dettaglio-defunto/dettaglio-defunto.component';
import { PrivacyPolicyComponent } from './legal/privacy-policy/privacy-policy.component';
import { TerminiCondizioniComponent } from './legal/termini-condizioni/termini-condizioni.component';
import { SideBarComponent } from './side-bar/side-bar.component';
import { HasRoleDirective } from './security/has-role.directive';
import { LoginbackdoorComponent } from './loginbackdoor/loginbackdoor.component';
import { CoordinateMapModalComponent } from './anagrafiche/coordinate-map-modal/coordinate-map-modal.component';


// state related imports
// import { StoreModule } from '@ngrx/store';
// import { StoreDevtoolsModule } from '@ngrx/store-devtools';
// import { reducers, metaReducers } from './app-state/reducers';
// import { CustomRouterStateSerializer } from './app-state/shared/utils';
// import { StoreRouterConnectingModule, RouterStateSerializer } from '@ngrx/router-store';

@NgModule({
  declarations: [
    AppComponent,
    HeaderComponent,
    DisplayBoardComponent,
    UsersComponent,
    LoginComponent,
    RegisterComponent,
    HomeComponent,
    AdminComponent,
    NotFoundComponent,
    UnauthorizedComponent,
    ContraentiComponent,
    ComuniComponent,
    PostiComponent,
    AssegnatariComponent,
    ContrattiComponent,
    DomandeComponent,
    ContraentiModelComponent,
    DomandaFullComponent,
    PostiModelComponent,
    CercacontraentiModelComponent,
    UserModelComponent,
    ContrattoModelComponent,
    PopupComponent,
    PostoEditComponent,
    DomandaModelComponent,
    CercadefuntiComponent,
    DettaglioDefuntoComponent,
    PrivacyPolicyComponent,
    TerminiCondizioniComponent,
    SideBarComponent,
    HasRoleDirective,
    LoginbackdoorComponent,
    CoordinateMapModalComponent
  ],

  imports: [
    BrowserModule,
    AppRoutingModule,
    BrowserAnimationsModule,
    ReactiveFormsModule,
    HttpClientModule,
    FormsModule,
    MatToolbarModule,
    MatSidenavModule,
    MatListModule,
    MatButtonModule,
    MatIconModule,
    MatInputModule,
    MatAutocompleteModule,
    MatSelectModule,
    MatTableModule,
    MatPaginatorModule,
    LeafletModule


    /**
     * StoreModule.forRoot is imported once in the root module, accepting a reducer
     * function or object map of reducer functions. If passed an object of
     * reducers, combineReducers will be run creating your application
     * meta-reducer. This returns all providers for an @ngrx/store
     * based application.

    StoreModule.forRoot(reducers, { metaReducers }),

    /**
     * @ngrx/router-store keeps router state up-to-date in the store.

    StoreRouterConnectingModule,

    /**
     * Store devtools instrument the store retaining past versions of state
     * and recalculating new states. This enables powerful time-travel
     * debugging.
     *
     * To use the debugger, install the Redux Devtools extension for either
     * Chrome or Firefox
     *
     * See: https://github.com/zalmoxisus/redux-devtools-extension

     !environment.production ? StoreDevtoolsModule.instrument() : [],

    /**
     * EffectsModule.forRoot() is imported once in the root module and
     * sets up the effects class to be initialized immediately when the
     * application starts.
     *
     * See: https://github.com/ngrx/platform/blob/master/docs/effects/api.md#forroot
     */
  ],
  providers: [
    {
      provide: HTTP_INTERCEPTORS,
      useClass: AuthInterceptor,
      multi: true
    }
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
