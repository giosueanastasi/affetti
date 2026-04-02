# affetti
Affetti - Software gestionale cari scomparsi

piTTySoft srl

## Security - Mappa Endpoint e Ruoli

### Ruoli
| Ruolo | Descrizione |
|---|---|
| ADMIN | Accesso completo, gestione utenti, creazione/modifica di tutte le entita' |
| OPERATOR | Consultazione completa di tutte le anagrafiche, nessuna modifica |
| USER | Consultazione limitata (domande, contratti, comuni, CAP). Modifica relegata al contesto ricerca defunti |

### Protezione backend
La security e' interamente URL-based nel `SecurityFilterChain` (nessun `@PreAuthorize` sui metodi).
L'ordine di valutazione e': PUBLIC -> USER -> OPERATOR -> fallback ADMIN per tutto `/api/**`.

### Mappa endpoint - chi puo' fare cosa

#### Endpoint pubblici (nessuna autenticazione)
| Endpoint | Metodo | Descrizione |
|---|---|---|
| `/api/login` | POST | Autenticazione, restituisce JWT |
| `/api/search_defunti` | POST | Ricerca pubblica defunti |
| `/api/defunto/{id}` | GET | Dettaglio singolo defunto |
| `/h2-console/**` | * | Console H2 (solo sviluppo) |

#### Endpoint USER (ADMIN + OPERATOR + USER)
| Endpoint | Metodo | Descrizione |
|---|---|---|
| `/api/get_lista_cap` | POST | Ricerca CAP per comune |
| `/api/comuni` | GET | Lista comuni |
| `/api/get_comune` | POST | Dettaglio comune |
| `/api/domande` | GET | Lista domande |
| `/api/search_domande` | POST | Ricerca domande |
| `/api/stampa_domanda/{id}` | GET | Genera PDF domanda |
| `/api/contratti` | GET | Lista contratti |
| `/api/search_contratti` | POST | Ricerca contratti |
| `/api/get_contratto_by_protocollo` | POST | Contratto per protocollo |
| `/api/stampa_contratto/{id}` | GET | Genera PDF contratto |

#### Endpoint OPERATOR (ADMIN + OPERATOR)
| Endpoint | Metodo | Descrizione |
|---|---|---|
| `/api/users` | GET | Lista utenti |
| `/api/search_user` | POST | Ricerca utenti |
| `/api/assegnatari` | GET | Lista assegnatari |
| `/api/contraenti` | GET | Lista contraenti |
| `/api/search_contraenti` | POST | Ricerca contraenti |
| `/api/posti` | GET | Lista posti |
| `/api/search_posti` | POST | Ricerca posti |

#### Endpoint ADMIN (solo ADMIN)
| Endpoint | Metodo | Descrizione |
|---|---|---|
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
| /user, /comune, /contraente, /posto, /assegnatario | authGuard | Qualsiasi autenticato |
| /contratto, /domanda, /domandaFull | authGuard | Qualsiasi autenticato |
| /admin | authGuard + roleGuard | ADMIN |

### Visibilita' sidebar (direttiva *appHasRole)
| Voce menu | Ruoli |
|---|---|
| Nuova Domanda, Domande, Contratti | ADMIN, OPERATOR, USER |
| Posti, Contraenti | ADMIN, OPERATOR |
| Cerca Defunti | Pubblico |

