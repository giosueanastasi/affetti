package it.pittysoft.affetti.mapper;

import org.mapstruct.Mapper;

import it.pittysoft.affetti.dto.ContraentiDto;
import it.pittysoft.affetti.entity.Contraenti;

@Mapper(componentModel = "spring")
public interface ContraentiMapper {
	
	ContraentiDto toDto(Contraenti contraenti);
	
}
