package it.pittysoft.affetti.model;

import java.util.ArrayList;
import java.util.List;

import lombok.Data;

@Data
public class ContrattoSearchResponse extends Response{
	List<ContrattoModel> contratti = new ArrayList<>();
}
