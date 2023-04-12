import { Component, ContentChild, OnInit, ViewChild } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { Subject } from 'rxjs';
import { takeUntil } from 'rxjs/operators';
import { AppService } from 'src/app/app.service';
import { ContraentiModelComponent } from '../contraenti-model/contraenti-model.component';
import { Contraente } from 'src/app/app-state/models';
import { Posto } from 'src/app/app-state/models';
import { PostiModelComponent } from '../posti-model/posti-model.component';

@Component({
  selector: 'app-domanda-full',
  templateUrl: './domanda-full.component.html',
  styleUrls: ['./domanda-full.component.css']
})
export class DomandaFullComponent implements OnInit {

  @ViewChild(ContraentiModelComponent) child: ContraentiModelComponent|undefined;
  
  @ViewChild(PostiModelComponent) child1: PostiModelComponent|undefined;
  
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

    luculo: new FormControl('', Validators.nullValidator && Validators.required),
    fornice: new FormControl('', Validators.nullValidator && Validators.required),

    nomeAss: new FormControl('', Validators.nullValidator && Validators.required),
    cognomeAss: new FormControl('', Validators.nullValidator && Validators.required),

    comune_decesso: new FormControl('', Validators.nullValidator ),
    data_decesso: new FormControl('', Validators.nullValidator ),
  });

  ngOnInit(): void {
  }

  destroy$: Subject<boolean> = new Subject<boolean>();


  onSubmit() {
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
  
  saveContraenteWatcher(contraente: Contraente){
    this.domandaFullForm.controls['nome'].setValue(contraente.nome);
    this.domandaFullForm.controls['cognome'].setValue(contraente.cognome);
    this.domandaFullForm.controls['comune_nascita'].setValue(contraente.comune_nascita);
    this.domandaFullForm.controls['provincia_nascita'].setValue(contraente.provincia_nascita);
    this.domandaFullForm.controls['stato_nascita'].setValue(contraente.stato_nascita);
    this.domandaFullForm.controls['data_nascita'].setValue(contraente.data_nascita);
    this.domandaFullForm.controls['comune_residenza'].setValue(contraente.comune_residenza);
    this.domandaFullForm.controls['provincia_residenza'].setValue(contraente.provincia_residenza);
    this.domandaFullForm.controls['via_residenza'].setValue(contraente.via_residenza);
    this.domandaFullForm.controls['civico_residenza'].setValue(contraente.civico_residenza);
    this.domandaFullForm.controls['cap_residenza'].setValue(contraente.cap_residenza);
    this.domandaFullForm.controls['codice_fiscale'].setValue(contraente.codice_fiscale);
    this.domandaFullForm.controls['email'].setValue(contraente.email);
    this.domandaFullForm.controls['note'].setValue(contraente.note);
  }

  savePostoWatcher(posto: Posto){
    this.domandaFullForm.controls['loculo'].setValue(posto.loculo);
    this.domandaFullForm.controls['fornice'].setValue(posto.fornice);
    
  }

}
