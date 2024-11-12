package api_clima.dto;

import java.io.Serializable;
import java.time.Instant;

public record LoginResponseDTO(String token, Instant expirationTime) implements Serializable {
}
