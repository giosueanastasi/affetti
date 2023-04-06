import { createAction, props } from '@ngrx/store';
import { Contraente } from '../models';

export const CREATE_CONTRAENTE = '[CREATE CONTRAENTE] Create Contraente  ';
export const CREATE_CONTRAENTE_SUCCESS = '[CREATE CONTRAENTE] Create Contraente API Success';
export const CREATE_CONTRAENTE_FAILURE = '[CREATE CONTRAENTE] Create Contraente API Failure';

export const createContraente = createAction(
  CREATE_CONTRAENTE,
  props<Contraente>()
);

export const createContraenteSuccess = createAction(
  CREATE_CONTRAENTE,
  props<Contraente>()
);

export const createContraenteFailure = createAction(
  CREATE_CONTRAENTE,
  props<Contraente>()
);


