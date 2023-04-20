package it.pittysoft.affetti.repository;

import java.util.List;

import it.pittysoft.affetti.entity.Posti;
import it.pittysoft.affetti.model.PostiRequest;

public interface PostiRepositoryCustom {
	List<Posti> findtPostiByLoculoAndFornice(PostiRequest posti);

}
