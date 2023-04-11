import { Component, ContentChild, OnInit, ViewChild } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { Subject } from 'rxjs';
import { takeUntil } from 'rxjs/operators';
import { AppService } from 'src/app/app.service';
import { ContraentiModelComponent } from '../contraenti-model/contraenti-model.component';
import { Contraente } from 'src/app/app-state/models';

@Component({
  selector: 'app-domanda-full',
  templateUrl: './domanda-full.component.html',
  styleUrls: ['./domanda-full.component.css']
})
export class DomandaFullComponent implements OnInit {

  @ViewChild(ContraentiModelComponent) child: ContraentiModelComponent|undefined;
  constructor(private appService: AppService) {}

  domandaFullForm = new FormGroup({
    protocollo: new FormControl('', Validators.nullValidator && Validators.required),
    data_protocollo: new FormControl('', Validators.nullValidator && Validators.required),
    stato: new FormControl('', Validators.nullValidator && Validators.required),
    fk_posto: new FormControl('', Validators.nullValidator && Validators.required),
    fk_contraente: new FormControl('', Validators.nullValidator && Validators.required),
    nomeAss: new FormControl('', Validators.nullValidator && Validators.required),
    cognomeAss: new FormControl('', Validators.nullValidator && Validators.required),
    comuneAss: new FormControl('', Validators.nullValidator && Validators.required),
    provAss: new FormControl('', Validators.nullValidator && Validators.required),
    dataDecesso: new FormControl('', Validators.nullValidator && Validators.required),
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
  
  saveContraenteWatcher(contraente: Contraente){
    this.domandaFullForm.controls['fk_contraente'].setValue(contraente.id);
  }

}
