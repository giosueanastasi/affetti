import { Component, Output, EventEmitter } from '@angular/core';
import { type Comune, Contraente } from 'src/app/app-state/models';
import { AppService } from 'src/app/app.service';
import { takeUntil } from 'rxjs/operators';
import { Observable, Subject } from 'rxjs';
import { FormControl } from '@angular/forms';
import {startWith, map} from 'rxjs/operators';
import { Utils } from 'src/app/app-state/shared/utils';
declare var $ : any;

@Component({
  selector: 'app-contraenti-model',
  templateUrl: './contraenti-model.component.html',
  styleUrls: ['./contraenti-model.component.css'],
})
export class ContraentiModelComponent  {

  contraente: Contraente = new Contraente();
  errorMessage: string = "";

  @Output() save =  new EventEmitter<any>();

  comuneNascitaControl= new FormControl('');
  comuneResidenzaControl= new FormControl('');

  constructor(private appService: AppService, private utils: Utils) { };

  comuni: Comune[] = [];
  comuniNascitaFiltrati: Observable<Comune[]>;
  comuniResidenzaFiltrati: Observable<Comune[]>;
  listaCap: any[] = [];
  comuneRes: Comune;//Comune di residenza


  destroy$: Subject<boolean> = new Subject<boolean>();


  saveContraente() {
    debugger;
    //Il blocco di istruzioni per salvare i dati del contraente viene eseguito solo se 
    //i valori anagrafici inseriti sono validi
    if(this.controllaSelezioniComuni()) {
      this.appService.saveContraente(this.contraente).pipe().subscribe(data => {
        this.save.emit(data);
        $('#contraentiModal').modal('hide');
      }, error => {
        this.errorMessage = "Errore imprevisto, contattare l'assistenza";
        console.log(error);
      });
    }
  }
  
  showContraentiModal(){
    $('#contraentiModal').modal('show');
    this.getAllComuni();
  }

  //Funzione per recuperare la lista con tutti i comuni
  getAllComuni(){
    this.appService.getComuni().pipe(takeUntil(this.destroy$)).subscribe((data: any) => {
              this.comuni = data.comuni;
          });
    
  }

  ngOnInit() {
    //Inizializzazione degli array che andranno a popolare i campi relativi ai comuni di nascita e residenza
      this.comuniNascitaFiltrati =  this.comuneNascitaControl.valueChanges.pipe(
        startWith(''),
        map(value => this.comuniFilter(value || '')),
      );
      this.comuniResidenzaFiltrati =  this.comuneResidenzaControl.valueChanges.pipe(
        startWith(''),
        map(value => this.comuniFilter(value || '')),
      );

    }

 
  //Funzione che assegna automaticamente i valori relativi alla provincia e allo stato in base al comune di nascita selezionato
  onComuneNascSelect(comuneNascitaSelezionato: Comune){
    this.contraente.comune_nascita = comuneNascitaSelezionato.nome;
    this.contraente.provincia_nascita = comuneNascitaSelezionato.provincia;
    this.contraente.stato_nascita = comuneNascitaSelezionato.stato;

  }

  //Funzione che assegna automaticamente il valore della provincia in base al comune di residenza selezionato
  onComuneResSelect(comuneResidenzaSelezionato: Comune){
    this.contraente.comune_residenza = comuneResidenzaSelezionato.nome;
    this.contraente.provincia_residenza = comuneResidenzaSelezionato.provincia;
    this.appService.getCapListByComune(comuneResidenzaSelezionato.id).pipe(takeUntil(this.destroy$)).subscribe((data: any) => {
      this.listaCap = data.listaCap;
    });



  }

  //Funzione che filtra la lista dei comuni in base ad una stringa data in input
  private comuniFilter(value: string): Comune[] {
    const valoreFiltro =  this.utils.normalizeValue(value);
    return this.comuni.filter(comune => this.utils.normalizeValue(comune.nome).includes(valoreFiltro));
   }

   //Funzione che controlla se i valori nei campi relativi ai comuni sono stati 
   private controllaSelezioniComuni(): boolean{
    let valoriComuniValidi = true;
     let nomiComuni = this.comuni.map(nomeComune => nomeComune.nome);
     if(!nomiComuni.includes(this.contraente.comune_nascita.toString())){
       this.contraente.comune_nascita = null;
       this.contraente.provincia_nascita = null;
       this.contraente.stato_nascita = null;
       this.errorMessage = "Scegliere il comune dalla lista";
       valoriComuniValidi = false;
     }
     if(!nomiComuni.includes(this.contraente.comune_residenza.toString())){
       this.contraente.comune_residenza = null;
       this.contraente.provincia_residenza;
       this.listaCap = null;
       this.errorMessage = "Scegliere il comune dalla lista";
       valoriComuniValidi = false;
     }

     return valoriComuniValidi;
   }
  


}
