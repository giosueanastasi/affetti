import { Injectable } from "@angular/core";

@Injectable({providedIn: 'root'})
export class UtilsService{

    //Funzione per normalizzare i valori di tipo string
     normalizeValue(value: string): string {
    return value.toLowerCase().replace(/\s/g, '');
  }
}