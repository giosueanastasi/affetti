# Affetti - Software gestionale cari scomparsi

## Stack
- Backend: Java 17, Spring Boot 2.7.18, Maven
- Frontend: Angular 16.2, TypeScript 5.1
- DB: H2 in-memory (modalita' PostgreSQL) per sviluppo locale
- Security: Spring Security + OAuth2 Resource Server + JWT (jjwt 0.11.5)
- ORM: Hibernate 5.6 / Spring Data JPA + QueryDSL
- Mapping: MapStruct 1.6.3 + Lombok 1.18.30
- PDF: FreeMarker + Flying Saucer + iText 5
- UI libs: Angular Material 15, Bootstrap 5, ngx-bootstrap, Leaflet (mappe)
- State management: NgRx 16 (presente come dipendenza, attualmente commentato nel modulo)

## Struttura
- `/` (root) -> progetto Maven, backend Spring Boot
- `/src/main/java/it/pittysoft/affetti/` -> sorgenti Java
  - `config/` -> configurazioni Spring
  - `controller/` -> REST controller
  - `dao/` -> Data Access Objects
  - `dto/` -> Data Transfer Objects
  - `entity/` -> entita' JPA
  - `mapper/` -> mapper MapStruct
  - `model/` -> modelli di dominio
  - `repository/` -> repository Spring Data
  - `security/` -> configurazione sicurezza, JWT, filtri
  - `service/` -> logica di business
  - `utils/` -> utilita'
  - `links/` -> HATEOAS link builders
- `/src/main/resources/` -> configurazioni e risorse
  - `application.properties` -> configurazione Spring Boot
  - `data.sql` -> dati iniziali H2
  - `static/` -> build Angular servita dal backend
- `/src/main/ui/` -> progetto Angular (frontend)
  - `src/app/anagrafiche/` -> componenti CRUD principali (contraenti, domande, posti, defunti, contratti, ecc.)
  - `src/app/guest/` -> login, registrazione, home pubblica
  - `src/app/admin/` -> pannello amministrazione
  - `src/app/security/` -> interceptor HTTP, direttive ruoli
  - `src/app/header/`, `src/app/side-bar/` -> layout
  - `src/app/shared/` -> componenti riusabili (cerca-defunti-widget, tenant-card, defunto-card)
  - `src/app/service/` -> servizi specifici (tenant.service)
  - `src/app/legal/` -> privacy policy, termini e condizioni

## Comandi

### Backend
- `mvn clean install` dalla root per buildare il backend
- `mvn spring-boot:run` dalla root per avviare (porta 8080)
- Il backend serve anche il frontend compilato da `src/main/resources/static/`

### Frontend
- `cd src/main/ui`
- `npm install` per installare le dipendenze
- `npm run start` -> `ng serve` (dev server con proxy verso backend su localhost:8080)
- `npm run start-debug` -> `ng serve --configuration=development` (sourcemap attive, no ottimizzazione)
- `npm run build` -> `ng build` (build di produzione, output in `dist/angular-nodejs-example`)
- `npm run test` -> `ng test` (unit test con Karma/Jasmine)
- `npm run lint` -> `ng lint` (linting con TSLint)
- `npm run e2e` -> `ng e2e` (end-to-end con Protractor)

### Proxy FE -> BE
Il dev server Angular (`ng serve`) proxya le chiamate `/api` verso `http://localhost:8080` (configurato in `proxy.conf.json`).

## Convenzioni
- Pattern controller/service/repository nel backend
- I componenti Angular usano NgModule (non standalone), tutti dichiarati in `AppModule`
- `FormsModule` e `ReactiveFormsModule` entrambi usati nei template
- Autenticazione tramite `AuthInterceptor` che inietta il token JWT nelle richieste HTTP
- Direttiva `HasRoleDirective` per controllo accessi basato su ruoli nei template
- Le entita' JPA usano Lombok per getter/setter e MapStruct per la conversione entity <-> DTO

## Security

### Architettura
- Autenticazione stateless con JWT (HS256), token valido 1 ora, no refresh token
- Spring Security + OAuth2 Resource Server per validazione JWT lato backend
- Secret key JWT centralizzata in `application.properties` (`jwt.secret`), iniettata via `@Value`
- Password hashate con BCrypt (12 round)
- Frontend: token salvato in localStorage, iniettato via `AuthInterceptor`
- Stato auth gestito da `AuthStateService` (BehaviorSubject con decode JWT locale)

### Ruoli
- 4 ruoli definiti: `superadmin`, `admin`, `operator`, `user` (tabella `role`, id 1/2/3/4)
- Gerarchia: SUPERADMIN >= ADMIN > OPERATOR > USER
- SUPERADMIN ha tutti i permessi di ADMIN + gestione multi-tenant (vede tutti i tenant)
- Enum frontend: `Roles.SUPERADMIN`, `Roles.ADMIN`, `Roles.OPERATOR`, `Roles.USER`
- Nel JWT i ruoli sono nel claim "roles" come `["ROLE_ADMIN"]` ecc.
- `JwtAuthenticationConverter` aggiunge prefisso `ROLE_` automaticamente

### Protezione endpoint backend (SecurityConfiguration + ApiEndpoints)
- **PUBLIC** (permitAll): `/api/login`, `/h2-console/**`, `/api/search_defunti`, `/api/defunto/{id}`
- **USER** (ADMIN/OPERATOR/USER): ricerca CAP, contratti (list/search/stampa), domande (list/search/stampa), comuni (list/get)
- **OPERATOR** (SUPERADMIN/ADMIN/OPERATOR): users, assegnatari, contraenti (list/search), posti (list/search), tenant endpoints (list/get/cimiteri/search_defunti/defunti), + tutto USER
- **ADMIN** (SUPERADMIN/ADMIN): tutto il resto sotto `/api/**` (include tutte le POST di creazione/modifica)
- POST `/api/tenant` (creazione tenant): cade nel fallback ADMIN ma ha guard esplicito SUPERADMIN nel controller
- Nessun `@PreAuthorize` sui metodi del controller, tutta la security e' URL-based nel SecurityFilterChain (eccezione: createTenant ha check SUPERADMIN programmatico)

### Protezione rotte frontend (app-routing.module.ts)
- **Pubbliche**: /home, /login, /register, /cercadefunti, /defunti/:id, /privacy-policy, /termini-condizioni, /pitty-in
- **authGuard** (qualsiasi utente autenticato): /profile
- **roleGuard([SUPERADMIN,ADMIN,OPERATOR])**: /user, /comune, /contraente, /posto, /assegnatario, /tenant/:id
- **roleGuard([SUPERADMIN,ADMIN,OPERATOR,USER])**: /contratto
- **roleGuard([SUPERADMIN,ADMIN])**: /domanda, /domandaFull, /admin

### Sidebar (visibilita' menu per ruolo via *appHasRole)
- Nuova Domanda, Domande -> SUPERADMIN, ADMIN
- Contratti -> SUPERADMIN, ADMIN, OPERATOR, USER
- Posti, Contraenti -> SUPERADMIN, ADMIN, OPERATOR
- Cerca Defunti -> pubblico

### Utenti di test (data.sql)
- superadmin/superadmin (SUPERADMIN, nessun tenant - vede tutti)
- admin_roma/admin_roma (ADMIN, tenant 1 - Comune di Roma)
- admin_milano/admin_milano (ADMIN, tenant 2 - Comune di Milano)
- admin_napoli/admin_napoli (ADMIN, tenant 3 - Comune di Napoli)
- operator/operator (OPERATOR, tenant 1 - Comune di Roma)
- user/user (USER, nessun tenant)
- Antonio90, Stefano24, Giovanna98 (tutti USER)
- Backdoor di sviluppo su `/pitty-in` con login rapido

### File security principali
- BE: `security/SecurityConfiguration.java`, `security/DatabaseUserDetailsService.java`, `security/DatabaseUserDetails.java`
- BE: `security/AuthRequest.java`, `security/AuthResponse.java`
- BE: `model/ApiEndpoints.java`, `links/*.java` (costanti path)
- BE: `controller/ControllerPrincipale.java` (endpoint `/api/login`)
- BE: `entity/Users.java`, `entity/Role.java`, `repository/UsersRepository.java`
- FE: `security/auth.service.ts`, `security/auth-state.service.ts`
- FE: `security/auth.guard.ts` (authGuard + roleGuard), `security/auth.interceptor.ts`
- FE: `security/has-role.directive.ts`, `app-state/enum/roles.enum.ts`

## Regole di tuning

### Versioni e refactor
- NON aggiornare le versioni di Spring Boot, Java e Angular in questa fase
- NON eseguire pulizie e refactor non richiesti esplicitamente: verranno fatti in sessioni apposite
- Il ControllerPrincipale contiene tutti gli endpoint: il refactor per suddividerlo sara' fatto dopo il completamento delle feature

### Database e data.sql
- Ogni volta che si modificano entita' JPA o struttura DB, verificare la coerenza di `data.sql`
- Le tabelle `cap` e `comuni` sono molto grandi (migliaia di righe): NON leggerle/analizzarle se non strettamente necessario
- Quando si modifica data.sql, toccare SOLO le INSERT relative alle tabelle effettivamente modificate
- Tabelle tipicamente trasparenti alle modifiche (saltare i controlli): `cap`, `cap_comuni`, `comuni`
- Tabelle piccole da verificare sempre quando si tocca la security: `role`, `users`, `role_users`, `tenant`

### Multi-tenant

#### Modello dati
- Entita' Tenant con campi: id, descrizione, logoUrl, colorePrimario, coloreSecondario, slug (unique), sinossi (varchar 500, breve descrizione per card)
- Gerarchia dati: Tenant -> Cimiteri (fk_tenant) -> Aree -> Posti -> Defunti
- Posti ha anche fk_tenant diretto per ottimizzazione query
- Users.fk_tenant associa utenti al proprio tenant (SUPERADMIN ha NULL = vede tutti)
- Defunti NON ha fk_tenant diretto: il tenant si ricava tramite join Defunti->Posti->Aree->Cimiteri->Tenant

#### DTO e arricchimento dati
- DefuntoCardDto (dto/): DTO arricchito con domandaId e contrattoId per azioni rapide dalle card
- TenantService.enrichDefuntiCards(): batch lookup delle Domande correlate via DomandeRepository.findByPosto_IdIn()
- Il DefuntoCardDto viene usato sia nella ricerca defunti tenant-scoped che nei defunti recenti

#### Endpoint tenant (ControllerPrincipale)
- POST /api/tenant -> creazione nuovo tenant (solo SUPERADMIN, guard programmatico nel controller)
- PUT /api/tenant/{id} -> modifica tenant (solo SUPERADMIN)
- DELETE /api/tenant/{id} -> eliminazione tenant con cascading completo (solo SUPERADMIN)
- POST /api/tenant/{id}/logo -> upload file logo (solo SUPERADMIN, multipart)
- GET /api/tenants -> lista tenant filtrata per ruolo
- GET /api/tenant/{id} -> dettaglio tenant
- GET /api/tenant/slug/{slug} -> lookup per slug (usato dal routing FE)
- GET /api/tenant/{id}/cimiteri -> cimiteri del tenant
- GET /api/tenant/{id}/defunti?limit=N&cimiteroIds=1,2,3 -> defunti recenti (paginati, filtrabili per cimitero)
- POST /api/tenant/{id}/search_defunti -> ricerca defunti con filtro cimiteri opzionale
- Logica accesso: SUPERADMIN vede tutti e puo' creare/modificare/eliminare, ADMIN/OPERATOR vedono solo il proprio tenant
- Costanti path in links/TenantLinks.java, registrati in model/ApiEndpoints.java come OPERATOR_ENDPOINTS
- Slug auto-generato da descrizione (normalizzazione accenti, spazi->trattini, unicita' garantita)

#### Eliminazione tenant (cascading)
- @Transactional con query native nell'ordine: contratti -> domande -> defunti -> posti -> strutture -> aree -> cimiteri -> UPDATE users SET fk_tenant=NULL -> tenant
- Gli utenti NON vengono eliminati, solo sganciati dal tenant (fk_tenant = NULL)
- Conferma con doppio messaggio di warning nell'interfaccia

#### Upload logo tenant
- Endpoint POST /api/tenant/{id}/logo accetta MultipartFile
- File salvato in src/main/resources/static/assets/tenants/ con nome tenant-{id}-{filename}
- Il campo logoUrl del tenant viene aggiornato automaticamente
- Nell'admin form: campo file + campo URL testuale (il file ha priorita')

#### Pannello admin (/admin)
- AdminComponent: gestione completa tenant e cimiteri
- Sezione Tenant: lista tabellare con bottoni modifica/elimina (solo SUPERADMIN)
  - Form creazione/modifica: descrizione, slug, logoUrl/upload file, colori (color picker), sinossi
  - Anteprima gradient colori in tempo reale
  - Eliminazione con confirm e warning cascading
- Sezione Cimiteri: visibile selezionando un tenant dalla lista
  - Lista cimiteri del tenant selezionato con codice, nome, indirizzo
  - Form creazione: codice, nome, indirizzo, ID comune, tenant auto-assegnato
  - Bottone elimina con conferma
  - ADMIN puo' gestire cimiteri del proprio tenant, SUPERADMIN di tutti

#### Endpoint cimiteri
- POST /api/cimitero -> crea/modifica cimitero (fallback ADMIN: SUPERADMIN+ADMIN)
- GET /api/cimitero/{id} -> dettaglio cimitero (OPERATOR_ENDPOINTS)
- DELETE /api/cimitero/{id} -> elimina cimitero (fallback ADMIN: SUPERADMIN+ADMIN)
- GET /api/tenant/{id}/cimiteri -> lista cimiteri per tenant (gia' esistente, OPERATOR)
- CimiteriService: saveCimitero (con date auto), deleteCimitero, getCimiteroById, getCimiteriByTenant
- Costanti path in links/CimiteroLinks.java

#### Query repository
- DefuntiRepositoryCustom: ricerca defunti via QueryDSL con join Defunti->Posti->Aree->Cimiteri
- Metodi accettano List<Long> cimiteroIds per filtro multi-cimitero
- findRecentDefuntiByTenant(): ordina per ID DESC con LIMIT
- DomandeRepository.findByPosto_IdIn(): batch lookup domande per lista posti (usato nell'enrichment)

#### Frontend tenant
- Routing: rotta `/:slug` mappa al TenantDashboardComponent (ultimo match, wildcard)
- TenantService (service/tenant.service.ts): 6 metodi API (getTenants, getTenant, getTenantBySlug, getCimiteriByTenant, searchDefuntiByTenant, getRecentDefuntiByTenant)
- AuthStateService: esteso con tenantId/tenantDescrizione dal JWT, esposti come Observable
- Header: badge tenant visibile con nome del tenant corrente

#### Componenti FE shared
- CercaDefuntiWidgetComponent: riusabile con @Input tenantId/cimiteroIds/showBanner/bannerTitle/bannerSubtitle, reagisce a ngOnChanges su cimiteroIds
- TenantCardComponent: card con branding tenant, naviga a /:slug
- DefuntoCardComponent: card con immagine, dati anagrafici, bottoni azione rapida domanda/contratto (con @Output openDomanda/openContratto), supporta primaryColor dinamico
- CimiteroCardComponent (NUOVO, shared/cimitero-card/): card toggle per filtrare cimiteri con @Input cimitero/active e @Output toggled
- DefuntoCard model (app-state/models/defunto-card.model.ts): estende Defunto con domandaId/contrattoId

#### Dashboard tenant (anagrafiche/tenant-dashboard/)
- Header colorato con gradient dai colori tenant + logo
- Filtro cimiteri multi-select con CimiteroCard (toggle, almeno 1 attivo)
- Widget ricerca defunti scoped al tenant/cimiteri selezionati
- Griglia defunti recenti (4 per riga) con sorting (data decesso desc/asc, cognome asc/desc)
- Modali per dettaglio domanda e contratto (riuso componenti esistenti)

#### Home page
- Widget cerca defunti globale (pubblica, in alto)
- Card tenant (autenticati, in basso): SUPERADMIN vede tutti, ADMIN/OPERATOR vedono solo il proprio

### Decisioni architetturali in vigore
- Le domande (creazione/modifica) sono accessibili solo ad ADMIN al momento
- Il ruolo USER ha possibilita' di modifica solo nel contesto della ricerca defunti (/cercadefunti)
- La registrazione utente e' aperta a tutti (self-registration), assegna automaticamente ruolo USER
- La gestione utenti da admin dashboard sara' implementata in un ticket separato

## Note ambiente
- La porta backend di default e' 8080. Se non parte, verificare che non sia gia' occupata (`lsof -i :8080`)
- Il DB H2 e' in-memory (`jdbc:h2:mem:testdb`), i dati si resettano ad ogni riavvio
- H2 Console disponibile su `/h2-console` (user: `sa`, password: `password`)
- Il progetto Java dichiara Java 17 nel pom.xml ma funziona anche con Java 21
- Il frontend richiede `npm install` dopo un clone o cambio branch per assicurarsi che `node_modules` sia completo
- Le versioni di Spring Boot (2.7.18), Java (17) e Angular (16.2) NON vanno aggiornate in questa fase
- Le tabelle `cap` e `comuni` nel data.sql sono molto grandi: evitare di leggerle/analizzarle se non strettamente necessario
