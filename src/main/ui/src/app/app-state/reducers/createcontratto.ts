import * as createContrattoActions from '../actions/createContratto';
import { Action, createReducer, on } from '@ngrx/store';

export interface State {
    isLoading: boolean;
    isLoadingSuccess: boolean;
    contratti: any;
}

const initialState: State = {
    isLoading: false,
    isLoadingSuccess: false,
    contratti: []
};

export const createContrattoReducer = createReducer(
  initialState,
  on(createContrattoActions.createContratto, state => ({...state, isLoading: true, isLoadingSuccess: false, login: undefined})),
  on(createContrattoActions.createContrattoSuccess, (state, contratto) => ({...state, isLoading: false, isLoadingSuccess: true, contratto})),
  on(createContrattoActions.createContrattoFailure, (state, contratto) => ({...state, isLoading: false, isLoadingSuccess: true, contratto}))
);

export function reducer(state: State | undefined, action: Action) {
  return createContrattoReducer(state, action);
}

export const getContratti = (state: State) => {
    return {
      isLoading: state.isLoading,
      isLoadingSuccess: state.isLoadingSuccess,
      login: state.contratti };
};
