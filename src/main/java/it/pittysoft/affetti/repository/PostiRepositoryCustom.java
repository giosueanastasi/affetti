package it.pittysoft.affetti.repository;

import java.util.List;

import it.pittysoft.affetti.entity.Posti;

public interface PostiRepositoryCustom {
	List<Posti> findtPostiByLoculoAndFornice(Posti posti);

}
