import { Component, OnInit } from '@angular/core';
import { FormControl } from '@angular/forms';
import { Router } from '@angular/router';
import { debounce, debounceTime, Subject, switchMap, takeUntil } from 'rxjs';
import { Defunto } from 'src/app/app-state/models/defunto.model';
import { AppService } from 'src/app/app.service';

@Component({
  selector: 'app-cercadefunti',
  templateUrl: './cercadefunti.component.html',
  styleUrls: ['./cercadefunti.component.css']
})
export class CercadefuntiComponent implements OnInit{

  destroy$: Subject<boolean> = new Subject<boolean>();

  ricerca = new FormControl('');
  defunti: Defunto[] = [];
  ricercaAvviata: boolean = false;

  constructor(private appService: AppService, private router: Router){ };

  ngOnInit(): void {
      this.ricerca.valueChanges.pipe(
        takeUntil(this.destroy$),
        debounceTime(500),
        switchMap(ricerca => {
          if(ricerca.trim()) {
            this.ricercaAvviata = true;
            return this.appService.cercaDefunti(ricerca.trim());
          } else {
            this.ricercaAvviata = false;
            return [];
          }
        }),
      ).subscribe((data: Defunto[]) => {this.defunti = data;
      })
  }


  dettaglio(defuntoId: number): void { 
    this.router.navigate(['/defunti', defuntoId])
  } 


}
