package api_clima.dto;

import api_clima.entities.enums.UserRole;

import java.io.Serializable;

public record RegisterRequestDTO(String username, String email, String password, UserRole userRole) implements Serializable {
}
