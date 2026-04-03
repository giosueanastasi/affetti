package it.pittysoft.affetti.repository;

import java.util.List;

import javax.persistence.EntityManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.jpa.impl.JPAQuery;

import it.pittysoft.affetti.entity.Defunti;
import it.pittysoft.affetti.entity.QAree;
import it.pittysoft.affetti.entity.QCimiteri;
import it.pittysoft.affetti.entity.QDefunti;
import it.pittysoft.affetti.entity.QPosti;
import it.pittysoft.affetti.model.DefuntiRequest;

@Repository
public class DefuntiRepositoryCustomImpl implements DefuntiRepositoryCustom {
	
	@Autowired
	private EntityManager em;
	

	@Override
	public List<Defunti> findDefuntiByNameAndOrSurname(DefuntiRequest request) {
	    QDefunti qDefunti = QDefunti.defunti;
	    BooleanBuilder builder = new BooleanBuilder();

	    if (request != null && !request.getRicerca().trim().isEmpty()) {
	        String[] parts = request.getRicerca().trim().toUpperCase().split("\\s+");

	        for (String part : parts) {
	            String search = "%" + part + "%";

	            builder.and(
	                qDefunti.nome.upper().like(search)
	                .or(qDefunti.cognome.upper().like(search))
	            );
	        }
	    }

	    JPAQuery<Defunti> query = new JPAQuery<Defunti>(em)
	            .select(qDefunti)
	            .from(qDefunti)
	            .where(builder)
	            .orderBy(qDefunti.id.asc());

	    if (request.getPage() != null && request.getSize() != null) {
	        query.offset((long) request.getPage() * request.getSize())
	             .limit(request.getSize());
	    }

	    return query.fetch();
	}

	@Override
	public List<Defunti> findDefuntiByTenant(Long tenantId, Long cimiteroId, DefuntiRequest request) {
		QDefunti qDefunti = QDefunti.defunti;
		QPosti qPosti = QPosti.posti;
		QAree qAree = QAree.aree;
		QCimiteri qCimiteri = QCimiteri.cimiteri;
		BooleanBuilder builder = new BooleanBuilder();

		builder.and(qCimiteri.tenant.id.eq(tenantId));

		if (cimiteroId != null) {
			builder.and(qCimiteri.id.eq(cimiteroId));
		}

		if (request != null && request.getRicerca() != null && !request.getRicerca().trim().isEmpty()) {
			String[] parts = request.getRicerca().trim().toUpperCase().split("\\s+");
			for (String part : parts) {
				String search = "%" + part + "%";
				builder.and(
					qDefunti.nome.upper().like(search)
					.or(qDefunti.cognome.upper().like(search))
				);
			}
		}

		JPAQuery<Defunti> query = new JPAQuery<Defunti>(em)
				.select(qDefunti)
				.from(qDefunti)
				.join(qDefunti.posto, qPosti)
				.join(qPosti.area, qAree)
				.join(qAree.cimitero, qCimiteri)
				.where(builder)
				.orderBy(qDefunti.id.asc());

		if (request != null && request.getPage() != null && request.getSize() != null) {
			query.offset((long) request.getPage() * request.getSize())
				 .limit(request.getSize());
		}

		return query.fetch();
	}

	@Override
	public List<Defunti> findRecentDefuntiByTenant(Long tenantId, int limit) {
		QDefunti qDefunti = QDefunti.defunti;
		QPosti qPosti = QPosti.posti;
		QAree qAree = QAree.aree;
		QCimiteri qCimiteri = QCimiteri.cimiteri;

		return new JPAQuery<Defunti>(em)
				.select(qDefunti)
				.from(qDefunti)
				.join(qDefunti.posto, qPosti)
				.join(qPosti.area, qAree)
				.join(qAree.cimitero, qCimiteri)
				.where(qCimiteri.tenant.id.eq(tenantId))
				.orderBy(qDefunti.id.desc())
				.limit(limit)
				.fetch();
	}

}


