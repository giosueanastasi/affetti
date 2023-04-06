import { createAction, props } from '@ngrx/store';
import { Comune } from '../models';

export const CREATE_COMUNE = '[CREATE COMUNE] Create Comune API ';
export const CREATE_COMUNE_SUCCESS = '[CREATE COMUNE] Create Comune API Success';
export const CREATE_COMUNE_FAILURE = '[CREATE COMUNE] Create Comune API Failure';

export const createComune = createAction(
  CREATE_COMUNE,
  props<Comune>()
);

export const createComuneSuccess = createAction(
  CREATE_COMUNE,
  props<Comune>()
);

export const createComuneFailure = createAction(
  CREATE_COMUNE,
  props<Comune>()
);


