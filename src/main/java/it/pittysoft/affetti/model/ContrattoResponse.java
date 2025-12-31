package it.pittysoft.affetti.model;

import java.util.ArrayList;
import java.util.List;

import lombok.Data;

@Data
public class ContrattoResponse extends Response{
	List<ContrattoModel> contratti = new ArrayList<>();
}
