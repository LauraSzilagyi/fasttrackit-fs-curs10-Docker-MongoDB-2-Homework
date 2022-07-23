package ro.fasttrackit.curs10.homework.loader;


import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import ro.fasttrackit.curs10.homework.entity.Location;
import ro.fasttrackit.curs10.homework.entity.Route;
import ro.fasttrackit.curs10.homework.entity.Train;
import ro.fasttrackit.curs10.homework.repository.LocationRepository;
import ro.fasttrackit.curs10.homework.repository.RouteRepository;
import ro.fasttrackit.curs10.homework.repository.TrainRepository;

import java.util.List;

import static java.util.UUID.randomUUID;
@Component
@RequiredArgsConstructor
public class DataLoader implements CommandLineRunner {
    private final TrainRepository trainRepository;
    private final LocationRepository locationRepository;
    private final RouteRepository routeRepository;


    @Override

    public void run(String... args) throws Exception {
        List<Location> locations = getLocationsData();
        List<Train> trains = getTrainsData(locations);
        getRoutesData(locations, trains);
    }

    private void getRoutesData(List<Location> locations, List<Train> trains) {
        routeRepository.deleteAll();
        List.of(
                new Route(randomUUID().toString(), locations.get(0), locations.get(1), 170, trains.get(0).id()),
                new Route(randomUUID().toString(), locations.get(1), locations.get(2), 540, trains.get(1).id()),
                new Route(randomUUID().toString(), locations.get(2), locations.get(3), 440, trains.get(2).id()),
                new Route(randomUUID().toString(), locations.get(3), locations.get(4), 170, trains.get(3).id())

        ).forEach(routeRepository::save);
        System.out.println(routeRepository.findAll());
    }

    private List<Train> getTrainsData(List<Location> locations) {
        trainRepository.deleteAll();
        List.of(
                new Train(randomUUID().toString(), "HL2651", 3, locations.get(0).id()),
                new Train(randomUUID().toString(), "HJ2392", 10, locations.get(1).id()),
                new Train(randomUUID().toString(), "sta-1021", 5, locations.get(0).id()),
                new Train(randomUUID().toString(), "TA-11001", 7, locations.get(2).id()),
                new Train(randomUUID().toString(), "r-71239", 1, locations.get(3).id())
        ).forEach(trainRepository::save);

        List<Train> trains = trainRepository.findAll();
        System.out.println(trains);
        return trains;
    }

    private List<Location> getLocationsData() {
        locationRepository.deleteAll();
          List.of(
                new Location(randomUUID().toString(), "Oradea"),
                new Location(randomUUID().toString(), "Timisoara"),
                new Location(randomUUID().toString(), "Bucuresti"),
                new Location(randomUUID().toString(), "Cluj"),
                new Location(randomUUID().toString(), "Sibiu")
        ).forEach(locationRepository::save);
        List<Location> locations = locationRepository.findAll();
        System.out.println(locations);
        return locations;
    }

}