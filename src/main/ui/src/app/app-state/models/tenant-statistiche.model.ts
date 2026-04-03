export interface ChartData {
  name: string;
  value: number;
}

export interface ChartGroupData {
  name: string;
  series: ChartData[];
}

export interface TenantStatistiche {
  totaleDefunti: number;
  postiLiberi: number;
  postiTotali: number;
  domandeAperte: number;
  contrattiInScadenza: number;

  postiPerStato: ChartData[];
  postiPerTipoSepoltura: ChartData[];
  domandePerStato: ChartData[];
  sepolturePerTipoOperazione: ChartData[];

  decessiPerMese: ChartGroupData[];
  domandePerMese: ChartGroupData[];
  contrattiPerMese: ChartGroupData[];

  capienzaVsOccupazione: ChartGroupData[];
  postiPerStatoPerCimitero: ChartGroupData[];
}
