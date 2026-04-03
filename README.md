# affetti
Affetti - Software gestionale cari scomparsi

piTTySoft srl

## Security - Mappa Endpoint e Ruoli

### Ruoli
| Ruolo | Descrizione |
|---|---|
| SUPERADMIN | Come ADMIN, predisposto per gestione multi-tenant (vede tutti i tenant) |
| ADMIN | Accesso completo, gestione utenti, creazione/modifica di tutte le entita'. Vede solo il proprio tenant |
| OPERATOR | Consultazione completa di tutte le anagrafiche e dashboard tenant (read-only), nessuna modifica |
| USER | Consultazione limitata (domande, contratti, comuni, CAP). Modifica relegata al contesto ricerca defunti |

### Protezione backend
La security e' interamente URL-based nel `SecurityFilterChain` (nessun `@PreAuthorize` sui metodi).
L'ordine di valutazione e': PUBLIC -> USER -> OPERATOR -> fallback ADMIN per tutto `/api/**`.

### Mappa endpoint - chi puo' fare cosa

#### Endpoint pubblici (nessuna autenticazione)
| Endpoint | Metodo | Descrizione |
|---|---|---|
| `/api/login` | POST | Autenticazione, restituisce JWT |
| `/api/register` | POST | Registrazione utente (self-service, assegna ruolo USER) |
| `/api/search_defunti` | POST | Ricerca pubblica defunti |
| `/api/defunto/{id}` | GET | Dettaglio singolo defunto |
| `/api/comuni` | GET | Lista comuni (per form registrazione) |
| `/h2-console/**` | * | Console H2 (solo sviluppo) |

#### Endpoint USER (SUPERADMIN + ADMIN + OPERATOR + USER)
| Endpoint | Metodo | Descrizione |
|---|---|---|
| `/api/get_lista_cap` | POST | Ricerca CAP per comune |
| `/api/get_comune` | POST | Dettaglio comune |
| `/api/domande` | GET | Lista domande |
| `/api/search_domande` | POST | Ricerca domande |
| `/api/stampa_domanda/{id}` | GET | Genera PDF domanda |
| `/api/contratti` | GET | Lista contratti |
| `/api/search_contratti` | POST | Ricerca contratti |
| `/api/get_contratto_by_protocollo` | POST | Contratto per protocollo |
| `/api/stampa_contratto/{id}` | GET | Genera PDF contratto |
| `/api/profile` | GET | Profilo utente corrente |
| `/api/profile/password` | PUT | Modifica password |
| `/api/profile/email` | PUT | Modifica email |

#### Endpoint OPERATOR (SUPERADMIN + ADMIN + OPERATOR)
| Endpoint | Metodo | Descrizione |
|---|---|---|
| `/api/users` | GET | Lista utenti |
| `/api/search_user` | POST | Ricerca utenti |
| `/api/assegnatari` | GET | Lista assegnatari |
| `/api/contraenti` | GET | Lista contraenti |
| `/api/search_contraenti` | POST | Ricerca contraenti |
| `/api/posti` | GET | Lista posti |
| `/api/search_posti` | POST | Ricerca posti |
| `/api/cimitero/{id}` | GET | Dettaglio cimitero |
| `/api/tenants` | GET | Lista tenant (SUPERADMIN: tutti, altri: solo il proprio) |
| `/api/tenant/{id}` | GET | Dettaglio tenant |
| `/api/tenant/{id}/cimiteri` | GET | Cimiteri del tenant |
| `/api/tenant/{id}/search_defunti` | POST | Ricerca defunti nel tenant (con filtro cimitero opzionale) |
| `/api/tenant/{id}/defunti` | GET | Defunti recenti del tenant |
| `/api/tenant/{id}/statistiche` | GET | Statistiche aggregate del tenant (KPI, distribuzioni, trend, confronti) |

