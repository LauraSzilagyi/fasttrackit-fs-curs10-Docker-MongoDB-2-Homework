package ro.fasttrackit.curs10.homework.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import ro.fasttrackit.curs10.homework.entity.Route;

public interface RouteRepository extends MongoRepository<Route, String> {
}
