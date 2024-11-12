package api_clima.controller;

import api_clima.dto.FavoriteCityDTO;
import api_clima.security.TokenProvider;
import api_clima.services.FavoriteCityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/favorite")
public class FavoriteCityController {

    @Autowired
    FavoriteCityService service;

    @Autowired
    TokenProvider tokenProvider;

    @GetMapping
    public ResponseEntity<List<FavoriteCityDTO>> getFavoriteCities(@RequestParam(defaultValue = "0") int page, @RequestParam int size) {
        List<FavoriteCityDTO> favoriteCity = service.getFavoriteCities(page, size);
        return ResponseEntity.ok(favoriteCity);
    }

    @PostMapping("/add")
    public ResponseEntity<Void> addFavoriteCity(@RequestParam String cityName) {
        service.addFavoriteCity(cityName);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/remove")
    public ResponseEntity<Void> removeFavoriteCity(@RequestParam String cityName) {
        service.removeFavoriteCity(cityName);
        return ResponseEntity.noContent().build();
    }
}
