import * as createDomandeActions from '../actions/createDomanda';
import { Action, createReducer, on } from '@ngrx/store';

export interface State {
    isLoading: boolean;
    isLoadingSuccess: boolean;
    domande: any;
}

const initialState: State = {
    isLoading: false,
    isLoadingSuccess: false,
    domande: []
};

export const createDomandaReducer = createReducer(
  initialState,
  on(createDomandeActions.createDomanda, state => ({...state, isLoading: true, isLoadingSuccess: false, login: undefined})),
  on(createDomandeActions.createDomandaSuccess, (state, domanda) => ({...state, isLoading: false, isLoadingSuccess: true, domanda})),
  on(createDomandeActions.createDomandaFailure, (state, domanda) => ({...state, isLoading: false, isLoadingSuccess: true, domanda}))
);

export function reducer(state: State | undefined, action: Action) {
  return createDomandaReducer(state, action);
}

export const getDomande = (state: State) => {
    return {
      isLoading: state.isLoading,
      isLoadingSuccess: state.isLoadingSuccess,
      login: state.domande };
};
