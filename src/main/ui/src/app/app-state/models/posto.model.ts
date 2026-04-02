import { Cimitero } from "./cimitero.model";
import { Area } from "./area.model";

export class Posto {

  id: number;
  loculo: string;
  fornice: string;
  tipo: string;
  stato: string;
  data_update: Date;
  data_insert: Date;
  fk_user_modifier: number;
  fk_cimitero?: number;
  cimitero?: Cimitero;
  fk_area?: number;
  area?: Area;
  latitudine?: number;
  longitudine?: number;

}
