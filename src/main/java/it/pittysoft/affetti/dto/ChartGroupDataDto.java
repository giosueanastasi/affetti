package it.pittysoft.affetti.dto;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ChartGroupDataDto {
	private String name;
	private List<ChartSeriesItemDto> series;
}
