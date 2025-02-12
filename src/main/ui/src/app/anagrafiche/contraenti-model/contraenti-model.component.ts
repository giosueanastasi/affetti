import { Component, Output, EventEmitter } from '@angular/core';
import { Comune, Contraente } from 'src/app/app-state/models';
import { AppService } from 'src/app/app.service';
import { takeUntil } from 'rxjs/operators';
import { Observable, Subject } from 'rxjs';
import { FormControl } from '@angular/forms';
import {startWith, map} from 'rxjs/operators';

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
  constructor(private appService: AppService) { }
  comuni: any[] = [];
  comuniNascitaFiltrati: Observable<string[]>;
  comuniResidenzaFiltrati: Observable<string[]>;
  destroy$: Subject<boolean> = new Subject<boolean>();
  comuneRes: Comune = new Comune();//Comune di residenza


  saveContraente() {
    debugger;
    this.appService.saveContraente(this.contraente).pipe().subscribe(data => {
      this.save.emit(data);
      $('#contraentiModal').modal('hide');
    }, error => {
      this.errorMessage = "Errore imprevisto, contattare l'assistenza";
      console.log(error);
    });
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
  }

  //Funzione che filtra la lista dei comuni in base ad una stringa in data in input
  private comuniFilter(value: string): string[] {
    const valoreFiltro =  this.normalizeValue(value);
    return this.comuni.filter(comune => this.normalizeValue(comune.nome).includes(valoreFiltro));
   }

  //Funzione per normalizzare i valori di tipo string
  private normalizeValue(value: string): string {
    return value.toLowerCase().replace(/\s/g, '');
  }

}
