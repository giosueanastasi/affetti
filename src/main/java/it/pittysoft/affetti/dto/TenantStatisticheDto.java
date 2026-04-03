package it.pittysoft.affetti.dto;

import java.util.List;

import lombok.Data;

@Data
public class TenantStatisticheDto {
	// KPI
	private long totaleDefunti;
	private long postiLiberi;
	private long postiTotali;
	private long domandeAperte;
	private long contrattiInScadenza;

	// Distribuzioni
	private List<ChartDataDto> postiPerStato;
	private List<ChartDataDto> postiPerTipoSepoltura;
	private List<ChartDataDto> domandePerStato;
	private List<ChartDataDto> sepolturePerTipoOperazione;

	// Trend temporali
	private List<ChartGroupDataDto> decessiPerMese;
	private List<ChartGroupDataDto> domandePerMese;
	private List<ChartGroupDataDto> contrattiPerMese;

	// Confronti
	private List<ChartGroupDataDto> capienzaVsOccupazione;
	private List<ChartGroupDataDto> postiPerStatoPerCimitero;
}
