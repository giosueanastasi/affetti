import * as createPostoActions from '../actions/createPosto';
import { Action, createReducer, on } from '@ngrx/store';

export interface State {
    isLoading: boolean;
    isLoadingSuccess: boolean;
    posti: any;
}

const initialState: State = {
    isLoading: false,
    isLoadingSuccess: false,
    posti: []
};

export const createPostoReducer = createReducer(
  initialState,
  on(createPostoActions.createPosto, state => ({...state, isLoading: true, isLoadingSuccess: false, login: undefined})),
  on(createPostoActions.createPostoSuccess, (state, posto) => ({...state, isLoading: false, isLoadingSuccess: true, posto})),
  on(createPostoActions.createPostoFailure, (state, posto) => ({...state, isLoading: false, isLoadingSuccess: true, posto}))
);

export function reducer(state: State | undefined, action: Action) {
  return createPostoReducer(state, action);
}

export const getPosti = (state: State) => {
    return {
      isLoading: state.isLoading,
      isLoadingSuccess: state.isLoadingSuccess,
      login: state.posti };
};
