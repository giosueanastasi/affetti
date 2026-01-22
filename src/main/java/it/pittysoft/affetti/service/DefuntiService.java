package it.pittysoft.affetti.service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.pittysoft.affetti.entity.Defunti;
import it.pittysoft.affetti.model.DefuntiRequest;
import it.pittysoft.affetti.repository.DefuntiRepository;

@Service
public class DefuntiService {

	@Autowired
	private DefuntiRepository defuntiRepository;

	public List<Defunti> getDefunti(DefuntiRequest request) {
		return defuntiRepository.findDefuntiByNameAndOrSurname(request);
	}

	public Optional<Defunti> getDefuntiById(Long id) {
		return defuntiRepository.findById(id);
	}

	public Defunti saveDefunto(Defunti defunto) {
		// Genera codice automatico se non presente
		if (defunto.getCodice() == null || defunto.getCodice().isEmpty()) {
			defunto.setCodice(generateCodiceDefunto(defunto.getData_decesso()));
		}
		return defuntiRepository.save(defunto);
	}

	/**
	 * Genera automaticamente il codice univoco per un defunto.
	 * Formato: DEF{anno_decesso}-{progressivo}
	 * Esempio: DEF2023-001, DEF2023-002
	 */
	private String generateCodiceDefunto(String dataDecesso) {
		int anno;
		if (dataDecesso != null && dataDecesso.length() >= 4) {
			// Estrae l'anno dalla data in formato yyyy-MM-dd o simile
			anno = Integer.parseInt(dataDecesso.substring(0, 4));
		} else {
			anno = LocalDate.now().getYear();
		}

		String prefix = "DEF" + anno;
		Long count = defuntiRepository.countByCodiceLike(prefix);
		int progressivo = count.intValue() + 1;

		return String.format("%s-%03d", prefix, progressivo);
	}

}
