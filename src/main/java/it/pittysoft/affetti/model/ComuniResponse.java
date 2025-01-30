package it.pittysoft.affetti.model;

import java.util.ArrayList;
import java.util.List;

import lombok.Data;

@Data
public class ComuniResponse extends Response {
	List<ComuniModel> comuni = new ArrayList<>();

}
