package api_clima.dto;

import api_clima.entities.City;

import java.io.Serializable;
import java.util.List;

public record FavoriteCityDTO(City cities) implements Serializable {
}

