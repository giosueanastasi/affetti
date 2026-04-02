import { Cimitero } from "./cimitero.model";
import { Area } from "./area.model";

export class Posto1 {
  id: string;
  stato: string;
  loculo: string;
  fornice: string;
  tipo?: string;
  fk_cimitero?: number;
  cimitero?: Cimitero;
  fk_area?: number;
  area?: Area;
  nome: string;
  cognome: string;

  data_scadenza: string;
  data_inizio: string;
  checked: boolean;
  latitudine?: number;
  longitudine?: number;
}
