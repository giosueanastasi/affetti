package it.pittysoft.affetti.model;

import java.util.ArrayList;
import java.util.List;

import lombok.Data;

@Data
public class DomandaResponseSearch extends Response{
	List<PostiModel> domande = new ArrayList<>();
}
