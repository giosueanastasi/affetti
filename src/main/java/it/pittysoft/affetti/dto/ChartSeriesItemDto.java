package it.pittysoft.affetti.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ChartSeriesItemDto {
	private String name;
	private long value;
}
