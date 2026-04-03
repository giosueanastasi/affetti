# Guida OPERATOR

> **Stato:** In fase di sviluppo

## Descrizione ruolo

L'OPERATOR ha accesso in sola lettura alle anagrafiche e alla dashboard del proprio tenant. Puo' consultare tutte le informazioni ma non puo' creare o modificare entita'.

E' associato a un tenant specifico tramite `fk_tenant`.

## Funzionalita' disponibili

- Visualizzazione del proprio tenant nella home page
- Accesso alla dashboard del proprio tenant con filtro cimiteri, ricerca defunti e statistiche
- Consultazione lista domande e contratti
- Consultazione dettaglio domande e contratti (read-only)
- Consultazione contraenti, posti, assegnatari, utenti
- Ricerca defunti pubblica
- Accesso alla sezione contratti
- Gestione profilo personale (password, email)

## Aree accessibili

| Sezione | Accesso |
|---|---|
| Home | Si |
| Dashboard tenant (solo il proprio) | Si |
| Domande (solo consultazione) | No |
| Contratti (consultazione) | Si |
| Contraenti (consultazione) | Si |
| Posti (consultazione) | Si |
| Assegnatari (consultazione) | Si |
| Utenti (consultazione) | Si |
| Admin | No |
| Cerca Defunti | Si |
| Profilo | Si |

## Limitazioni

- Accesso in sola lettura: non puo' creare o modificare domande, contratti, contraenti, posti o assegnatari
- Non ha accesso alla sezione creazione/modifica domande
- Non ha accesso al pannello di amministrazione
- Visibilita' limitata al proprio tenant
