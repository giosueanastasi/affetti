package it.pittysoft.affetti.mapper;

import org.mapstruct.Mapper;

import it.pittysoft.affetti.dto.DomandeDto;
import it.pittysoft.affetti.entity.Domande;

@Mapper(
		componentModel = "spring", 
		uses = { AssegnatariMapper.class, PostiMapper.class, ContraentiMapper.class })
public interface DomandeMapper {
	
	DomandeDto toDto(Domande domande);
	
}
