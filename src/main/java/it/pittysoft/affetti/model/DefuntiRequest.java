package it.pittysoft.affetti.model;

import lombok.Data;

@Data
public class DefuntiRequest extends Request{

	private String ricerca;
	private Integer page = 0;
	private Integer size = 10;

}
