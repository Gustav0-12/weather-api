package api_clima.dto;

import api_clima.entities.enums.UserRole;

import java.io.Serializable;

public record UserResponseDTO(String username, String email, UserRole userRole) implements Serializable {
}
