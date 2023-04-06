import * as createContraenteActions from '../actions/createContraente';
import { Action, createReducer, on } from '@ngrx/store';

export interface State {
    isLoading: boolean;
    isLoadingSuccess: boolean;
    contraenti: any;
}

const initialState: State = {
    isLoading: false,
    isLoadingSuccess: false,
    contraenti: []
};

export const createContraenteReducer = createReducer(
  initialState,
  on(createContraenteActions.createContraente, state => ({...state, isLoading: true, isLoadingSuccess: false, login: undefined})),
  on(createContraenteActions.createContraenteSuccess, (state, contraente) => ({...state, isLoading: false, isLoadingSuccess: true, contraente})),
  on(createContraenteActions.createContraenteFailure, (state, contraente) => ({...state, isLoading: false, isLoadingSuccess: true, contraente}))
);

export function reducer(state: State | undefined, action: Action) {
  return createContraenteReducer(state, action);
}

export const getContraenti = (state: State) => {
    return {
      isLoading: state.isLoading,
      isLoadingSuccess: state.isLoadingSuccess,
      login: state.contraenti};
};
