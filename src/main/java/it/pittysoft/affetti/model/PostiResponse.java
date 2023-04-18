package it.pittysoft.affetti.model;

import java.util.ArrayList;
import java.util.List;

import lombok.Data;

@Data
public class PostiResponse extends Response {
	List<PostiModel> posti = new ArrayList<>();
}
