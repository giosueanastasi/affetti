package it.pittysoft.affetti.model;

import java.util.ArrayList;
import java.util.List;

import lombok.Data;

@Data
public class ComuniSelectResponse extends Response {
	List<ComuniSelectModel> comuni = new ArrayList<>();

}
