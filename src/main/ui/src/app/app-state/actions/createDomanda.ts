import { createAction, props } from '@ngrx/store';
import { Domanda } from '../models';

export const CREATE_DOMANDA = '[CREATE DOMANDA] Create Domanda API ';
export const CREATE_DOMANDA_SUCCESS = '[CREATE DOMANDA] Create Domanda API Success';
export const CREATE_DOMANDA_FAILURE = '[CREATE DOMANDA] Create Domanda API Failure';

export const createDomanda = createAction(
  CREATE_DOMANDA,
  props<Domanda>()
);

export const createDomandaSuccess = createAction(
  CREATE_DOMANDA,
  props<Domanda>()
);

export const createDomandaFailure = createAction(
  CREATE_DOMANDA,
  props<Domanda>()
);


