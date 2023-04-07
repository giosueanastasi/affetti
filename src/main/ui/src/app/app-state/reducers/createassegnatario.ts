import * as createAssegnatarioActions from '../actions/createAssegnatario';
import { Action, createReducer, on } from '@ngrx/store';

export interface State {
    isLoading: boolean;
    isLoadingSuccess: boolean;
    assegnatari: any;
}

const initialState: State = {
    isLoading: false,
    isLoadingSuccess: false,
    assegnatari: []
};

export const createAssegnatarioReducer = createReducer(
  initialState,
  on(createAssegnatarioActions.createAssegnatario, state => ({...state, isLoading: true, isLoadingSuccess: false, login: undefined})),
  on(createAssegnatarioActions.createAssegnatarioSuccess, (state, assegnatario) => ({...state, isLoading: false, isLoadingSuccess: true, assegnatario})),
  on(createAssegnatarioActions.createAssegnatarioFailure, (state, assegnatario) => ({...state, isLoading: false, isLoadingSuccess: true, assegnatario}))
);

export function reducer(state: State | undefined, action: Action) {
  return createAssegnatarioReducer(state, action);
}

export const getAssegnatari = (state: State) => {
    return {
      isLoading: state.isLoading,
      isLoadingSuccess: state.isLoadingSuccess,
      login: state.assegnatari };
};
