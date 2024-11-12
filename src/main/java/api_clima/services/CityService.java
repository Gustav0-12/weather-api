package api_clima.services;

import api_clima.entities.City;
import api_clima.entities.Weather;
import api_clima.repository.CityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CityService {

    @Autowired
    CityRepository repository;

    public City addCity(String name) {
        Optional<City> existingCity = getCityByName(name);

        City newCity;
        if (existingCity.isEmpty()) {
            newCity = new City();
            newCity.setName(name);
        } else {
            newCity = existingCity.get();
        }

        return repository.save(newCity);
    }

    public Optional<City> getCityByName(String name) {
        Optional<City> city = repository.findByName(name);
        return city;
    }

}
