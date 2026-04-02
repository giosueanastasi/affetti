package it.pittysoft.affetti.model;

import java.util.List;

import lombok.Data;

@Data
public class ProfileResponse {
    private String username;
    private String email;
    private List<String> roles;
}
