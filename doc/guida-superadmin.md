# Guida SUPERADMIN

> **Stato:** In fase di sviluppo

## Descrizione ruolo

Il SUPERADMIN ha il livello di accesso piu' elevato nel sistema. Possiede tutti i permessi dell'ADMIN con l'aggiunta della gestione multi-tenant: puo' visualizzare e operare su tutti i tenant (comuni) registrati nel sistema.

Non e' associato a nessun tenant specifico (`fk_tenant = NULL`), il che gli consente visibilita' trasversale su tutti i dati.

## Funzionalita' disponibili

- Visualizzazione di tutti i tenant nella home page (card tenant con logo, nome e sinossi)
- Creazione, modifica ed eliminazione tenant dal pannello di amministrazione
- Upload logo per ogni tenant (file immagine o URL manuale)
- Eliminazione tenant con cascading completo (rimuove tutti i dati collegati)
- Gestione cimiteri per ogni tenant (creazione, eliminazione) dal pannello admin
- Accesso alla dashboard di ogni tenant con filtro cimiteri e ricerca defunti
- Creazione e modifica domande di concessione
- Creazione e modifica contratti
- Gestione contraenti, posti, assegnatari, comuni
- Gestione utenti (lista, ricerca)
- Accesso al pannello di amministrazione
- Generazione protocollo domanda
- Stampa PDF domande e contratti
- Gestione profilo personale (password, email)

## Aree accessibili

| Sezione | Accesso |
|---|---|
| Home | Si |
| Dashboard tenant (tutti) | Si |
| Domande (creazione/modifica) | Si |
| Contratti | Si |
| Contraenti | Si |
| Posti | Si |
| Assegnatari | Si |
| Utenti | Si |
| Comuni | Si |
| Admin | Si |
| Cerca Defunti | Si |
| Profilo | Si |

## Limitazioni

Nessuna limitazione. Il SUPERADMIN ha accesso completo a tutte le funzionalita' del sistema.
