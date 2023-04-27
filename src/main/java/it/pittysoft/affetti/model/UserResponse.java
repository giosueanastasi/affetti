package it.pittysoft.affetti.model;

import java.util.ArrayList;
import java.util.List;

import it.pittysoft.affetti.entity.Users;
import lombok.Data;

@Data
public class UserResponse extends Response {
	List<Users> utenti = new ArrayList<>();
}
