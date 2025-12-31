import { Component, ContentChild, OnInit, ViewChild } from "@angular/core";
import { FormControl, FormGroup, Validators } from "@angular/forms";
import { Observable, Subject } from "rxjs";
import { map, startWith, takeUntil } from "rxjs/operators";
import { AppService } from "src/app/app.service";
import { ContraentiModelComponent } from "../contraenti-model/contraenti-model.component";
import { Contraente, Posto1 } from "src/app/app-state/models";
import { Posto } from "src/app/app-state/models";
import { PostiModelComponent } from "../posti-model/posti-model.component";
import { each } from "jquery";
import { CercacontraentiModelComponent } from "../cercacontraenti-model/cercacontraenti-model.component";
import { PopupComponent } from "src/app/popup/popup.component";
import { Router } from "@angular/router";
import { Comune } from "codice-fiscale-js/types/comune";
import { Utils } from 'src/app/app-state/shared/utils';

declare var $: any;
@Component({
  selector: "app-domanda-full",
  templateUrl: "./domanda-full.component.html",
  styleUrls: ["./domanda-full.component.css"],
})
export class DomandaFullComponent implements OnInit {
  @ViewChild(PopupComponent) childPopUp: PopupComponent | undefined;
  @ViewChild(ContraentiModelComponent) child:
    | ContraentiModelComponent
    | undefined;
  @ViewChild(PostiModelComponent) child1: PostiModelComponent | undefined;
  @ViewChild(CercacontraentiModelComponent) child2:
    | CercacontraentiModelComponent
    | undefined;

  constructor(private appService: AppService, private router: Router, private utils: Utils) {}

  ngOnInit(): void {
    //this.inputValidation();
    // Recupera tutti i comuni
    this.getAllComuni();
    //Inizializzazione dell'array che andrà a popolare il campo relativo al comune di decesso
    this.comuniDecessoFiltrati = this.domandaFullForm.get('comune_decesso').valueChanges.pipe(
      startWith(''), 
      map(value => this.comuniFilter(value || '')) 
    );
  }

  comuni: Comune[] = [];
  comuniDecessoFiltrati: Observable<Comune[]>;

  //Funzione per recuperare la lista con tutti i comuni
  getAllComuni(){
    this.appService.getComuni().pipe(takeUntil(this.destroy$)).subscribe((data: any) => {
              this.comuni = data.comuni;
          });
    
  }

  //Funzione che filtra la lista dei comuni in base ad una stringa data in input
  private comuniFilter(value: string): Comune[] {
    const valoreFiltro =  this.utils.normalizeValue(value);
    return this.comuni.filter(comune => this.utils.normalizeValue(comune.nome).includes(valoreFiltro));
  }

  //Funzione che imposta il valore del campo di input comune_decesso con il nome del comune selezionato
  onComuneDecSelect(comuneDecessoSelezionato: Comune){
    this.domandaFullForm.get('comune_decesso').setValue(comuneDecessoSelezionato.nome)
  }

  domandaFullForm = new FormGroup({
    fk_contraente: new FormControl("", Validators.required),
    nome: new FormControl("", Validators.required),
    cognome: new FormControl("", Validators.required),
    comune_nascita: new FormControl("", Validators.required),
    provincia_nascita: new FormControl("", Validators.required),
    stato_nascita: new FormControl("", Validators.required),
    data_nascita: new FormControl("", Validators.required),
    tipologia: new FormControl("", Validators.required),
    comune_residenza: new FormControl("", Validators.required),
    provincia_residenza: new FormControl("", Validators.required),
    via_residenza: new FormControl("", Validators.required),
    civico_residenza: new FormControl("", Validators.required),
    cap_residenza: new FormControl("", Validators.required),
    codice_fiscale: new FormControl("", Validators.required),
    email: new FormControl("", [Validators.required, Validators.email]),
    note: new FormControl(""),

    protocollo: new FormControl("", Validators.required),
    data_protocollo: new FormControl("", Validators.required),

    loculo: new FormControl("", Validators.required),
    fornice: new FormControl("", Validators.required),

    nomeAss: new FormControl("", Validators.required),
    cognomeAss: new FormControl("", Validators.required),

    comune_decesso: new FormControl("", Validators.required),
    data_decesso: new FormControl("", Validators.required),
  });

