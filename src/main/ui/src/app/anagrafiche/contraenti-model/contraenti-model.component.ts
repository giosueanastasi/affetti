import { Component, OnInit, Output, EventEmitter } from '@angular/core';
import { Comune, Contraente } from 'src/app/app-state/models';
import { AppService } from 'src/app/app.service';
import { takeUntil } from 'rxjs/operators';
import { Subject } from 'rxjs';
import { NgModel } from '@angular/forms';

declare var $ : any;

@Component({
  selector: 'app-contraenti-model',
  templateUrl: './contraenti-model.component.html',
  styleUrls: ['./contraenti-model.component.css']
})
export class ContraentiModelComponent  {

  contraente: Contraente = new Contraente();
  errorMessage: string = "";

  @Output() save =  new EventEmitter<any>();
  constructor(private appService: AppService) { }
  comuniCount = 0;
  comuni: any[] = [];
  destroy$: Subject<boolean> = new Subject<boolean>();
  comuneNasc: Comune = new Comune();//Comune di nascita
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
  getAllComuni(){
    this.appService.getComuni().pipe(takeUntil(this.destroy$)).subscribe((data: any) => {
              this.comuniCount = data.length;
              this.comuni = data.comuni;
          });
  }

  ngOnInit() {
    //this.getAllComuni();
    }

  onComuneNascSelect(nome: string){
    this.appService.getComuneByNome(nome).pipe(takeUntil(this.destroy$)).subscribe((data: any) => {
      this.comuneNasc = data.comune;
    });

    this.contraente.comune_nascita = this.comuneNasc.nome;
    this.contraente.provincia_nascita = this.comuneNasc.provincia;


  }
  onComuneResSelect(nome: string){
    this.appService.getComuneByNome(nome).pipe(takeUntil(this.destroy$)).subscribe((data: any) => {
      this.comuneRes = data.comune;
    });
    this.contraente.comune_residenza = this.comuneRes.nome;
    this.contraente.provincia_residenza = this.comuneRes.provincia;
  }

}
