import { Cimitero } from "./cimitero.model";

export class Area {
  id: number;
  cimitero?: Cimitero;
  fk_cimitero?: number;
  codice: string;
  nome?: string;
  descrizione?: string;
  latitudine?: number;
  longitudine?: number;
  attiva?: boolean;
  data_insert?: Date;
  data_update?: Date;
  fk_user_modifier?: number;
}
