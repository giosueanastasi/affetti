import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { DomandaFull } from './app-state/models/domandaFull.model';
import { Domanda, Posto1 } from './app-state/models';
import { DomandaSearch } from './app-state/models/domandaSearch.model';
import { ContrattiSearch } from './app-state/models/contrattiSearch.model';



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


  cercaUser(user: any) {
    return this.http.post(this.rootURL + '/search_user', user);
  }

  cercaPosti(posti1: any) {
    return this.http.post(this.rootURL + '/search_posti',posti1);
  }

  cercaCercacontraenti(contraenti1: any) {
    return this.http.post(this.rootURL + '/search_contraenti',contraenti1);
  }

  getPosti() {
    return this.http.get(this.rootURL + '/posti');
  }

  addPosto(posto: any, id: number) {
	posto.id = id;
  debugger;
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
    debugger;
    return this.http.post(this.rootURL + '/contraente', contraente);
  }

  saveUser(user: any) {
    return this.http.post(this.rootURL + '/user', user);
  }

  saveContraente(contraente: any) {
    return this.http.post(this.rootURL + '/contraente', contraente);
  }

  savePosto(posto1: any) {
    return this.http.post(this.rootURL + '/posto1', posto1);
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

  saveContratto(contratto: any) {
    return this.http.post(this.rootURL + '/contratto', contratto);
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

  addDomandaFull(domandaFullForm: any) {
    let domanda = new Domanda;
    domanda.id = domandaFullForm.id;
    domanda.protocollo = domandaFullForm.protocollo;
    domanda.data_protocollo = domandaFullForm.data_protocollo;
    domanda.stato = 'APERTA';
    domanda.tipologia = domandaFullForm.tipologia;
    domanda.fk_posto = domandaFullForm.fk_posto;
   

    let  df = new DomandaFull();
    df.domanda = domanda;
    df.nomeAss = domandaFullForm.nomeAss;
    df.cognomeAss = domandaFullForm.cognomeAss;
    df.comuneAss = domandaFullForm.comuneAss;
    df.provAss = domandaFullForm.provAss;
    df.dataDecesso = domandaFullForm.data_decesso;
    df.loculo = domandaFullForm.loculo;
    df.fornice = domandaFullForm.fornice;
    df.fkContraente = domandaFullForm.fk_contraente;

	return this.http.post(this.rootURL + '/domandaFull', df);
  }


  cercaDomandeService(domandaForm: any) {
    let  ds = new DomandaSearch();
    ds.nome = domandaForm.nome;
    ds.cognome = domandaForm.cognome;
    ds.codiceFiscale = domandaForm.codice_fiscale;
    ds.dataProtocolloFinale = domandaForm.data_protocollo_finale;
    ds.dataProtocolloIniziale = domandaForm.data_protocollo_iniziale;
    ds.tipologia = domandaForm.tipologia;
    ds.numeroProtocollo = domandaForm.numero_protocollo;
    ds.stato = domandaForm.stato;
	  return this.http.post(this.rootURL + '/search_domande', ds);
  }
  cercaContrattiService(contrattoForm: any) {
    let  ds = new ContrattiSearch();
    ds.nome = contrattoForm.nome;
    ds.cognome = contrattoForm.cognome;
    ds.codiceFiscale = contrattoForm.codice_fiscale;
    ds.dataProtocolloFinale = contrattoForm.data_protocollo_finale;
    ds.dataProtocolloIniziale = contrattoForm.data_protocollo_iniziale;
    ds.tipologia = contrattoForm.tipologia;
    ds.numeroProtocollo = contrattoForm.numero_protocollo;
    ds.stato = contrattoForm.stato;
	  return this.http.post(this.rootURL + '/search_contratti', ds);
  }
  
}