  destroy$: Subject<boolean> = new Subject<boolean>();

  addDomanda() {
    //this.inputValidation();

    this.appService
      .addDomandaFull(this.domandaFullForm.value)
      .pipe(takeUntil(this.destroy$))
      .subscribe((data) => {
        console.log("message::::", data);
        this.childPopUp?.showPopupModal();

        setTimeout(() => {
          this.childPopUp?.hidePopupModal();
          console.log("chiamato hide");
          this.router.navigate(["/domanda"]);
        }, 3000);
      });
  }

  //funzione di validazione onInit. Non più utilizzata.
  //La validazione è gestita direttamente nell'HTML attraverso l'aggiunta condizionale della classe "is-invalid" sui singoli input
  inputValidation() {
    //prendi tutti gli elementi FormControl dalla proprietà domandaFullForm di DomandaFullComponent, in un formato simile ad un literal object (key:value)
    const formControls = this.domandaFullForm.controls;
    //prendi l'elemento del DOM con id domanda-full-form (l'intero form)
    const formElement = document.getElementById("domanda-full-form");

    //verifica che l'oggetto formElement esista (non null o undefined)
    if (!formElement) return;

    //itera su tutte le chiavi dell'oggetto formControls
    for (let key in formControls) {
      //dichiara due variabili per prendere la proprietà di Angular (control) e il corrispondente oggetto del DOM (input)
      const control = formControls[key];
      const input = formElement.querySelector("#" + key);

      // Verifica che l'oggetto del DOM esista, abbia la proprietà 'classList' e che non sia la textarea 'note' (non richiede validazione)
      if (input && "classList" in input && key != "note") {
        //rimuove le classi precedentemente applicate
        input.classList.remove("is-invalid", "is-valid");

        //verifica la validità di control (Angular) e applica le proprietà 'is-valid' o 'is-invalid' di bootstrap di conseguenza
        if (control.invalid) {
          input.classList.add("is-invalid");
        } else {
          input.classList.add("is-valid");
        }
      }
    }
  }

  creaContraente() {
    this.child?.showContraentiModal();
  }

  cercaPosto() {
    this.child1?.showPostiModal();
  }

  cercaContraente() {
    this.child2?.showCercacontraentiModal();
  }

  saveContraenteWatcher(contraente: Contraente) {
    this.domandaFullForm.controls["fk_contraente"].setValue(
      contraente.id.toString()
    );
    this.domandaFullForm.controls["nome"].setValue(contraente.nome.toString());
    this.domandaFullForm.controls["cognome"].setValue(
      contraente.cognome.toString()
    );
    this.domandaFullForm.controls["comune_nascita"].setValue(
      contraente.comune_nascita.toString()
    );
    this.domandaFullForm.controls["provincia_nascita"].setValue(
      contraente.provincia_nascita.toString()
    );
    this.domandaFullForm.controls["stato_nascita"].setValue(
      contraente.stato_nascita.toString()
    );
    this.domandaFullForm.controls["data_nascita"].setValue(
      contraente.data_nascita.toString()
    );
    this.domandaFullForm.controls["comune_residenza"].setValue(
      contraente.comune_residenza.toString()
    );
    this.domandaFullForm.controls["provincia_residenza"].setValue(
      contraente.provincia_residenza.toString()
    );
    this.domandaFullForm.controls["via_residenza"].setValue(
      contraente.via_residenza.toString()
    );
    this.domandaFullForm.controls["civico_residenza"].setValue(
      contraente.civico_residenza.toString()
    );
    this.domandaFullForm.controls["cap_residenza"].setValue(
      contraente.cap_residenza.toString()
    );
    this.domandaFullForm.controls["codice_fiscale"].setValue(
      contraente.codice_fiscale.toString()
    );
    this.domandaFullForm.controls["email"].setValue(
      contraente.email.toString()
    );
    this.domandaFullForm.controls["note"].setValue(contraente.note.toString());
  }

