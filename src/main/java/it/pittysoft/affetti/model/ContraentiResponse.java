package it.pittysoft.affetti.model;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.domain.Page;

import lombok.Data;

@Data
public class ContraentiResponse extends Response {
	Page<ContraentiModel> contraenti;
}
