package ro.fasttrackit.curs10.homework.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import ro.fasttrackit.curs10.homework.entity.Location;

import java.util.Optional;

public interface LocationRepository extends MongoRepository<Location, String> {
    Optional<Location> findByCityIgnoreCase(String location);
}
