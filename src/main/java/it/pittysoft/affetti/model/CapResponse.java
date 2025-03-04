package it.pittysoft.affetti.model;

import java.util.ArrayList;
import java.util.List;

import lombok.Data;

@Data
public class CapResponse extends Response{
	List<String> listaCap = new ArrayList<>();

}
