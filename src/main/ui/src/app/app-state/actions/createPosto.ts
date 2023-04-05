import { createAction, props } from '@ngrx/store';
import { Posto } from '../models';

export const CREATE_POSTO = '[CREATE POSTO] Create Posto API ';
export const CREATE_POSTO_SUCCESS = '[CREATE POSTO] Create Posto API Success';
export const CREATE_POSTO_FAILURE = '[CREATE POSTO] Create Posto API Failure';

export const createPosto = createAction(
  CREATE_POSTO,
  props<Posto>()
);

export const createPostoSuccess = createAction(
  CREATE_POSTO,
  props<Posto>()
);

export const createPostoFailure = createAction(
  CREATE_POSTO,
  props<Posto>()
);


