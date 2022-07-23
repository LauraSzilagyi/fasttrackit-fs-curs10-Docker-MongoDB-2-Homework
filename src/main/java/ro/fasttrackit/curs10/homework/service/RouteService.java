package ro.fasttrackit.curs10.homework.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ro.fasttrackit.curs10.homework.entity.Location;
import ro.fasttrackit.curs10.homework.entity.Route;
import ro.fasttrackit.curs10.homework.entity.Train;
import ro.fasttrackit.curs10.homework.exceptions.EntityNotFoundException;
import ro.fasttrackit.curs10.homework.exceptions.InvalidModelException;
import ro.fasttrackit.curs10.homework.exceptions.TrainNotInTheStartLocationException;
import ro.fasttrackit.curs10.homework.model.RouteModel;
import ro.fasttrackit.curs10.homework.repository.RouteRepository;
import ro.fasttrackit.curs10.homework.repository.TrainRepository;

import java.util.List;

import static java.util.Objects.isNull;
import static java.util.UUID.randomUUID;

@Service
@RequiredArgsConstructor
public class RouteService {

    private final RouteRepository repository;
    private final LocationService locationService;
    private final TrainRepository trainRepository;


    public List<Route> getAll() {
        return repository.findAll();
    }

    public Route addNewRoute(RouteModel model) {
        validateModel(model);
        Location startLocation = locationService.findLocationByCityName(model.start());
        Location destinationLocation = locationService.findLocationByCityName(model.destination());
        Train train = findTrainByModel(model.trainModel());
        Route entity = Route.builder()
                .id(randomUUID().toString())
                .start(startLocation)
                .destination(destinationLocation)
                .length(model.length())
                .trainId(train.id())
                .build();
        return repository.save(entity);
    }

    private void validateModel(RouteModel model) {

        verifyForNull(model);
        verifyIfStartIsAnExistingLocation(model.start());
        verifyIfDestinationIsAnExistingLocation(model.destination());
        verifiIfTrainIsInTheStartLocation(model.start(), model.trainModel());

    }

    private void verifiIfTrainIsInTheStartLocation(String start, String trainModel) {
        Train train = findTrainByModel(trainModel);
        String cityName = locationService.getCityNameByLocationId(train.locationId());
        if (trainIsNotInStartLocation(start, cityName)) {
            throw new TrainNotInTheStartLocationException("The train is not in the start location");
        }
    }

    private boolean trainIsNotInStartLocation(String start, String cityNameByLocationId) {
        return !cityNameByLocationId.equalsIgnoreCase(start);
    }

    private Train findTrainByModel(String trainModel) {
        return trainRepository.findByModel(trainModel)
                .orElseThrow(() -> new EntityNotFoundException("Train with this model not found!"));
    }

    private void verifyIfDestinationIsAnExistingLocation(String destination) {
        locationService.findLocationByCityName(destination);
    }

    private void verifyIfStartIsAnExistingLocation(String start) {
        locationService.findLocationByCityName(start);
    }

    private void verifyForNull(RouteModel model) {
        if (isNull(model.start()) || isNull(model.destination()) || isNull(model.trainModel())) {
            throw new InvalidModelException("Route must contains start, destination, and train model!");
        }
    }
}
