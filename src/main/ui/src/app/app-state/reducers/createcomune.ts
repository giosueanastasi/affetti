import * as createComuneActions from '../actions/createComune';
import { Action, createReducer, on } from '@ngrx/store';

export interface State {
    isLoading: boolean;
    isLoadingSuccess: boolean;
    comuni: any;
}

const initialState: State = {
    isLoading: false,
    isLoadingSuccess: false,
    comuni: []
};

export const createComuneReducer = createReducer(
  initialState,
  on(createComuneActions.createComune, state => ({...state, isLoading: true, isLoadingSuccess: false, login: undefined})),
  on(createComuneActions.createComuneSuccess, (state, comune) => ({...state, isLoading: false, isLoadingSuccess: true, comune})),
  on(createComuneActions.createComuneFailure, (state, comune) => ({...state, isLoading: false, isLoadingSuccess: true, comune}))
);

export function reducer(state: State | undefined, action: Action) {
  return createComuneReducer(state, action);
}

export const getComuni = (state: State) => {
    return {
      isLoading: state.isLoading,
      isLoadingSuccess: state.isLoadingSuccess,
      login: state.comuni };
};
