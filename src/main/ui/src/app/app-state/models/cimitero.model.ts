import { Comune } from "./comune.model";

export class Cimitero {
  id: number;
  nome: string;
  indirizzo: string;
  comune?: Comune;
  data_insert?: Date;
  data_update?: Date;
  fk_user_modifier?: number;
  latitudine?: number;
  longitudine?: number;
}
