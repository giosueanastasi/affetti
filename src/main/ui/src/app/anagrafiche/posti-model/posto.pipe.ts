import { Pipe, PipeTransform } from '@angular/core';
import { Posto1 } from 'src/app/app-state/models';

@Pipe({
  name: 'SearchPostoPipe'
})
export class PostoPipe implements PipeTransform {

transform(Posto1: any[], searchValue: string): any[] {
  if (!Posto1 || !searchValue) {
    return Posto1;
  }
  return Posto1.filter( Posto1=>
    Posto1.loculo.toLocaleLowerCase().includes(searchValue.toLocaleLowerCase()));
   
  } 
  }
