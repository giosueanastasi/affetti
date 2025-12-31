package it.pittysoft.affetti.model;

import org.springframework.data.domain.Page;

import lombok.Data;

@Data
public class ContrattoSearchResponse extends Response{
	Page<ContrattoModel> contratti;
}
