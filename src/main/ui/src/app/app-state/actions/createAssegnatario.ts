import { createAction, props } from '@ngrx/store';
import { Assegnatario } from '../models';

export const CREATE_ASSEGNATARIO = '[CREATE ASSEGNATARIO] Create ASSEGNATARIO API ';
export const CREATE_ASSEGNATARIO_SUCCESS = '[CREATE ASSEGNATARIO] Create ASSEGNATARIO API Success';
export const CREATE_ASSEGNATARIO_FAILURE = '[CREATE ASSEGNATARIO] Create ASSEGNATARIO API Failure';

export const createAssegnatario = createAction(
  CREATE_ASSEGNATARIO,
  props<Assegnatario>()
);

export const createAssegnatarioSuccess = createAction(
  CREATE_ASSEGNATARIO,
  props<Assegnatario>()
);

export const createAssegnatarioFailure = createAction(
  CREATE_ASSEGNATARIO,
  props<Assegnatario>()
);

