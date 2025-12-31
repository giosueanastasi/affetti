package it.pittysoft.affetti.mapper;

import org.mapstruct.Mapper;

import it.pittysoft.affetti.entity.Assegnatari;
import it.pittysoft.affetti.dto.AssegnatariDto;

@Mapper(componentModel = "spring")
public interface AssegnatariMapper {

    AssegnatariDto toDto(Assegnatari assegnatari);

}