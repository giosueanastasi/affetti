import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';


@Injectable({
  providedIn: 'root'
})
export class AppService {


  constructor(private http: HttpClient) { }

  rootURL = '/api';

  getUsers() {
    return this.http.get(this.rootURL + '/users');
  }

  addUser(user: any, id: number) {
	user.id = id;
	return this.http.post(this.rootURL + '/user', user);
  }

  getPosti() {
    return this.http.get(this.rootURL + '/posti');
  }

  addPosto(posto: any, id: number) {
	posto.id = id;
	return this.http.post(this.rootURL + '/posto', posto);
  }

  getComuni() {
    return this.http.get(this.rootURL + '/comuni');
  }

  addComune(comune: any, id: number) {
	comune.id = id;
	return this.http.post(this.rootURL + '/comune', comune);
  }

  getContraenti() {
    return this.http.get(this.rootURL + '/contraenti');
  }

  addContraente(contraente: any, id: number) {
    contraente.id = id;
    return this.http.post(this.rootURL + '/contraente', contraente);
  }

  getAssegnatari() {
    return this.http.get(this.rootURL + '/assegnatari');
  }

  addAssegnatario(assegnatario: any, id: number) {
    assegnatario.id = id;
	return this.http.post(this.rootURL + '/assegnatario', assegnatario);
  }

  getContratti() {
    return this.http.get(this.rootURL + '/contratti');
  }

  addContratto(contratto: any, id: number) {
    contratto.id = id;
	return this.http.post(this.rootURL + '/contratto', contratto);
  }
  
  getDomande() {
    return this.http.get(this.rootURL + '/domande');
  }

  addDomanda(domanda: any, id: number) {
    domanda.id = id;
	return this.http.post(this.rootURL + '/domanda', domanda);
  }

}