#### Endpoint ADMIN (SUPERADMIN + ADMIN)
| Endpoint | Metodo | Descrizione |
|---|---|---|
| `/api/tenant` | POST | Crea nuovo tenant (solo SUPERADMIN, guard programmatico) |
| `/api/tenant/{id}` | PUT | Modifica tenant (solo SUPERADMIN) |
| `/api/tenant/{id}` | DELETE | Elimina tenant con cascading completo (solo SUPERADMIN) |
| `/api/tenant/{id}/logo` | POST | Upload logo tenant (multipart, solo SUPERADMIN) |
| `/api/cimitero` | POST | Crea/modifica cimitero |
| `/api/cimitero/{id}` | DELETE | Elimina cimitero |
| `/api/user` | POST | Crea/modifica utente |
| `/api/domanda` | POST | Crea/modifica domanda |
| `/api/domandaFull` | POST | Crea domanda completa |
| `/api/contratto` | POST | Crea/modifica contratto |
| `/api/contraente` | POST | Crea/modifica contraente |
| `/api/posto` | POST | Crea/modifica posto |
| `/api/assegnatario` | POST | Crea/modifica assegnatario |
| `/api/comune` | POST | Crea/modifica comune |
| `/api/genera_protocollo_domanda` | GET | Genera nuovo protocollo |

### Protezione rotte frontend
| Rotta | Guard | Ruoli |
|---|---|---|
| /home, /login, /register, /cercadefunti, /defunti/:id | Nessuno | Pubblico |
| /privacy-policy, /termini-condizioni, /pitty-in | Nessuno | Pubblico |
| /profile | authGuard | Qualsiasi autenticato |
| /user, /comune, /contraente, /posto, /assegnatario | authGuard + roleGuard | SUPERADMIN, ADMIN, OPERATOR |
| /contratto | authGuard + roleGuard | SUPERADMIN, ADMIN, OPERATOR, USER |
| /domanda, /domandaFull | authGuard + roleGuard | SUPERADMIN, ADMIN |
| /tenant/:id | authGuard + roleGuard | SUPERADMIN, ADMIN, OPERATOR |
| /admin | authGuard + roleGuard | SUPERADMIN, ADMIN |

### Visibilita' sidebar (direttiva *appHasRole)
| Voce menu | Ruoli |
|---|---|
| Nuova Domanda, Domande | SUPERADMIN, ADMIN |
| Contratti | SUPERADMIN, ADMIN, OPERATOR, USER |
| Posti, Contraenti | SUPERADMIN, ADMIN, OPERATOR |
| Cerca Defunti | Pubblico |

## Multi-tenant

### Architettura
- Entita' Tenant: id, descrizione, logoUrl, colorePrimario, coloreSecondario, slug (unique), sinossi
- Gerarchia dati: Tenant -> Cimiteri (fk_tenant) -> Aree (fk_cimitero) -> Strutture (fk_area) -> Posti (fk_area, fk_tenant)
- Ogni utente (ADMIN, OPERATOR) e' associato a un tenant (Users.fk_tenant)
- SUPERADMIN ha fk_tenant = NULL e vede tutti i tenant
- CRUD completo tenant: creazione, modifica, eliminazione con cascading, upload logo (solo SUPERADMIN)
- CRUD cimiteri: creazione, eliminazione (ADMIN+)

### Home page
- Ricerca defunti globale (pubblica, in alto)
- Card tenant con logo, nome e sinossi (autenticati, in basso): SUPERADMIN vede tutti, ADMIN/OPERATOR vedono solo il proprio

### Dashboard tenant (/:slug)
- URL basato su slug del tenant (es. /comune-di-roma)
- Header colorato con gradient colori tenant + logo
- Filtro cimiteri multi-select con card toggle (almeno 1 attivo)
- Widget ricerca defunti scoped al tenant/cimiteri
- Sezione statistiche (accordion, lazy loading): KPI, grafici donut/linea/barre con navigazione custom
- Griglia defunti recenti (4 per riga) con sorting e bottoni azione domanda/contratto

### Pannello admin (/admin)
- Gestione tenant: lista, creazione, modifica, eliminazione (SUPERADMIN)
- Gestione cimiteri per tenant selezionato: lista, creazione, eliminazione

### Utenti di test
| Username | Password | Ruolo | Tenant |
|---|---|---|---|
| superadmin | superadmin | SUPERADMIN | Nessuno (vede tutti) |
| admin_roma | admin_roma | ADMIN | Comune di Roma |
| admin_milano | admin_milano | ADMIN | Comune di Milano |
| admin_napoli | admin_napoli | ADMIN | Comune di Napoli |
| operator | operator | OPERATOR | Comune di Roma |
| user | user | USER | Nessuno |

