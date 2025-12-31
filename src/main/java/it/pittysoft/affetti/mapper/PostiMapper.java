package it.pittysoft.affetti.mapper;

import org.mapstruct.Mapper;

import it.pittysoft.affetti.dto.PostiDto;
import it.pittysoft.affetti.entity.Posti;

@Mapper(componentModel = "spring")
public interface PostiMapper {
	
	PostiDto toDto(Posti posti);
	
}
