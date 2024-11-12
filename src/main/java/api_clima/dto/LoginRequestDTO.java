package api_clima.dto;

import java.io.Serializable;

public record LoginRequestDTO(String email, String password) implements Serializable {
}
