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
    this.domandaFullForm.controls['scadenza'].setValue(posto.scadenza);
    
  }

  assegnaPostoWatcher(posti: any[]){

    let loculo;
    let fornice;
    let nome;
    let cognome;
    let scadenza;

    posti.forEach(function (value) {
      if(value.checked){

        loculo = value.loculo;
        fornice = value.fornice;
        nome = value.nome;
        cognome = value.cognome;
        scadenza = value.scadenza;

      }
    });
    this.domandaFullForm.controls['loculo'].setValue(loculo);
    this.domandaFullForm.controls['fornice'].setValue(fornice);
    this.domandaFullForm.controls['nomeAss'].setValue(nome);
    this.domandaFullForm.controls['cognomeAss'].setValue(cognome);
    this.domandaFullForm.controls['scadenza'].setValue(scadenza);
  
    
  }

}
