import { createAction, props } from '@ngrx/store';
import { Contratto } from '../models';

export const CREATE_CONTRATTO = '[CREATE CONTRATTO] Create CONTRATTO API ';
export const CREATE_CONTRATTO_SUCCESS = '[CREATE CONTRATTO] Create CONTRATTO API Success';
export const CREATE_CONTRATTO_FAILURE = '[CREATE CONTRATTO] Create CONTRATTO API Failure';

export const createContratto = createAction(
  CREATE_CONTRATTO,
  props<Contratto>()
);

export const createContrattoSuccess = createAction(
  CREATE_CONTRATTO,
  props<Contratto>()
);

export const createContrattoFailure = createAction(
  CREATE_CONTRATTO,
  props<Contratto>()
);

