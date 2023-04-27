import { Component, ContentChild, OnInit, ViewChild } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { Subject } from 'rxjs';
import { takeUntil } from 'rxjs/operators';
import { AppService } from 'src/app/app.service';
import { ContraentiModelComponent } from '../contraenti-model/contraenti-model.component';
import { Contraente, Posto1 } from 'src/app/app-state/models';
import { Posto } from 'src/app/app-state/models';
import { PostiModelComponent } from '../posti-model/posti-model.component';
import { each } from 'jquery';
import { CercacontraentiModelComponent } from '../cercacontraenti-model/cercacontraenti-model.component';

@Component({
  selector: 'app-domanda-full',
  templateUrl: './domanda-full.component.html',
  styleUrls: ['./domanda-full.component.css']
})
export class DomandaFullComponent  {

  @ViewChild(ContraentiModelComponent) child: ContraentiModelComponent|undefined;
  @ViewChild(PostiModelComponent) child1: PostiModelComponent|undefined;
  @ViewChild(CercacontraentiModelComponent) child2: CercacontraentiModelComponent|undefined;

  constructor(private appService: AppService) {}

  domandaFullForm = new FormGroup({
    nome: new FormControl('', Validators.nullValidator && Validators.required),
    cognome: new FormControl('', Validators.nullValidator && Validators.required),
    comune_nascita: new FormControl('', Validators.nullValidator && Validators.required),
    provincia_nascita: new FormControl('', Validators.nullValidator && Validators.required),
    stato_nascita: new FormControl('', Validators.nullValidator && Validators.required),
    data_nascita: new FormControl('', Validators.nullValidator && Validators.required),
    tipologia:new FormControl('', Validators.nullValidator && Validators.required),
    comune_residenza: new FormControl('', Validators.nullValidator && Validators.required),
    provincia_residenza: new FormControl('', Validators.nullValidator && Validators.required),
    via_residenza: new FormControl('', Validators.nullValidator && Validators.required),
    civico_residenza: new FormControl('', Validators.nullValidator && Validators.required),
    cap_residenza: new FormControl('', Validators.nullValidator && Validators.required),
    codice_fiscale: new FormControl('', Validators.nullValidator && Validators.required),
    email: new FormControl('', Validators.nullValidator ),
    note: new FormControl('', Validators.nullValidator ),

    protocollo: new FormControl('', Validators.nullValidator && Validators.required),
    data_protocollo: new FormControl('', Validators.nullValidator && Validators.required),

    loculo: new FormControl('', Validators.nullValidator && Validators.required),
    fornice: new FormControl('', Validators.nullValidator && Validators.required),

    nomeAss: new FormControl('', Validators.nullValidator && Validators.required),
    cognomeAss: new FormControl('', Validators.nullValidator && Validators.required),

    comune_decesso: new FormControl('', Validators.nullValidator ),
    data_decesso: new FormControl('', Validators.nullValidator ),


  });



  destroy$: Subject<boolean> = new Subject<boolean>();


  addDomanda() {
    this.appService.addDomandaFull(this.domandaFullForm.value).pipe(takeUntil(this.destroy$)).subscribe(data => {
      console.log('message::::', data);
      
    });
  }
  creaContraente(){
    this.child?.showContraentiModal();
  }

  cercaPosto(){
    this.child1?.showPostiModal();
  }

  cercaContraente(){
    this.child2?.showCercacontraentiModal();
  }
  
  saveContraenteWatcher(contraente: Contraente){
    this.domandaFullForm.controls['nome'].setValue((contraente.nome).toString());
    this.domandaFullForm.controls['cognome'].setValue((contraente.cognome).toString());
    this.domandaFullForm.controls['comune_nascita'].setValue(contraente.comune_nascita.toString());
    this.domandaFullForm.controls['provincia_nascita'].setValue(contraente.provincia_nascita.toString());
    this.domandaFullForm.controls['stato_nascita'].setValue(contraente.stato_nascita.toString());
    this.domandaFullForm.controls['data_nascita'].setValue(contraente.data_nascita.toString());
    this.domandaFullForm.controls['comune_residenza'].setValue(contraente.comune_residenza.toString());
    this.domandaFullForm.controls['provincia_residenza'].setValue(contraente.provincia_residenza.toString());
    this.domandaFullForm.controls['via_residenza'].setValue(contraente.via_residenza.toString());
    this.domandaFullForm.controls['civico_residenza'].setValue(contraente.civico_residenza.toString());
    this.domandaFullForm.controls['cap_residenza'].setValue(contraente.cap_residenza.toString());
    this.domandaFullForm.controls['codice_fiscale'].setValue(contraente.codice_fiscale.toString());
    this.domandaFullForm.controls['email'].setValue(contraente.email.toString());
    this.domandaFullForm.controls['note'].setValue(contraente.note.toString());
  }

  savePostoWatcher(posto: Posto1){
    this.domandaFullForm.controls['loculo'].setValue(posto.loculo);
    this.domandaFullForm.controls['fornice'].setValue(posto.fornice);
    this.domandaFullForm.controls['nomeA'].setValue(posto.nome);
    this.domandaFullForm.controls['cognomeA'].setValue(posto.cognome);
    
  }

  assegnaPostoWatcher(posti: any[]){

    let loculo;
    let fornice;
    let nome;
    let cognome;

    posti.forEach(function (value) {
      if(value.checked){

        loculo = value.loculo;
        fornice = value.fornice;
        nome = value.nome;
        cognome = value.cognome;
       

      }
    });
    this.domandaFullForm.controls['loculo'].setValue(loculo);
    this.domandaFullForm.controls['fornice'].setValue(fornice);
    this.domandaFullForm.controls['nomeAss'].setValue(nome);
    this.domandaFullForm.controls['cognomeAss'].setValue(cognome);
  
    
  }

  assegnaContraenteWatcher(contraenti: any[]){

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

 

    contraenti.forEach(function (value) {
      if(value.checked){


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

      }
    });
    this.domandaFullForm.controls['nome'].setValue(nome);
    this.domandaFullForm.controls['cognome'].setValue(cognome);
    this.domandaFullForm.controls['comune_nascita'].setValue(comune_nascita);
    this.domandaFullForm.controls['provincia_nascita'].setValue(provincia_nascita);
    this.domandaFullForm.controls['stato_nascita'].setValue(stato_nascita);
    this.domandaFullForm.controls['data_nascita'].setValue(data_nascita);
    this.domandaFullForm.controls['comune_residenza'].setValue(comune_residenza);
    this.domandaFullForm.controls['provincia_residenza'].setValue(provincia_residenza);
    this.domandaFullForm.controls['via_residenza'].setValue(via_residenza);
    this.domandaFullForm.controls['civico_residenza'].setValue(civico_residenza);
    this.domandaFullForm.controls['cap_residenza'].setValue(cap_residenza);
    this.domandaFullForm.controls['codice_fiscale'].setValue(codice_fiscale);
    this.domandaFullForm.controls['email'].setValue(email);
    this.domandaFullForm.controls['note'].setValue(note);

  
    
  }

}
