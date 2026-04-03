# Affetti - Software gestionale cari scomparsi

## Stack
- Backend: Java 17, Spring Boot 2.7.18, Maven
- Frontend: Angular 16.2, TypeScript 5.1
- DB: H2 in-memory (modalita' PostgreSQL) per sviluppo locale
- Security: Spring Security + OAuth2 Resource Server + JWT (jjwt 0.11.5)
- ORM: Hibernate 5.6 / Spring Data JPA + QueryDSL
- Mapping: MapStruct 1.6.3 + Lombok 1.18.30
- PDF: FreeMarker + Flying Saucer + iText 5
- UI libs: Angular Material 15, Bootstrap 5, ngx-bootstrap (accordion, carousel), Leaflet (mappe), @swimlane/ngx-charts 20.5 (grafici statistiche)
- State management: NgRx 16 (presente come dipendenza, attualmente commentato nel modulo)

## Struttura
- `/` (root) -> progetto Maven, backend Spring Boot
- `/src/main/java/it/pittysoft/affetti/` -> sorgenti Java
  - `config/` -> configurazioni Spring
  - `controller/` -> REST controller (ControllerPrincipale contiene TUTTI gli endpoint)
  - `dao/` -> Data Access Objects (query QueryDSL complesse)
  - `dto/` -> Data Transfer Objects (DefuntoCardDto, TenantStatisticheDto, ChartDataDto, ChartGroupDataDto, ChartSeriesItemDto)
  - `entity/` -> entita' JPA (Tenant, Cimiteri, Aree, Strutture, Posti, Defunti, Domande, Contratti, Assegnatari, Contraenti, Users, Role, Sepolture, ecc.)
  - `mapper/` -> mapper MapStruct (DomandeMapper, PostiMapper, ContraentiMapper, AssegnatariMapper - NON esiste TenantMapper)
  - `model/` -> modelli di dominio (request/response DTO per endpoint)
  - `repository/` -> repository Spring Data (NON esistono AreeRepository e StruttureRepository)
  - `security/` -> configurazione sicurezza, JWT, filtri
  - `service/` -> logica di business (TenantService, TenantStatisticheService, CimiteriService, DomandeService, ecc.)
  - `utils/` -> utilita'
  - `links/` -> costanti path endpoint (TenantLinks, CimiteroLinks, DomandaLinks, ecc.)
- `/src/main/resources/` -> configurazioni e risorse
  - `application.properties` -> configurazione Spring Boot
  - `data.sql` -> dati iniziali H2 (~4MB, la maggior parte sono tabelle cap/comuni + immagini base64 defunti)
  - `static/` -> build Angular servita dal backend
  - `static/assets/tenants/` -> directory per upload loghi tenant
- `/src/main/ui/` -> progetto Angular (frontend)
  - `src/app/anagrafiche/` -> componenti CRUD principali + tenant-dashboard + tenant-statistiche
  - `src/app/guest/` -> login, registrazione, home pubblica
  - `src/app/admin/` -> pannello amministrazione (gestione tenant + cimiteri)
  - `src/app/security/` -> interceptor HTTP, direttive ruoli, AuthStateService
  - `src/app/header/`, `src/app/side-bar/` -> layout
  - `src/app/shared/` -> componenti riusabili (cerca-defunti-widget, tenant-card, defunto-card, cimitero-card)
  - `src/app/service/` -> servizi specifici (tenant.service con tutti i metodi CRUD + statistiche)
  - `src/app/app-state/models/` -> modelli TypeScript (Tenant, Cimitero, CimiteroSelect, DefuntoCard, TenantStatistiche, ecc.)
  - `src/app/legal/` -> privacy policy, termini e condizioni
- `/doc/` -> documentazione (index.html + guide per ruolo in markdown)

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

### Proxy FE -> BE
Il dev server Angular (`ng serve`) proxya le chiamate `/api` verso `http://localhost:8080` (configurato in `proxy.conf.json`).

## Convenzioni
- Pattern controller/service/repository nel backend
- I componenti Angular usano NgModule (non standalone), tutti dichiarati in `AppModule`
- `FormsModule` e `ReactiveFormsModule` entrambi usati nei template
- Autenticazione tramite `AuthInterceptor` che inietta il token JWT nelle richieste HTTP
- Direttiva `HasRoleDirective` per controllo accessi basato su ruoli nei template
- Le entita' JPA usano Lombok per getter/setter e MapStruct per la conversione entity <-> DTO
- AppModule importa: NgxChartsModule, AccordionModule.forRoot(), CarouselModule.forRoot() (da ngx-bootstrap)

## Security

### Architettura
- Autenticazione stateless con JWT (HS256), token valido 1 ora, no refresh token
- Spring Security + OAuth2 Resource Server per validazione JWT lato backend
- Secret key JWT centralizzata in `application.properties` (`jwt.secret`), iniettata via `@Value`
- Password hashate con BCrypt (12 round)
- Frontend: token salvato in localStorage, iniettato via `AuthInterceptor`
- Stato auth gestito da `AuthStateService` (BehaviorSubject con decode JWT locale)
- JWT include claims custom: tenantId, tenantDescrizione (per header FE)

### Ruoli
- 4 ruoli definiti: `superadmin`, `admin`, `operator`, `user` (tabella `role`, id 1/2/3/4)
- Gerarchia: SUPERADMIN >= ADMIN > OPERATOR > USER
- SUPERADMIN ha tutti i permessi di ADMIN + gestione multi-tenant (vede tutti i tenant, crea/modifica/elimina)
- Enum frontend: `Roles.SUPERADMIN`, `Roles.ADMIN`, `Roles.OPERATOR`, `Roles.USER`
- Nel JWT i ruoli sono nel claim "roles" come `["ROLE_ADMIN"]` ecc.
- `JwtAuthenticationConverter` aggiunge prefisso `ROLE_` automaticamente

### Protezione endpoint backend (SecurityConfiguration + ApiEndpoints)
- **PUBLIC** (permitAll): `/api/login`, `/api/register`, `/h2-console/**`, `/api/search_defunti`, `/api/defunto/{id}`, `/api/comuni`
- **USER** (ADMIN/OPERATOR/USER): ricerca CAP, contratti (list/search/stampa), domande (list/search/stampa), comuni (list/get), profilo
- **OPERATOR** (SUPERADMIN/ADMIN/OPERATOR): users, assegnatari, contraenti (list/search), posti (list/search), cimitero/*, tenant endpoints (list/get/cimiteri/search_defunti/defunti/statistiche), + tutto USER
- **ADMIN** (SUPERADMIN/ADMIN): tutto il resto sotto `/api/**` (include POST creazione/modifica, DELETE)
- Endpoint SUPERADMIN-only con guard programmatico nel controller: POST/PUT/DELETE /api/tenant, POST /api/tenant/{id}/logo

### Protezione rotte frontend (app-routing.module.ts)
- **Pubbliche**: /home, /login, /register, /cercadefunti, /defunti/:id, /privacy-policy, /termini-condizioni, /pitty-in
- **authGuard** (qualsiasi utente autenticato): /profile
- **roleGuard([SUPERADMIN,ADMIN,OPERATOR])**: /user, /comune, /contraente, /posto, /assegnatario
- **roleGuard([SUPERADMIN,ADMIN,OPERATOR,USER])**: /contratto
- **roleGuard([SUPERADMIN,ADMIN])**: /domanda, /domandaFull, /admin
- **/:slug** (wildcard, ultima rotta): TenantDashboardComponent con roleGuard([SUPERADMIN,ADMIN,OPERATOR])

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
- ATTENZIONE: posti.fk_tenant viene popolato via UPDATE dopo le INSERT (non nella INSERT stessa). Le query statistiche usano il join aree->cimiteri->tenant, NON posti.fk_tenant direttamente
- I defunti del blocco 1 (19 record) hanno immagini base64 enormi: NON riscrivere quelle righe, fare solo modifiche chirurgiche con sed/python se necessario

### Dati mock attuali (data.sql)
- 3 tenant: Roma (id=1), Milano (id=2), Napoli (id=3)
- 4 cimiteri: CIM001+CIM004 (Roma), CIM002 (Milano), CIM003 (Napoli)
- 6 aree distribuite sui 4 cimiteri
- 10 strutture (fornici)
- 35 posti: 13 Roma, 12 Milano, 10 Napoli. Stati: LIBERO, OCCUPATO, PRENOTATO, DA_LIBERARE. Tipi sepoltura variati (loculo, tomba, cappella, ossario, colombario, sepolcro)
- 30 defunti (19 con immagini base64 + 11 senza): distribuiti 12 Roma, 9 Milano, 9 Napoli
- 30 assegnatari (1 per defunto)
- 30 contraenti (1 per domanda)
- 30 domande: date distribuite 2019-2025, stati APERTA/CHIUSA, tipologie LOCULO/TENUTA_DISPOSIZIONE
- 30 contratti: date allineate alle domande, scadenze varie (2054-2060 + 3 in scadenza entro 2026 per KPI), stati PAGATO/IN_ATTESA_PAGAMENTO/SCADUTO
- 20 sepolture: tipo INUMAZIONE/TUMULAZIONE/ESUMAZIONE distribuite sui 3 tenant

## Multi-tenant

### Modello dati
- Entita' Tenant con campi: id, descrizione, logoUrl, colorePrimario, coloreSecondario, slug (unique), sinossi (varchar 500)
- Gerarchia dati: Tenant -> Cimiteri (fk_tenant) -> Aree (fk_cimitero) -> Strutture (fk_area) -> Posti (fk_area, fk_struttura, fk_tenant)
- Posti ha fk_tenant diretto (popolato via UPDATE in data.sql) + fk_area per join alla catena cimitero->tenant
- Users.fk_tenant associa utenti al proprio tenant (SUPERADMIN ha NULL = vede tutti)
- Defunti NON ha fk_tenant: il tenant si ricava tramite join Defunti->Posti->Aree->Cimiteri->Tenant
- IMPORTANTE: per query aggregate/statistiche usare sempre il join via aree->cimiteri, NON posti.fk_tenant (puo' essere NULL in certi contesti)

### DTO e arricchimento dati
- DefuntoCardDto (dto/): DTO arricchito con domandaId e contrattoId per azioni rapide dalle card
- TenantService.enrichDefuntiCards(): batch lookup delle Domande correlate via DomandeRepository.findByPosto_IdIn()

### Endpoint tenant (ControllerPrincipale)
- POST /api/tenant -> creazione nuovo tenant (solo SUPERADMIN, guard programmatico)
- PUT /api/tenant/{id} -> modifica tenant (solo SUPERADMIN)
- DELETE /api/tenant/{id} -> eliminazione con cascading completo (solo SUPERADMIN)
- POST /api/tenant/{id}/logo -> upload file logo multipart (solo SUPERADMIN)
- GET /api/tenants -> lista filtrata per ruolo
- GET /api/tenant/{id} -> dettaglio
- GET /api/tenant/slug/{slug} -> lookup per slug (routing FE)
- GET /api/tenant/{id}/cimiteri -> cimiteri del tenant
- GET /api/tenant/{id}/defunti?limit=N&cimiteroIds=... -> defunti recenti
- POST /api/tenant/{id}/search_defunti -> ricerca defunti
- GET /api/tenant/{id}/statistiche?cimiteroIds=... -> statistiche aggregate
- Costanti path in links/TenantLinks.java

### Eliminazione tenant (cascading)
- TenantService.deleteTenant(): @Transactional con query native
- Ordine: contratti -> domande -> defunti -> posti -> strutture -> aree -> cimiteri -> UPDATE users SET fk_tenant=NULL -> tenant
- Gli utenti NON vengono eliminati, solo sganciati (fk_tenant = NULL)

### Upload logo
- File salvato in src/main/resources/static/assets/tenants/tenant-{id}-{filename}
- logoUrl aggiornato automaticamente. Form admin: campo file + campo URL testuale (file ha priorita')

### Endpoint cimiteri
- POST /api/cimitero -> crea/modifica (ADMIN+)
- GET /api/cimitero/{id} -> dettaglio (OPERATOR+)
- DELETE /api/cimitero/{id} -> elimina (ADMIN+)
- CimiteriService: saveCimitero, deleteCimitero, getCimiteroById, getCimiteriByTenant
- Costanti path in links/CimiteroLinks.java

### Pannello admin (/admin)
- AdminComponent: gestione completa tenant e cimiteri
- Sezione Tenant: lista tabellare cliccabile (seleziona tenant attivo) + bottoni modifica/elimina (solo SUPERADMIN)
  - Form creazione/modifica: descrizione, slug (auto-generato), logoUrl/upload file, colori (color picker), sinossi
  - Anteprima gradient colori in tempo reale
  - Eliminazione con confirm e warning cascading
- Sezione Cimiteri: appare dopo selezione tenant
  - Lista con codice, nome, indirizzo + bottone elimina
  - Form creazione: codice, nome, indirizzo, ID comune, tenant auto-assegnato

### Dashboard tenant (anagrafiche/tenant-dashboard/)
- Layout: Header tenant -> Widget ricerca defunti + card cimiteri -> Statistiche (accordion) -> Griglia defunti recenti
- Header colorato con gradient dai colori tenant + logo
- Filtro cimiteri multi-select con CimiteroCard (toggle, almeno 1 attivo)
- Widget ricerca defunti scoped al tenant/cimiteri selezionati
- Griglia defunti recenti (4 per riga) con sorting (data decesso desc/asc, cognome asc/desc)
- Modali per dettaglio domanda e contratto
- NOTA TECNICA: `activeCimiteroIdsArray` e' una property (NON un getter) sincronizzata via `syncCimiteroIdsArray()` per evitare loop infiniti di change detection con i componenti figli

### Statistiche tenant (anagrafiche/tenant-statistiche/)
- Accordion ngx-bootstrap (chiuso di default, lazy loading al primo open)
- Navigazione custom con ngSwitch (NO carousel nativo per evitare sovrapposizione frecce/contenuto)
- 4 slide con altezza fissa 420px e centratura verticale:
  1. KPI: totale defunti, posti liberi/totali, domande aperte, contratti in scadenza (6 mesi)
  2. Distribuzione (donut): posti per stato, per tipo sepoltura, domande per stato, sepolture per tipo operazione
  3. Trend temporale (linea): decessi/domande/contratti per mese
  4. Confronto (barre raggruppate): capienza vs occupazione per struttura, posti per stato per cimitero
- Dropdown per cambiare dataset in ogni slide
- Barra navigazione custom sotto il grafico: frecce + pill button con label slide
- Color scheme generato dai colori tenant (6 sfumature via lighten)
- Reagisce a ngOnChanges su cimiteroIds (ricarica se accordion aperto, segna needsReload se chiuso)
- Guard `if (this.loading) return` in loadStats() per prevenire chiamate duplicate
- Backend: TenantStatisticheService con query native via EntityManager, join aree->cimiteri->tenant
- DTO: TenantStatisticheDto (KPI + 4 distribuzioni + 3 trend + 2 confronti), ChartDataDto, ChartGroupDataDto, ChartSeriesItemDto

### Componenti FE shared
- CercaDefuntiWidgetComponent: @Input tenantId/cimiteroIds/showBanner/bannerTitle/bannerSubtitle
- TenantCardComponent: card con logo (o placeholder lettera), nome, sinossi, naviga a /:slug
- DefuntoCardComponent: card con avatar, dati anagrafici, bottoni domanda/contratto con @Output
- CimiteroCardComponent: card toggle per filtrare cimiteri

### Home page
- Widget cerca defunti globale (pubblica, in alto)
- Card tenant (autenticati, in basso): SUPERADMIN vede tutti, ADMIN/OPERATOR vedono solo il proprio

### Frontend services
- TenantService (service/tenant.service.ts): getTenants, getTenant, getTenantBySlug, getCimiteriByTenant, searchDefuntiByTenant, getRecentDefuntiByTenant, createTenant, updateTenant, deleteTenant, uploadTenantLogo, saveCimitero, getCimitero, deleteCimitero, getStatistiche

### Decisioni architetturali in vigore
- Le domande (creazione/modifica) sono accessibili solo ad ADMIN al momento
- Il ruolo USER ha possibilita' di modifica solo nel contesto della ricerca defunti (/cercadefunti)
- La registrazione utente e' aperta a tutti (self-registration), assegna automaticamente ruolo USER
- La gestione utenti da admin dashboard sara' implementata in un ticket separato
- Il ControllerPrincipale contiene tutti gli endpoint: il refactor per suddividerlo e' pianificato dopo le feature

## Note ambiente
- La porta backend di default e' 8080. Se non parte, verificare che non sia gia' occupata (`lsof -i :8080`)
- Il DB H2 e' in-memory (`jdbc:h2:mem:testdb`), i dati si resettano ad ogni riavvio
- H2 Console disponibile su `/h2-console` (user: `sa`, password: `password`)
- Il progetto Java dichiara Java 17 nel pom.xml ma funziona anche con Java 21
- Il frontend richiede `npm install` dopo un clone o cambio branch per assicurarsi che `node_modules` sia completo
- Le versioni di Spring Boot (2.7.18), Java (17) e Angular (16.2) NON vanno aggiornate in questa fase
- Le tabelle `cap` e `comuni` nel data.sql sono molto grandi: evitare di leggerle/analizzarle se non strettamente necessario
- Branch attuale di sviluppo: `feature/multi-tenant` (ref ticket #376)
