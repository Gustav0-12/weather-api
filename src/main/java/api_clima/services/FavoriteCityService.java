package api_clima.services;

import api_clima.dto.FavoriteCityDTO;
import api_clima.entities.City;
import api_clima.entities.FavoriteCity;
import api_clima.entities.User;
import api_clima.entities.Weather;
import api_clima.repository.FavoriteCityRepository;
import api_clima.security.TokenProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FavoriteCityService {

    @Autowired
    FavoriteCityRepository repository;

    @Autowired
    TokenProvider tokenProvider;

    @Autowired
    CityService cityService;

    @Autowired
    WeatherService weatherService;

    @CacheEvict(value = "weather", allEntries = true)
    public void addFavoriteCity(String name) {
        User user = tokenProvider.getUser();
        City city = cityService.addCity(name);

        FavoriteCity existingFavoriteCity = repository.findByCityNameAndUserId(city.getName(), user.getId());
        if (existingFavoriteCity != null) {
            throw new RuntimeException("Cidade já favoritada");
        }

        FavoriteCity favoriteCity = new FavoriteCity();
        favoriteCity.setCity(city);
        favoriteCity.setUser(user);
        repository.save(favoriteCity);
    }

    @CacheEvict(value = "weather", allEntries = true)
    public void  removeFavoriteCity(String name) {
        User user = tokenProvider.getUser();
        FavoriteCity favoriteCity = repository.findByCityNameAndUserId(name, user.getId());

        if (favoriteCity == null) {
            throw new RuntimeException("Essa cidade n foi favoritada");
        }
        repository.delete(favoriteCity);
    }

    @Cacheable(value = "weather")
    public List<FavoriteCityDTO> getFavoriteCities(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);

        User user = tokenProvider.getUser();
        Page<FavoriteCity> favoriteCity = repository.findByUserId(user.getId(), pageable);

        for(FavoriteCity fav : favoriteCity) {
            Weather weather = weatherService.getWeatherByCity(fav.getCity().getName());
            fav.getCity().setWeatherDetails(weather);
        }

        return favoriteCity.map(fav -> new FavoriteCityDTO(fav.getCity())).stream().toList();
    }
}
