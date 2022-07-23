package ro.fasttrackit.curs10.homework.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ro.fasttrackit.curs10.homework.entity.Location;
import ro.fasttrackit.curs10.homework.exceptions.AlreadyExistException;
import ro.fasttrackit.curs10.homework.exceptions.EntityNotFoundException;
import ro.fasttrackit.curs10.homework.exceptions.InvalidModelException;
import ro.fasttrackit.curs10.homework.model.LocationModel;
import ro.fasttrackit.curs10.homework.repository.LocationRepository;

import java.util.List;
import java.util.Optional;

import static java.util.Objects.isNull;
import static java.util.UUID.randomUUID;

@Service
@RequiredArgsConstructor
public class LocationService {
    private final LocationRepository repository;


    public List<Location> getAll() {
        return repository.findAll();
    }

    public Location addNewLocation(LocationModel model) {
        validateModel(model);
        Location entity = Location.builder()
                .id(randomUUID().toString())
                .city(model.city())
                .build();
        return repository.save(entity);
    }

    private void validateModel(LocationModel model) {
        verifyForNull(model);
        verifyIfLocationAlreadyExist(model);
    }

    private void verifyIfLocationAlreadyExist(LocationModel model) {
        Optional<Location> byCity = repository.findByCityIgnoreCase(model.city());
        if (byCity.isPresent()) {
            throw new AlreadyExistException("Location already exists!");
        }
    }

    private void verifyForNull(LocationModel model) {
        if (isNull(model.city())) {
            throw new InvalidModelException("Location must contains the city name!");
        }
    }

    public Optional<Location> deleteLocation(String id) {
        Optional<Location> entity = repository.findById(id);
        entity.ifPresent(repository::delete);
        return entity;
    }

    public void verifyIfLocationExists(String location) {
        findLocationByCityName(location);
    }

    public Location findLocationByCityName(String cityName) {
        return repository.findByCityIgnoreCase(cityName)
                .orElseThrow(() -> new EntityNotFoundException("Location not found!"));
    }

    public String getCityNameByLocationId(String id) {
        Location location = repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Location not found with id %s".formatted(id)));
        return location.city();
    }
}