  savePostoWatcher(posto: Posto1) {
    this.domandaFullForm.controls["loculo"].setValue(posto.loculo);
    this.domandaFullForm.controls["fornice"].setValue(posto.fornice);
    this.domandaFullForm.controls["nomeA"].setValue(posto.nome);
    this.domandaFullForm.controls["cognomeA"].setValue(posto.cognome);
  }

  assegnaPostoWatcher(posti: any[]) {
    let loculo;
    let fornice;
    let nome;
    let cognome;

    posti.forEach(function (value) {
      if (value.checked) {
        loculo = value.loculo;
        fornice = value.fornice;
        nome = value.nome;
        cognome = value.cognome;
      }
    });
    this.domandaFullForm.controls["loculo"].setValue(loculo);
    this.domandaFullForm.controls["fornice"].setValue(fornice);
    this.domandaFullForm.controls["nomeAss"].setValue(nome);
    this.domandaFullForm.controls["cognomeAss"].setValue(cognome);
  }

  assegnaContraenteWatcher(contraenti: any[]) {
    let nome;
    let cognome;
    let codice_fiscale;
    let comune_nascita;
    let provincia_nascita;
    let stato_nascita;
    let data_nascita;
    let comune_residenza;
    let provincia_residenza;
    let via_residenza;
    let civico_residenza;
    let cap_residenza;
    let email;
    let note;
    let fk_contraente;

    contraenti.forEach(function (value) {
      if (value.checked) {
        nome = value.nome;
        cognome = value.cognome;
        codice_fiscale = value.codice_fiscale;
        comune_nascita = value.comune_nascita;
        provincia_nascita = value.provincia_nascita;
        stato_nascita = value.stato_nascita;
        data_nascita = value.data_nascita;
        codice_fiscale = value.codice_fiscale;
        comune_residenza = value.comune_residenza;
        provincia_residenza = value.provincia_residenza;
        via_residenza = value.via_residenza;
        civico_residenza = value.civico_residenza;
        cap_residenza = value.cap_residenza;
        email = value.email;
        note = value.note;
        fk_contraente = value.id;
      }
    });
    this.domandaFullForm.controls["nome"].setValue(nome);
    this.domandaFullForm.controls["cognome"].setValue(cognome);
    this.domandaFullForm.controls["comune_nascita"].setValue(comune_nascita);
    this.domandaFullForm.controls["provincia_nascita"].setValue(
      provincia_nascita
    );
    this.domandaFullForm.controls["stato_nascita"].setValue(stato_nascita);
    this.domandaFullForm.controls["data_nascita"].setValue(data_nascita);
    this.domandaFullForm.controls["comune_residenza"].setValue(
      comune_residenza
    );
    this.domandaFullForm.controls["provincia_residenza"].setValue(
      provincia_residenza
    );
    this.domandaFullForm.controls["via_residenza"].setValue(via_residenza);
    this.domandaFullForm.controls["civico_residenza"].setValue(
      civico_residenza
    );
    this.domandaFullForm.controls["cap_residenza"].setValue(cap_residenza);
    this.domandaFullForm.controls["codice_fiscale"].setValue(codice_fiscale);
    this.domandaFullForm.controls["email"].setValue(email);
    this.domandaFullForm.controls["note"].setValue(note);
    this.domandaFullForm.controls["fk_contraente"].setValue(fk_contraente);
  }
  showPopuoModal() {
    $("#popUpModal").modal("show");
  }

  protocolloGenerato: boolean = false;

  generaProtocollo() {
    this.appService
      .getNuovoProtocolloDomanda()
      .pipe(takeUntil(this.destroy$))
      .subscribe((data: any) => {
        this.protocolloGenerato = data.protocolloDomanda.generato;
        this.domandaFullForm.controls["protocollo"].setValue(
          data.protocolloDomanda.protocollo
        );
      });
  }
}