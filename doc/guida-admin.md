# Guida ADMIN

> **Stato:** In fase di sviluppo

## Descrizione ruolo

L'ADMIN e' l'amministratore di un singolo tenant (comune). Puo' gestire tutte le entita' all'interno del proprio ambito di competenza ma non ha visibilita' sugli altri tenant.

E' associato a un tenant specifico tramite `fk_tenant`. Nell'header dell'applicazione viene visualizzato il nome del tenant assegnato.

## Funzionalita' disponibili

- Visualizzazione del proprio tenant nella home page
- Gestione cimiteri del proprio tenant (creazione, eliminazione) dal pannello admin
- Accesso alla dashboard del proprio tenant con filtro cimiteri, ricerca defunti e statistiche
- Creazione e modifica domande di concessione
- Creazione e modifica contratti
- Gestione contraenti, posti, assegnatari
- Gestione utenti (lista, ricerca)
- Accesso al pannello di amministrazione
- Generazione protocollo domanda
- Stampa PDF domande e contratti
- Gestione profilo personale (password, email)

## Aree accessibili

| Sezione | Accesso |
|---|---|
| Home | Si |
| Dashboard tenant (solo il proprio) | Si |
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

- Puo' vedere e operare solo sul proprio tenant
- Non ha visibilita' sugli altri comuni/tenant del sistema
