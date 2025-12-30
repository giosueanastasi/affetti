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
  currentPage: number = 0;
  pageSize: number = 10;
  fetchSize: number = 11;
  hasMore: boolean = true;

  constructor(private appService: AppService, private router: Router){ };

  ngOnInit(): void {
      this.ricerca.valueChanges.pipe(
        takeUntil(this.destroy$),
        debounceTime(500),
        switchMap(ricerca => {
          if(ricerca.trim()) {
            this.ricercaAvviata = true;
            this.currentPage = 0;
            this.defunti = [];
            return this.appService.cercaDefunti(ricerca.trim(), this.currentPage, this.fetchSize);
          } else {
            this.ricercaAvviata = false;
            this.defunti = [];
            this.hasMore = true;
            return [];
          }
        }),
      ).subscribe((data: Defunto[]) => {
        this.hasMore = data.length > this.pageSize;
        this.defunti = data.slice(0, this.pageSize);
      })
  }

  caricaAltri(): void {
    this.currentPage++;
    const ricercaCorrente = this.ricerca.value?.trim();
    if (ricercaCorrente) {
      this.appService.cercaDefunti(ricercaCorrente, this.currentPage, this.fetchSize)
        .subscribe((data: Defunto[]) => {
          this.hasMore = data.length > this.pageSize;
          const nuoviDefunti = data.slice(0, this.pageSize);
          this.defunti = [...this.defunti, ...nuoviDefunti];
        });
    }
  }

  dettaglio(defuntoId: number): void {
    this.router.navigate(['/defunti', defuntoId])
  }


}
