package it.pittysoft.affetti.model;

import java.util.ArrayList;
import java.util.List;

import lombok.Data;

@Data
public class ContraentiResponse extends Response {
	List<ContraentiModel> contraenti = new ArrayList<>();
}
