package it.pittysoft.affetti.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.itextpdf.text.pdf.PdfStructTreeController.returnType;

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
	
}
